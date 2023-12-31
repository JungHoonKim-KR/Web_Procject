package post.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.study.entity.Project;
import post.study.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    Page<Question> findAll(Pageable pageable);

}
