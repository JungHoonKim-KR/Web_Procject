package post.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import post.study.dto.ProjectDto;
import post.study.entity.Member;
import post.study.entity.Project;
import post.study.entity.Question;
import post.study.repository.ProjectRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Boolean search(ProjectDto projectDto) {
        Project project = projectRepository.findByProjectName(projectDto.getProjectName());
        if (project == null)
            return false;
        else return true;
    }

    public Project create(ProjectDto projectDto, Member member) {
        if (search(projectDto)==false){
            Project project = new Project();
            project.setProjectName(projectDto.getProjectName());
            project.setProjectLeader(projectDto.getProjectLeader());
            project.setScale(projectDto.getScale());
            project.setIntroduction(projectDto.getIntroduction());
            project.setImg(projectDto.getImg());
            projectRepository.save(project);
            //임시. project.addProjectMember를 save하지 않음
            return project;
        }
        else return null;


    }
    public Page<Project> getProjectList(int page){
        PageRequest pageRequest=PageRequest.of(page,5, Sort.by(Sort.Direction.DESC,"id"));
        Page<Project> projectList = projectRepository.findAll(pageRequest);
        return projectList;

    }
    public List<Project> findAllProject(){
        List<Project> projectList = projectRepository.findAll();
        return projectList;


    }

    public Project findProject(Long projectId){
        Project byId = projectRepository.findById(projectId).get();
        return byId;
    }



}
