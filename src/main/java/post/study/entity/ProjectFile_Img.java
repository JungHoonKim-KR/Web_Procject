package post.study.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProjectFile_Img {
    @Id@GeneratedValue
    private Long id;
    private Long projectId;
    private String filename;
    private String fileOriginName;
    private String fileUrl;
    private String fileExtension;

    @Builder

    public ProjectFile_Img(Long id, Long projectId, String filename, String fileOriginName, String fileUrl,String fileExtension) {
        this.id = id;
        this.projectId = projectId;
        this.filename = filename;
        this.fileOriginName = fileOriginName;
        this.fileUrl = fileUrl;
        this.fileExtension=fileExtension;
    }

    public ProjectFile_Img() {

    }
}
