package post.study.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProjectDto {
    private Long id;
    private String projectName;
    private String projectLeader;
    private String scale;
    private String img;
    private String introduction;
    private String comment;
    private Integer curHeadcount;
    private Integer totalHeadcount;
    private LocalDateTime creationTime;

    @Builder
    public ProjectDto(Long id,String projectName, String projectLeader, String scale, String img, String introduction,String comment,Integer curHeadcount,Integer totalHeadcount,LocalDateTime creationTime) {
        this.id=id;
        this.projectName = projectName;
        this.projectLeader = projectLeader;
        this.scale = scale;
        this.img = img;
        this.introduction = introduction;
        this.comment=comment;
        this.curHeadcount=curHeadcount;
        this.totalHeadcount=totalHeadcount;
        this.creationTime=creationTime;
    }
}
