package post.study.entity;

import com.amazonaws.services.kms.model.MessageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import post.study.entity.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Entity
@Getter
@Setter
@Entity
public class ChatMessage {
    public enum MessageType{
        ENTER,TALK,COMM
    }
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private MessageType type;
    private String roomId;
    private String writer;
    private String message;
    private LocalDateTime createTime = LocalDateTime.now();
    private String messageDate= LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm"));

//   메세지에는 Writer의 이름와 roomId만 있으면 된다고 판단하여 연관성 매핑을 하지 않았음.


//    public void addChatMessage(ChatRoom chatRoom, )




}
