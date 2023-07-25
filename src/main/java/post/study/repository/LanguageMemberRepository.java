package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import post.study.entity.Language_Member;

import javax.transaction.Transactional;

@Repository
public interface LanguageMemberRepository extends JpaRepository<Language_Member, Long> {
    @Modifying
    @Transactional
    @Query("delete from Language_Member l where l.member.id=:memberId")
    void deleteLanguage_MemberByMemberId(@Param("memberId") Long memberId);
}


