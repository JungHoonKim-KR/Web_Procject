package post.study.dto;

import lombok.Data;
import post.study.entity.Member;
import post.study.entity.Project;

import java.util.List;

@Data
public class ProjectDto {
    private Long id;
    private String projectName;
    private String projectLeader;
    private String scale;
    private String img;
    private String introduction;
    private List<Member> memberList;

    public ProjectDto(String projectName, String projectLeader, String scale, String img, String introduction, List<Member> memberList) {
        this.projectName = projectName;
        this.projectLeader = projectLeader;
        this.scale = scale;
        this.img = img;
        this.introduction = introduction;
        this.memberList = memberList;
    }
}
