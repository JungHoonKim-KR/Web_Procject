package post.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class BookmarkProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private LocalDateTime projectCreateTime;

    public void setBookmarkProject(Member member,Project project){;
        this.setMember(member);
        this.setProject(project);
        this.projectCreateTime=project.getCreateTime();
        member.addBookmarkProjectList(this);
    }
}
