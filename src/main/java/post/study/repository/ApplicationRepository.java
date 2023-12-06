package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import post.study.entity.Applicant;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Applicant,Long> {
    List<Applicant> findAllByProjectId(Long projectId);

    @Query("select a.member from Applicant a where a.project.id=:projectId and a.member.id=:memberId")
    Boolean findApplicantByProjectId(@Param("projectId") Long projectId ,@Param("memberId") Long memberId);

    @Modifying
    @Transactional
    @Query("delete from Applicant a where a.project.id=:projectId and a.member.id=:memberId")
    void deleteApplicant(@Param("projectId") Long projectId ,@Param("memberId") Long memberId);

}
