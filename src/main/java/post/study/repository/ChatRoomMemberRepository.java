package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import post.study.entity.ChatInvitation;
import post.study.entity.ChatMember;
import post.study.entity.ChatRoom;
import post.study.entity.ChatRoomMember;
import java.util.List;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember,Long> {
    @Query("select m.chatMember from ChatRoomMember m where m.chatRoom.roomId=:roomId")
    List<ChatMember> findChatMemberByRoomId(@Param("roomId") String roomId);
    @Query("select m.chatRoom from ChatRoomMember m where m.chatMember.memberId=:memberId")
    List<ChatRoom> findChatRoomByMemberId(@Param("memberId")Long memberId);


}
