package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import post.study.dto.MemberDto;
import post.study.entity.ChatMessage;
import post.study.entity.ChatRoom;
import post.study.service.ChatService;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final SimpMessageSendingOperations sendingOperations;

    private final ChatService chatService;

    //발신자가 pub/chat/message로 메시지를 보냄
    @GetMapping("/room/roomList")
    public String roomList(Model model){
        List<ChatRoom> allRoom = chatService.findAllRoom();
        model.addAttribute("roomList",allRoom);
        return "chat/roomList";
    }
    @GetMapping("/room/create")
    public String createChatRoom(HttpSession session,Model model) {
        if(session.getAttribute("member")==null){
            model.addAttribute("msg","로그인 필요");
            model.addAttribute("url","back");
        }
        return "chat/create";
    }
    @PostMapping("/room/create")
    public String ChatRoom(HttpSession session,String roomName){
        MemberDto member = (MemberDto) session.getAttribute("member");

        chatService.createRoom(roomName,member.getId(),member.getUsername());
        return "chat/roomList";
    }
    @MessageMapping("/chat/enter")
    public void enterMessage(ChatMessage message ) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getWriter() + "이 입장했습니다.");
        System.out.println("메시지: "+message.getRoomId());
        sendingOperations.convertAndSend("/sub/chat/room" + message.getRoomId(),message);
        log.info("입장 완료");
        System.out.println("입장 완료");

    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        message.setMessage(message.getMessage());
        chatService.saveMessage(message);
        sendingOperations.convertAndSend("/sub/chat/room"+message.getRoomId(),message);
        System.out.println("메시지: "+ message.getMessage());
    }



    @GetMapping("/room")
    public String room(HttpSession session,String roomId, Model model){
        List<ChatMessage> messageList = chatService.findMessage(roomId);
        MemberDto member = (MemberDto) session.getAttribute("member");
        model.addAttribute("roomId",roomId);
        model.addAttribute("member",member);
        model.addAttribute("messageList",messageList);
        return "chat/room";
    }
    @GetMapping("/rooms")
    public List<ChatRoom> rooms() {
        return chatService.findAllRoom();
    }



}

