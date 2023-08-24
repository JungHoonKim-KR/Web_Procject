package post.study.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ProjectFile_ImgDto {
    private Long id;
    private Long projectId;
    private String filename;
    private String fileOriginName;
    private String fileUrl;
    private String fileExtension;

    @Builder

    public ProjectFile_ImgDto(Long id, Long projectId, String filename, String fileOriginName, String fileUrl,String fileExtension) {
        this.id = id;
        this.projectId = projectId;
        this.filename = filename;
        this.fileOriginName = fileOriginName;
        this.fileUrl = fileUrl;
        this.fileExtension=fileExtension;
    }
}
