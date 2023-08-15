package post.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Applicant {
    @Id@GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private Project project;

    public void setApplicant(Member member, Project project){
        this.setMember(member);
        this.setProject(project);
        member.addApplicantList(this);
        project.setApplicant(this);
    }
}
