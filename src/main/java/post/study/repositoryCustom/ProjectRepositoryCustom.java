package post.study.repositoryCustom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import post.study.entity.Project;

import java.util.List;

public interface ProjectRepositoryCustom {
    Page<Project> searchProjectList(List<String>fieldList, List<String>languageList, Pageable pageable);
}
