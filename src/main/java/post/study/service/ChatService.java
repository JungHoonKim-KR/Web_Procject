package post.study.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import post.study.entity.ChatMember;
import post.study.entity.ChatMessage;
import post.study.entity.ChatRoom;
import post.study.entity.ChatRoomMember;
import post.study.repository.ChatMemberRepository;
import post.study.repository.ChatMessageRepository;
import post.study.repository.ChatRoomMemberRepository;
import post.study.repository.ChatRoomRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ObjectMapper objectMapper;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatMemberRepository chatMemberRepository;
//    private Map<String, ChatRoom> msgRooms;
//
//
//    @PostConstruct
//    private void init(){
//        msgRooms=new LinkedHashMap<>();
//    }

    public void createRoom(String roomName, Long memberId, String username){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomName(roomName);

        ChatMember chatMember = new ChatMember();
        chatMember.setMemberId(memberId);
        chatMember.setUsername(username);

        ChatRoomMember chatRoomMember=new ChatRoomMember();
        chatRoomMember.setChatRoomMemberList(chatMember,chatRoom);

        chatRoomRepository.save(chatRoom);
        chatMemberRepository.save(chatMember);
        chatRoomMemberRepository.save(chatRoomMember);



    }
    public<T>void sendMessage(WebSocketSession session, T message){
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (JsonProcessingException e) {


        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }

    public List<ChatRoom> findAllRoom(){
        return chatRoomRepository.findAll();
    }

    public void saveMessage(ChatMessage message){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setType(message.getType());
        chatMessage.setType(message.getType());
        chatMessage.setRoomId(message.getRoomId());
        chatMessage.setWriter(message.getWriter());
        chatMessage.setMessage(message.getMessage());

        //ChatRoom & Member에 add를 해줘야 하는데 일단 보류


        chatMessageRepository.save(chatMessage);

    }

    public List<ChatMessage> findMessage(String roomId){
       return chatMessageRepository.findAllById(roomId);
    }

    public List<ChatMember> findAllMember(String roomId){
        return chatRoomMemberRepository.findChatMemberByRoomId(roomId);
    }
}
