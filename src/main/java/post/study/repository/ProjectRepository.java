package post.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import post.study.entity.Project;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    Page<Project> findAll(Pageable pageable);
    Project findByProjectName(String projectName);
    Optional<Project> findById(Long id);
@Query("select p from Project p where p.projectName=:projectName and p.id!=:projectId ")
    Project findUpdate(@Param("projectName")String projectName,@Param("projectId") Long id);

}
