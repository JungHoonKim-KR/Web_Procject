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
import org.springframework.web.bind.annotation.ResponseBody;
import post.study.dto.MemberDto;
import post.study.entity.ChatInvitation;
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
    @GetMapping("/chat/roomList")
    public String roomList(HttpSession session,Model model){
        MemberDto member = (MemberDto) session.getAttribute("member");
        List<ChatRoom> allRoom = chatService.findAllChatRoom(member.getId());
        model.addAttribute("roomList",allRoom);
        return "chat/roomList";
    }
    @GetMapping("/chat/create")
    public String createChatRoom(HttpSession session,Model model) {
        if(session.getAttribute("member")==null){
            model.addAttribute("msg","로그인 필요");
            model.addAttribute("url","back");
        }
        return "chat/create";
    }
    @PostMapping("/chat/create")
    public String ChatRoom(HttpSession session,String roomName){
        MemberDto member = (MemberDto) session.getAttribute("member");
        chatService.createRoom(roomName,member.getId(),member.getUsername());
        return "redirect:/chat/roomList";
    }
    @MessageMapping("/chat/enter")
    public void enterMessage(ChatMessage message ) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getWriter() + "님이 입장했습니다.");
        sendingOperations.convertAndSend("/sub/chat/room" + message.getRoomId(),message);

    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        message.setMessage(message.getMessage());
        chatService.saveMessage(message);
        sendingOperations.convertAndSend("/sub/chat/room"+message.getRoomId(),message);
    }



    @GetMapping("/chat")
    public String room(HttpSession session,String roomId, Model model){
        List<ChatMessage> messageList = chatService.findMessage(roomId);
        MemberDto member = (MemberDto) session.getAttribute("member");
        model.addAttribute("roomId",roomId);
        model.addAttribute("member",member);
        model.addAttribute("messageList",messageList);
        return "chat/room";
    }

    @GetMapping("/chat/invite")
    @ResponseBody
    public String invite(String roomId, String username){
        ChatInvitation chatInvitation = chatService.chatInvite(roomId, username);
        if(chatInvitation==null) {
            return "실패";
        }
        else return "성공";


    }

    @GetMapping("/chat/invitationList")
    public String inviteList(HttpSession session,Model model){
        MemberDto member = (MemberDto) session.getAttribute("member");
        List<ChatInvitation> allChatInvitation = chatService.findAllChatInvitation(member);
        model.addAttribute("member",member);
        model.addAttribute("invitationList",allChatInvitation);
        return "chat/invitationList";
    }

    @GetMapping("/chat/approve")
    @ResponseBody
    public String approving(String value,Long memberId, String username,ChatRoom chatRoom){
        if(value.equals("승인")){
            chatService.applyInvitation(chatRoom,memberId,username);
        }

            chatService.deleteInvitation(chatRoom,memberId);
        return value;
        }




}

