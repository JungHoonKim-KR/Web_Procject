package post.study.repositoryCustom;

import org.springframework.stereotype.Repository;
import post.study.entity.Project;

import java.util.List;
@Repository
public interface BookmarkProjectRepositoryCustom {
    List<Long>findBookmarkProject(Long memberId, List<Project> projectList);
}
