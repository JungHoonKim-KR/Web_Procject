package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.study.entity.Field_Member;

@Repository
public interface FieldMemberRepository extends JpaRepository<Field_Member, Long> {
}
