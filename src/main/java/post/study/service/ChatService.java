package post.study.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import post.study.dto.MemberDto;
import post.study.entity.*;
import post.study.repository.*;

import java.io.IOException;
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
    private final ChatInvitationRepository chatInvitationRepository;
    private final MemberRepository memberRepository;
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

    public List<ChatRoom> findAllRoomById(Long id){
        return chatRoomRepository.findAll();
    }

    public void saveMessage(ChatMessage message){
        ChatMessage chatMessage = new ChatMessage();
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

    public List<ChatRoom> findAllChatRoom(Long memberId){
        return chatRoomMemberRepository.findChatRoomByMemberId(memberId);
    }

    public ChatInvitation chatInvite(String roomId, String username){
        Member member = memberRepository.findMemberByUsername(username);
        if(member==null)
            return null;
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId);
        ChatInvitation chatInvitation = new ChatInvitation();
        chatInvitation.setInvitation(member,chatRoom);
        chatInvitationRepository.save(chatInvitation);
        return chatInvitation;
    }

    public List<ChatInvitation> findAllChatInvitation(Member member){
        return chatInvitationRepository.findChatInvitation(member.getId());
    }

    public void applyInvitation(ChatRoom chatRoomDto, Long memberId,String username){
        ChatMember chatMember = new ChatMember();
        chatMember.setMemberId(memberId);
        chatMember.setUsername(username);

        ChatRoom chatRoom = chatRoomRepository.findByRoomId(chatRoomDto.getRoomId());
        ChatRoomMember chatRoomMember=new ChatRoomMember();
        chatRoomMember.setChatRoomMemberList(chatMember,chatRoom);

        chatMemberRepository.save(chatMember);
        chatRoomMemberRepository.save(chatRoomMember);
    }

    public void deleteInvitation(ChatRoom chatRoom,Long memberId){
        chatInvitationRepository.deleteChatInvitation(chatRoom.getRoomId(),memberId);
    }
}
