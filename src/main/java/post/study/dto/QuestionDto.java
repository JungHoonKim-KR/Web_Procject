package post.study.dto;

import lombok.Data;
import post.study.entity.Member;
import post.study.entity.Question;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class QuestionDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime dateTime;
    private Member member;

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.username = question.getUsername();
        this.dateTime = question.getDateTime();
        this.member = question.getMember();
    }





}
