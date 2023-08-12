package post.study.dto;

import lombok.Builder;
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

    public QuestionDto(Long id,String title,String content){
        this.id=id;
        this.title=title;
        this.content=content;
    }
    @Builder
    public QuestionDto(Long id, String title, String content, String username, LocalDateTime dateTime, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.dateTime = dateTime;
        this.member = member;
    }





}
