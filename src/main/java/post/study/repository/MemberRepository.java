package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.study.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByemailId(String emailId);

}
