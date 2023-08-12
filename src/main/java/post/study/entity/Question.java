package post.study.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import post.study.dto.QuestionDto;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long id;
    private String title;
    private String content;
    private String username;
    @CreationTimestamp
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;



    @Builder
    public Question(Long id, String title, String content, String username, LocalDateTime dateTime,Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.dateTime = dateTime;
        this.member=member;
    }

    public Question() {

    }

    public void setMember(Member member) {
        this.member = member;
        member.getQuestionList().add(this);
    }

}
