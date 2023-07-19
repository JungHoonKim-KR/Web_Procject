package post.study.entity;

import lombok.Getter;
import lombok.Setter;
import post.study.dto.ProjectDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="project_id")
    private Long id;
    private String projectName;
    private String projectLeader;
    private String scale;
    private String img;
    private String introduction;

    @OneToMany(mappedBy = "project")
    private List<Category> categoryList=new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> projectMemberList=new ArrayList<>();
    @OneToMany(mappedBy = "project")
    private List<BookmarkProject>bookmarkProjectList=new ArrayList<>();

    public void addProjectMember(ProjectMember projectMember){
        projectMemberList.add(projectMember);

    }

    public void addCategory(Category category){
        categoryList.add(category);
        category.setProject(this);
    }

//    public ProjectDto projectToDto(Project project){
//        return new ProjectDto(project);
//    }
}
