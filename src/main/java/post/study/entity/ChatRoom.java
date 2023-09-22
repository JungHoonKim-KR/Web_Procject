package post.study.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class ChatRoom {
//    private Set<WebSocketSession> sessions = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roomId = String.valueOf(UUID.randomUUID());
    private String roomName;

//    @OneToMany(mappedBy = "chatRoom")
//    private List<ChatMessage> messageList = new ArrayList<>();
    @OneToMany(mappedBy = "chatRoom")
    private List<ChatRoomMember> chatRoomMemberList = new ArrayList<>();



    @Builder
    public ChatRoom(String roomName) {
        this.roomName = roomName;
    }

    public ChatRoom() {

    }

    public void addChatRoomMember(ChatRoomMember chatRoomMember) {
        chatRoomMemberList.add(chatRoomMember);
    }

//    public void addMessage(ChatMessage message) {
//        messageList.add(message);
//    }

}