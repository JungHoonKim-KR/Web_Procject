package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.study.entity.Language_Project;
@Repository
public interface LanguageProjectRepository extends JpaRepository<Language_Project,Long> {
}
