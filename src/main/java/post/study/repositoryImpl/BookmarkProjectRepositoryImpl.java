package post.study.repositoryImpl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import post.study.entity.Project;
import post.study.repositoryCustom.BookmarkProjectRepositoryCustom;

import java.util.List;

import static post.study.entity.QBookmarkProject.bookmarkProject;

public class BookmarkProjectRepositoryImpl implements BookmarkProjectRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BookmarkProjectRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Long> findBookmarkProject(Long memberId, List<Project> projectList) {
        QueryResults<Long> longQueryResults = queryFactory.select(bookmarkProject.project.id)
                .from(bookmarkProject)
                .where(bookmarkProject.member.id.eq(memberId).and(bookmarkProject.project.in(projectList)))
                .orderBy(bookmarkProject.projectCreateTime.desc())
                .fetchResults();

        List<Long>bookmarkList=longQueryResults.getResults();
        return bookmarkList;
    }
}
