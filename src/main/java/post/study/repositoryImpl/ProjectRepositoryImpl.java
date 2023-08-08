package post.study.repositoryImpl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import post.study.entity.*;
import post.study.repositoryCustom.ProjectRepositoryCustom;

import java.beans.PropertyEditor;
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
        long total = 0;
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
            System.out.println(projectQueryResults.getTotal());
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
            List<Project> tempProjectList = new ArrayList<>();
            List<Long> languageQueryResults = queryFactory.select(project.id)
                    .from(language_Project)
                    .where(language_Project.language.in(languageList))
                    .groupBy(project.id)
                    .having(project.count().eq((long) languageSize))
                    .fetch();
            if (!languageQueryResults.isEmpty()) {
                QueryResults<Project> fieldQueryResults = queryFactory
                        .select(project)
                        .from(field_Project)
                        .where(field_Project.field.in(fieldList))
                        .groupBy(project)
                        .having(project.count().eq((long) fieldSize))
                        .fetchResults();

                List<Project> field_searchList = fieldQueryResults.getResults();
                for(Project p: field_searchList){
                    System.out.println("fff: "+p.getId());
                }
                for(Long l: languageQueryResults)
                    System.out.println("lll: "+l);
                if (!field_searchList.isEmpty()) {
                    for (Long l : languageQueryResults) {
                        for (Project f : field_searchList) {
                            if (l.equals(f.getId())) {
                                System.out.println("fli: "+f.getId());
                                tempProjectList.add(f);
                                break;
                            }

                        }
                    }
                }
            }
            if (tempProjectList.isEmpty())
                return null;
            total=tempProjectList.size();
            int subStart= (int) pageable.getOffset();
            int subEnd= total<((pageable.getPageNumber()+1) * pageable.getPageSize())? (int) total :((pageable.getPageNumber()+1) * pageable.getPageSize());

            System.out.println("pageNum: "+pageable.getPageNumber());

            System.out.println("offset: " + pageable.getOffset());
            System.out.println("limit: " + pageable.getPageSize());
            System.out.println("total: " + total);
            projectList = tempProjectList.subList(subStart,subEnd);


        }



        PageImpl<Project> projects = new PageImpl<>(projectList, pageable, total);
        return projects;
    }
}
//    select m1_0.member_id from language_member l1_0 join member m1_0 on m1_0.member_id=l1_0.member_id
//        where l1_0.language in ('JAVA','C') group by m1_0.member_id having count(*)=2)