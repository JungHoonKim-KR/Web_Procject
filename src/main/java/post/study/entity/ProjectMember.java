package post.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class ProjectMember {
    @Id
    @GeneratedValue
    @Column(name = "project_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setProjectMember(Member member, Project project){
        this.setMember(member);
        this.setProject(project);
        member.addProjectMemberList(this);
        project.addProjectMember(this);
    }

}
