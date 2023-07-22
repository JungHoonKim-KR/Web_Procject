package post.study.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "project")
    private List<Language_Project> languageList = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> projectMemberList = new ArrayList<>();
    @OneToMany(mappedBy = "project")
    private List<BookmarkProject> bookmarkProjectList = new ArrayList<>();
    @OneToMany(mappedBy = "project")
    private List<Field_Project> fieldList = new ArrayList<>();

    public void addProjectMember(ProjectMember projectMember) {
        projectMemberList.add(projectMember);

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
