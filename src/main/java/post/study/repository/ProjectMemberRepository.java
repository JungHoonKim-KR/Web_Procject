package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import post.study.entity.Project;
import post.study.entity.ProjectMember;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember,Long> {
    @Query("select p from ProjectMember p where p.project.id=:projectId and p.member.id=:memberId")
    ProjectMember findProjectByMember_id(@Param("projectId")Long projectId,@Param("memberId") Long memberId);
    List<ProjectMember> findProjectListByMember_id(Long id);
    List<ProjectMember> findMember_idByProject_id(Long id);
    @Query("select m from ProjectMember m where m.member.id=:id")
    List<ProjectMember> findprojectMemberList(@Param("id")Long id);

}
