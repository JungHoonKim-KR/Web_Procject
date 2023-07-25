package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import post.study.entity.Field_Project;
import post.study.entity.Language_Project;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FieldProjectRepository extends JpaRepository<Field_Project,Long> {
    @Modifying
    @Transactional
    @Query("delete from Field_Project f where f.project.id=:projectId")
    void deleteFeild_ProjectByProjectId(@Param("projectId")Long projectId);

    @Query("select f from Field_Project f where f.project.id=:id")
    List<Field_Project> findAllById(@Param("id") Long id);
}
