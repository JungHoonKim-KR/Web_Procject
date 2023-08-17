package post.study.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;
    private String emailId;
    private String username;
    private String password;

    @OneToMany(mappedBy = "member") //멤버의 질문
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "member") //멤버의 프로젝트
    private List<ProjectMember> projectMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<BookmarkProject> bookmarkProjectList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Language_Member> languageList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Field_Member> fieldMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Applicant> applicantList=new ArrayList<>();
    public Member() {

    }

    @Builder
    public Member(Long id,String emailId, String username, String password) {
        this.id=id;
        this.emailId = emailId;
        this.username = username;
        this.password = password;
    }

    public void addBookmarkProjectList(BookmarkProject bookmarkProject) {
        bookmarkProjectList.add(bookmarkProject);


    }

    public void addProjectMemberList(ProjectMember projectMember) {
        projectMemberList.add(projectMember);
    }

    public void addApplicantList(Applicant applicant){
        applicantList.add(applicant);
    }

    public void addLanguage(Language_Member language) {
        languageList.add(language);
        language.setMember(this);

    }

    public void addField(Field_Member field) {
        fieldMemberList.add(field);
        field.setMember(this);
    }


}
