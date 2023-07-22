package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.study.entity.Language_Member;
@Repository
public interface LanguageMemberRepository extends JpaRepository<Language_Member,Long> {

}


