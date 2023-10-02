package post.study.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import post.study.entity.ChatRoom;

import java.util.List;
import java.util.Map;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

@Query("select c from ChatRoom c where c.roomId=:roomId ")
  ChatRoom findByRoomId(@Param("roomId") String roomId);





}
