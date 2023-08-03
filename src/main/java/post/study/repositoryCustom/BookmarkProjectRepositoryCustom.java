package post.study.repositoryCustom;

import post.study.entity.Project;

import java.util.List;

public interface BookmarkProjectRepositoryCustom {
    List<Long>findBookmarkProject(Long memberId, List<Project> projectList);
}
