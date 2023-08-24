package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.study.entity.ProjectFile_Img;

import java.util.List;

@Repository
public interface ProjectFile_ImgRepository extends JpaRepository<ProjectFile_Img,Long> {
    List<ProjectFile_Img> findAllByProjectId(Long projectId);
}
