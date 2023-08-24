package post.study.repositoryCustom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import post.study.entity.Project;

import java.util.List;
@Repository
public interface ProjectRepositoryCustom {
    Page<Project> searchProjectList(List<String>fieldList, List<String>languageList, Pageable pageable);
}
