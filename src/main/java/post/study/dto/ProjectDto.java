package post.study.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;
import post.study.entity.Member;
import post.study.entity.Project;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProjectDto {
    private Long id;
    private String projectName;
    private String projectLeader;
    private String scale;
    private String img;
    private String introduction;
    private LocalDateTime creationTime;

    @Builder
    public ProjectDto(Long id,String projectName, String projectLeader, String scale, String img, String introduction,LocalDateTime creationTime) {
        this.id=id;
        this.projectName = projectName;
        this.projectLeader = projectLeader;
        this.scale = scale;
        this.img = img;
        this.introduction = introduction;
        this.creationTime=creationTime;
    }
}
