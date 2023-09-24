package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import post.study.entity.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByemailId(String emailId);
    @Query("select m from Member m where m.emailId=:emailId or m.username=:username")
    Member findByEmailIdOrUsername(@Param("emailId") String emailId,@Param("username") String username);
    @Modifying
    @Transactional
    @Query("delete from Language_Member l where l.member.id=:memberId")
    void deleteLanguage_MemberByMemberId(@Param("memberId") Long memberId);
    @Modifying
    @Transactional
    @Query("delete from Field_Member f where f.member.id=:memberId")
    void deleteFeild_MemberByMemberId(@Param("memberId")Long memberId);
    @Query("select l.member from Language_Member l where l.language in (:list) GROUP BY l.member having count(*) =2")
    List<Member> findMember(@Param("list") List<String> languageList);
    @Query("select f.field from Field_Member f where f.member.id=:id")
    List<String> findFieldByIdVerString(@Param("id") Long id);

    @Query("select l.language from Language_Member l where l.member.id=:id")
    List<String> findLanguageByIdVerString(@Param("id") Long id);

    Member findMemberByUsername(String username);

}
