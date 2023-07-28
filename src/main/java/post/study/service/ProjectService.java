package post.study.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import post.study.dto.ProjectDto;
import post.study.entity.*;
import post.study.norm.field;
import post.study.repository.FieldProjectRepository;
import post.study.repository.LanguageMemberRepository;
import post.study.repository.LanguageProjectRepository;
import post.study.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final LanguageProjectRepository languageProjectRepository;
    private final FieldProjectRepository fieldProjectRepository;

    public Boolean search(ProjectDto projectDto) {
        Project project = projectRepository.findByProjectName(projectDto.getProjectName());
        if (project == null)
            return false;
        else return true;
    }

    public Project create(ProjectDto projectDto,String language,String field, Member member) {
        if (search(projectDto) == false) {
            Project project = new Project();
            project.setProjectName(projectDto.getProjectName());
            project.setProjectLeader(member.getUsername());
            project.setScale(projectDto.getScale());
            project.setIntroduction(projectDto.getIntroduction());
            project.setImg(projectDto.getImg());

            if(language!=null) {
                String[] languageList = language.split(",");
                for (String s : languageList) {
                    Language_Project l = new Language_Project(s);
                    project.addLanguage(l);
                    languageProjectRepository.save(l);

                }
            }

            if(field!=null) {
                String[] fieldList = field.split(",");
                for (String s : fieldList) {
                    Field_Project f = new Field_Project(s);
                    project.addField(f);
                    fieldProjectRepository.save(f);
                }
            }
            projectRepository.save(project);
            //임시. project.addProjectMember를 save하지 않음
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
                String[] languageList = language.split(",");
                languageProjectRepository.deleteLanguage_ProjectByProjectId(findProject.getId());
                for (String s : languageList) {
                    Language_Project l = new Language_Project(s);
                    findProject.addLanguage(l);
                    languageProjectRepository.save(l);

                }
            }

            if(field!=null) {
                String[] fieldList = field.split(",");
                fieldProjectRepository.deleteFeild_ProjectByProjectId(findProject.getId());
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
        List<Language_Project> allById = languageProjectRepository.findAllById(project.getId());
        return allById;
    }

    public List<Field_Project> findAllField(Project project){
        List<Field_Project> allById = fieldProjectRepository.findAllById(project.getId());
        return allById;
    }



}
