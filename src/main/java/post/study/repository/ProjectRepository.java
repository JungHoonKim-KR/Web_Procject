package post.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.study.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    Page<Project> findAll(Pageable pageable);
    Project findByProjectName(String projectName);

}
