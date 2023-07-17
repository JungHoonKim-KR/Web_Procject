package post.study.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String emailId;
    private String username;
    private String password;
    private String position;
    private Integer age;

    @OneToMany(mappedBy = "member") //멤버의 질문
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "member") //멤버의 프로젝트
    private List<ProjectMember> projectMemberList=new ArrayList<>();



}
