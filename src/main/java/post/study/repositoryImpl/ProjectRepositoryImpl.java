package post.study.repositoryImpl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import post.study.entity.*;
import post.study.repositoryCustom.ProjectRepositoryCustom;

import java.util.ArrayList;
import java.util.List;

import static post.study.entity.QField_Project.field_Project;
import static post.study.entity.QLanguage_Project.language_Project;
import static post.study.entity.QProject.project;

public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ProjectRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Project> searchProjectList(List<String> fieldList, List<String> languageList, Pageable pageable) {
        List<Project> projectList = new ArrayList<>();
        int fieldSize = fieldList.size();
        int languageSize = languageList.size();
        long total=0;
        if (fieldSize == 0 && languageSize != 0) {
            QueryResults<Project> projectQueryResults = queryFactory.select(project)
                    .from(language_Project)
                    .where(language_Project.language.in(languageList))
                    .groupBy(project)
                    .having(project.count().eq((long) languageSize))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();
            total = projectQueryResults.getTotal();
            projectList = projectQueryResults.getResults();
        } else if (fieldSize != 0 && languageSize == 0) {
            QueryResults<Project> projectQueryResults = queryFactory.select(project)
                    .from(field_Project)
                    .where(field_Project.field.in(fieldList))
                    .groupBy(project)
                    .having(project.count().eq((long) fieldSize))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();
            total = projectQueryResults.getTotal();
            projectList = projectQueryResults.getResults();
        } else {
            List<Long> language_searchList = queryFactory.select(project.id)
                    .from(language_Project)
                    .where(language_Project.language.in(languageList))
                    .groupBy(project.id)
                    .having(project.count().eq((long) languageSize))
                    .fetch();
            if (language_searchList != null) {
                QueryResults<Project> projectQueryResults = queryFactory
                        .select(project)
                        .from(field_Project)
                        .where(field_Project.field.in(fieldList))
                        .groupBy(project)
                        .having(project.count().eq((long) fieldSize))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetchResults();
                total = projectQueryResults.getTotal();

                List<Project> field_searchList = projectQueryResults.getResults();
                if (field_searchList != null) {
                    for (Long l : language_searchList) {
                        for (Project f : field_searchList) {
                            if (l.equals(f.getId())) {
                                projectList.add(f);
                                break;
                            }

                        }
                    }
                }
            }
        }
        if(projectList.isEmpty())
            return null;

        List<Long> projectNumberList=new ArrayList<>();
        for(Project p:projectList){
            projectNumberList.add(p.getId());
        }

        PageImpl<Project> projects = new PageImpl<>(projectList, pageable, total);

        return projects;
    }
}
//    select m1_0.member_id from language_member l1_0 join member m1_0 on m1_0.member_id=l1_0.member_id
//        where l1_0.language in ('JAVA','C') group by m1_0.member_id having count(*)=2)