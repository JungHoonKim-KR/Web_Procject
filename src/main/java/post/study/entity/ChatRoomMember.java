package post.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import post.study.entity.ChatRoom;

@Getter
@Setter
@Entity
public class ChatRoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatRoom_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatMember_id")
    private ChatMember chatMember;

    public void setChatRoomMemberList(ChatMember chatMember, ChatRoom chatRoom){
        this.setChatMember(chatMember);
        this.setChatRoom(chatRoom);
        chatMember.addChatRoomMember(this);
        chatRoom.addChatRoomMember(this);
    }
}
