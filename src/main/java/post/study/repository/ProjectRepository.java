package post.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import post.study.entity.Field_Project;
import post.study.entity.Language_Project;
import post.study.entity.Project;
import post.study.repositoryCustom.ProjectRepositoryCustom;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findAll(Pageable pageable);

    Project findByProjectName(String projectName);

    Optional<Project> findById(Long id);

    @Query("select p from Project p where p.projectName=:projectName and p.id!=:projectId ")
    Project findUpdate(@Param("projectName") String projectName, @Param("projectId") Long id);

    @Modifying
    @Transactional
    @Query("delete from Field_Project f where f.project.id=:projectId")
    void deleteFeild_ProjectByProjectId(@Param("projectId") Long projectId);

    @Query("select f from Field_Project f where f.project.id=:id")
    List<Field_Project> findFieldById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("delete from Language_Project l where l.project.id=:projectId")
    void deleteLanguage_ProjectByProjectId(@Param("projectId") Long projectId);

    @Query("select l from Language_Project l where l.project.id=:id")
    List<Language_Project> findLanguageById(@Param("id") Long id);

}
