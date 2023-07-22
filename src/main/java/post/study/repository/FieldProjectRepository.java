package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.study.entity.Field_Project;
@Repository
public interface FieldProjectRepository extends JpaRepository<Field_Project,Long> {
}
