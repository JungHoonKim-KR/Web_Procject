package post.study.service;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import post.study.dto.MemberDto;
import post.study.dto.ProjectDto;
import post.study.entity.*;
import post.study.repository.FieldProjectRepository;
import post.study.repository.LanguageProjectRepository;
import post.study.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Builder
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final FieldLanguageService fieldLanguageService;
    private final ProjectRepository projectRepository;
    private final LanguageProjectRepository languageProjectRepository;
    private final FieldProjectRepository fieldProjectRepository;

    public Project projectToEntity(ProjectDto projectDto){

        return Project.builder()
                .id(projectDto.getId())
                .projectName(projectDto.getProjectName())
                .projectLeader(projectDto.getProjectLeader())
                .scale(projectDto.getScale())
                .img(projectDto.getImg())
                .introduction(projectDto.getIntroduction())
                .createTime(projectDto.getCreationTime())
                .build();
//        Project project = new Project();
//        project.setProjectName(projectDto.getProjectName());
//        project.setProjectLeader(leaderName);
//        project.setScale(projectDto.getScale());
//        project.setIntroduction(projectDto.getIntroduction());
//        project.setImg(projectDto.getImg());
    }
    public ProjectDto projectToDto(Project project){
        return ProjectDto.builder()
                .id(project.getId())
                .projectName(project.getProjectName())
                .projectLeader(project.getProjectLeader())
                .scale(project.getScale())
                .img(project.getImg())
                .introduction(project.getIntroduction())
                .creationTime(project.getCreateTime())
                .build();
    }

    public Boolean search(ProjectDto projectDto) {
        Project project = projectRepository.findByProjectName(projectDto.getProjectName());
        if (project == null)
            return false;
        else return true;
    }

    public Project create(ProjectDto projectDto,String language,String field, MemberDto memberDto) {
        if (search(projectDto) == false) {
            Project project = new Project();
            project.setProjectLeader(projectDto.getProjectLeader());
            project.setProjectName(projectDto.getProjectName());
            project.setScale(projectDto.getScale());
            project.setIntroduction(projectDto.getIntroduction());
            project.setImg(projectDto.getImg());

            if(language!=null) {
                List<String> languageList = fieldLanguageService.getLanguageList(language);
                for (String s : languageList) {
                    Language_Project l = new Language_Project(s);
                    project.addLanguage(l);
                    languageProjectRepository.save(l);

                }
            }

            if(field!=null) {
                List<String> fieldList = fieldLanguageService.getFieldList(field);
                for (String s : fieldList) {
                    Field_Project f = new Field_Project(s);
                    project.addField(f);
                    fieldProjectRepository.save(f);
                }
            }
            projectRepository.save(project);

            return project;
        } else return null;


    }

    public List<Project> findAllProject() {
        List<Project> projectList = projectRepository.findAll();
        return projectList;


    }

    public Project findProject(Long projectId) {
        Project byId = projectRepository.findById(projectId).get();
        return byId;
    }

    public Project findProject(String projectName) {
        Project byProjectName = projectRepository.findByProjectName(projectName);

        return byProjectName;
    }

    public Project update(ProjectDto projectDto,String language,String field) {
        Project project = projectRepository.findByProjectName(projectDto.getProjectName());
        if (projectRepository.findUpdate(project.getProjectName(),project.getId())==null) {
            Project findProject = projectRepository.findByProjectName(projectDto.getProjectName());
            findProject.setProjectName(projectDto.getProjectName());
            findProject.setProjectLeader(projectDto.getProjectLeader());
            //추가 예정

            if(language!=null) {
                List<String> languageList = fieldLanguageService.getLanguageList(language);
                projectRepository.deleteLanguage_ProjectByProjectId(findProject.getId());
                for (String s : languageList) {
                    Language_Project l = new Language_Project(s);
                    findProject.addLanguage(l);
                    languageProjectRepository.save(l);

                }
            }

            if(field!=null) {
                List<String> fieldList = fieldLanguageService.getFieldList(field);
                projectRepository.deleteFeild_ProjectByProjectId(findProject.getId());
                for (String s : fieldList) {
                    Field_Project f = new Field_Project(s);
                    findProject.addField(f);
                    fieldProjectRepository.save(f);
                }
            }
            projectRepository.save(findProject);
           return findProject;
        } else return null;
    }

    public List<Language_Project> findAllLanguage(Project project){
        List<Language_Project> allById = projectRepository.findLanguageById(project.getId());
        return allById;
    }

    public List<Field_Project> findAllField(Project project){
        List<Field_Project> allById = projectRepository.findFieldById(project.getId());
        return allById;
    }



}
