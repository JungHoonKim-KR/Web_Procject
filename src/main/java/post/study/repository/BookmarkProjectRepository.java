package post.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ResponseBody;
import post.study.entity.BookmarkProject;

import org.springframework.transaction.annotation.Transactional;
import post.study.repositoryCustom.BookmarkProjectRepositoryCustom;

import java.util.List;

@ResponseBody
public interface BookmarkProjectRepository extends JpaRepository<BookmarkProject, Long>, BookmarkProjectRepositoryCustom {

    @Modifying
    @Transactional
    @Query("delete from BookmarkProject b where b.member.id=:memberId and b.project.id=:projectId")
    void deleteBookmarkProject(@Param("memberId") Long memberId, @Param("projectId") Long projectId);
    List<BookmarkProject> findBookmarkProjectByMemberIdOrderByProjectCreateTime(Long id);
    @Query("select b from BookmarkProject b where b.member.id=:memberId and b.project.id=:projectId")
    BookmarkProject updateBookmarkProject(@Param("memberId")Long memberId, @Param("projectId")Long projectID);

}
