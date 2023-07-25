package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import post.study.entity.Field_Member;

import javax.transaction.Transactional;

@Repository
public interface FieldMemberRepository extends JpaRepository<Field_Member,Long> {
    @Modifying
    @Transactional
    @Query("delete from Field_Member f where f.member.id=:memberId")
    void deleteFeild_MemberByMemberId(@Param("memberId")Long memberId);
}
