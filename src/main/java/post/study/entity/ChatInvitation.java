package post.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ChatInvitation {
    @Id@GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatRoom_id")
    private ChatRoom chatRoom;

    public void setInvitation(Member member, ChatRoom chatRoom){
        this.setMember(member);
        this.setChatRoom(chatRoom);
        member.addChatInviteeList(this);
        chatRoom.addChatInvitee(this);
    }
}
