package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import post.study.entity.ChatInvitation;

import java.util.List;

@Repository
public interface ChatInvitationRepository extends JpaRepository<ChatInvitation,Long> {
    @Query("select i from ChatInvitation i where i.member.id=:memberId")
    List<ChatInvitation> findChatInvitation(@Param("memberId")Long memberId);

    @Modifying
    @Transactional
    @Query("delete from ChatInvitation i where i.chatRoom.roomId=:chatId and i.member.id=:memberId")
    void deleteChatInvitation(@Param("chatId") String chatId ,@Param("memberId") Long memberId);
}
