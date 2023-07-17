package post.study.dto;

import lombok.Data;
import post.study.entity.Project;
import post.study.entity.Question;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberDto {
    private Long id;
    private String emailId;
    private String username;
    private String password;
    private String position;
    private Integer age;
    private List<Question> questionList = new ArrayList<>();

    public MemberDto(String emailId, String username, String password, String position, Integer age, List<Question> questionList) {
        this.emailId = emailId;
        this.username = username;
        this.password = password;
        this.position = position;
        this.age = age;
        this.questionList = questionList;
    }
}
