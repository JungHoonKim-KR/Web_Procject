package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.study.entity.ChatMember;
import post.study.entity.ChatRoomMember;
@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember,Long> {
}
