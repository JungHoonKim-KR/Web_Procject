package post.study.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private Long id;
    private String projectName;
    private String projectLeader;
    private String scale;
    private String img;
    private String introduction;
    private LocalDateTime createTime = LocalDateTime.now();


    @OneToMany(mappedBy = "project")
    private List<Language_Project> languageList = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> projectMemberList = new ArrayList<>();
    @OneToMany(mappedBy = "project")
    private List<BookmarkProject> bookmarkProjectList = new ArrayList<>();
    @OneToMany(mappedBy = "project")
    private List<Field_Project> fieldList = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Applicant> applicantList=new ArrayList<>();


    @Builder
    public Project(Long id,String projectName, String projectLeader, String scale, String img, String introduction,LocalDateTime createTime) {
        this.id=id;
        this.projectName = projectName;
        this.projectLeader = projectLeader;
        this.scale = scale;
        this.img = img;
        this.introduction = introduction;
        this.createTime=createTime;
    }

    public Project() {

    }

    public void addProjectMember(ProjectMember projectMember) {
        projectMemberList.add(projectMember);

    }

    public void addApplicantList(Applicant applicant){
        applicantList.add(applicant);
    }

    public void addLanguage(Language_Project language) {
        languageList.add(language);
        language.setProject(this);
    }

    public void addField(Field_Project field) {

        fieldList.add(field);
        field.setProject(this);
    }

}
