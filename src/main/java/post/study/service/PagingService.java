package post.study.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.study.entity.Field_Project;
import post.study.entity.Language_Project;
import post.study.entity.Project;
import post.study.entity.Question;
import post.study.repository.ProjectRepository;
import post.study.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Getter
@Setter
@Service
public class PagingService {
    private final ProjectRepository projectRepository;
    private final QuestionRepository questionRepository;
    private final ProjectService projectService;
    Page<Project> projectList;
    Page<Question> questionList;
    int totalPage;
    int displayPage = 5;
    int startPage;
    int endPage;
    int prevPage;
    int nextPage;
    int pageLine;
    int totalPageLine;

    public Page<Project> getAllProjectList(Pageable pageable) {
        Page<Project> projectList = projectRepository.findAll(pageable);
        return projectList;

    }

    public Page<Project> getSearchProjectList(List<String> fieldList, List<String> languageList, Pageable pageable) {
        Page<Project> projectList = projectRepository.searchProjectList(fieldList, languageList, pageable);
        return projectList;
    }

    public Page<Question> getAllQuestionList(int page) {
        PageRequest pageRequest = PageRequest.of(page, 6, Sort.by(Sort.Direction.DESC, "id"));
        Page<Question> all = questionRepository.findAll(pageRequest);
        return all;

    }

    public void setProjectPaging(String field, String language, String type, Pageable pageable) {
        List<String> fieldList = new ArrayList<>();
        List<String> languageList = new ArrayList<>();
        if (type.equals("ALL")) {
            projectList = getAllProjectList(pageable);
        }
        else if (type.equals("SEARCH")) {
            if (field!=null) {
                String[] f = field.split(",");

                for (String s : f) {
                    fieldList.add(s);
                }
            }
            if (language!=null) {
                String[] l = language.split(",");

                for (String s : l) {
                    languageList.add(s);
                }
            }
            projectList = getSearchProjectList(fieldList, languageList, pageable);
        }
        if(projectList!=null) {
            totalPage = projectList.getTotalPages() - 1;

            setPaging(pageable.getPageNumber());
            for (Project p : projectList) {
                List<Language_Project> allLanguage = projectService.findAllLanguage(p);
                p.setLanguageList(allLanguage);

                List<Field_Project> allField = projectService.findAllField(p);
                p.setFieldList(allField);
            }
        }
    }


    public void setQuestionPaging(int page) {
        questionList = getAllQuestionList(page);
        totalPage = questionList.getTotalPages() - 1;
        setPaging(page);
    }

    public void setPaging(int page) {
        startPage = page / displayPage * displayPage;
        endPage = page / displayPage * displayPage + 4;
        prevPage = (pageLine - 1) * displayPage;
        nextPage = (pageLine + 1) * displayPage;
        pageLine = page / displayPage;
        totalPageLine = totalPage / displayPage;
        if (totalPage < endPage)
            endPage = totalPage;


    }


}
