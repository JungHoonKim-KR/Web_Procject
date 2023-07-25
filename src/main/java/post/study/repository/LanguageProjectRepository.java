package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import post.study.entity.Language_Project;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LanguageProjectRepository extends JpaRepository<Language_Project,Long> {

    @Modifying
    @Transactional
    @Query("delete from Language_Project l where l.project.id=:projectId")
    void deleteLanguage_ProjectByProjectId(@Param("projectId") Long projectId);

    @Query("select l from Language_Project l where l.project.id=:id")
    List<Language_Project> findAllById(@Param("id") Long id);
}
