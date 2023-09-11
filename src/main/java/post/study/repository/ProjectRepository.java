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
public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {
    Page<Project> findAll(Pageable pageable);

    Project findByProjectName(String projectName);

    Optional<Project> findById(Long id);

    @Query("select p from Project p where p.projectName=:projectName and p.id!=:projectId ")
    Project findUpdate(@Param("projectName") String projectName, @Param("projectId") Long id);

    @Modifying
    @Transactional
    @Query("delete from Field_Project f where f.project.id=:projectId")
    void deleteFeild_ProjectByProjectId(@Param("projectId") Long projectId);

    @Modifying
    @Transactional
    @Query("delete from Language_Project l where l.project.id=:projectId")
    void deleteLanguage_ProjectByProjectId(@Param("projectId") Long projectId);
    //프로젝트는 pageList에서 모든 project에 field,language를 set해야 하기 때문에 String으로 받지 않았음
    //프로젝트와 field,language의 연관성 매핑 타입을 String으로 하면 되긴 하지만 혹시나 field,language 객체를 사용해야 할 수도 있기 때문에 String으로 받지 않았음


    @Query("select f.field from Field_Project f where f.project.id=:id")
    List<Field_Project> findFieldById(@Param("id") Long id);
    @Query("select l.language from Language_Project l where l.project.id=:id")
    List<Language_Project> findLanguageById(@Param("id") Long id);

    @Query("select f.field from Field_Project f where f.project.id=:id")
    List<String> findFieldByIdVerString(@Param("id") Long id);

    @Query("select l.language from Language_Project l where l.project.id=:id")
    List<String> findLanguageByIdVerString(@Param("id") Long id);

}
