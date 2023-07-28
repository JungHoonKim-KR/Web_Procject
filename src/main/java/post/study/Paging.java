package post.study;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import post.study.entity.Field_Project;
import post.study.entity.Language_Project;
import post.study.entity.Project;
import post.study.entity.Question;
import post.study.repository.ProjectRepository;
import post.study.repository.QuestionRepository;
import post.study.service.ProjectService;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Getter@Setter
public class Paging {
    private final ProjectRepository projectRepository;
    private final ProjectService projectService;
    private final QuestionRepository questionRepository;
    Page<Project>projectList;
    Page<Question>questionList;
    int totalPage;
    int displayPage = 5;
    int startPage;
    int endPage;
    int prevPage;
    int nextPage;
    int pageLine;
    int totalPageLine;

    public Page<Project> getProjectList(int page) {
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "id"));
        Page<Project> projectList = projectRepository.findAll(pageRequest);
        return projectList;

    }
    public Page<Question> getQuestionList(int page) {
        PageRequest pageRequest = PageRequest.of(page, 7, Sort.by(Sort.Direction.DESC, "id"));
        Page<Question> all = questionRepository.findAll(pageRequest);
        return all ;

    }
    public void setProjectPaging(int page){
        projectList=getProjectList(page);
        totalPage = projectList.getTotalPages() - 1;

        setPaging(page);
        for(Project p: projectList){
            List<Language_Project> allLanguage = projectService.findAllLanguage(p);
            p.setLanguageList(allLanguage);

            List<Field_Project> allField = projectService.findAllField(p);
            p.setFieldList(allField);
        }
    }


    public void setQuestionPaging(int page){
        questionList = getQuestionList(page);
        totalPage = questionList.getTotalPages() - 1;
        setPaging(page);
    }

    public void setPaging(int page) {
        startPage = page / displayPage * displayPage;
        endPage = page / displayPage * displayPage + 4;
        prevPage=(pageLine-1)*displayPage;
        nextPage=(pageLine+1)*displayPage;
        pageLine = page / displayPage;
        totalPageLine = totalPage / displayPage;
        if (totalPage < endPage)
            endPage = totalPage;


    }


}
