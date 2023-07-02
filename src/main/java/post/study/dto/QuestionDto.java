package post.study.dto;

import lombok.Data;
import post.study.entity.Member;

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

}
