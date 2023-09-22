package post.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ChatMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private String username;

    @OneToMany(mappedBy = "chatMember")
    private List<ChatRoomMember> chatRoomMemberList = new ArrayList<>();
//    @OneToMany(mappedBy = "chatMember")
//    private List<ChatMessage> chatMessageList=new ArrayList<>();

    public void addChatRoomMember(ChatRoomMember chatRoomMember) {
        chatRoomMemberList.add(chatRoomMember);
    }

//    public void addChatMessage(ChatMessage chatMessage) {
//        chatMessageLIst.add(chatMessage);
//    }



}
