package post.study.service;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import post.study.dto.MemberDto;
import post.study.dto.ProjectDto;
import post.study.entity.*;
import post.study.repository.FieldProjectRepository;
import post.study.repository.LanguageProjectRepository;
import post.study.repository.ProjectFile_ImgRepository;
import post.study.repository.ProjectRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Builder
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final FieldLanguageService fieldLanguageService;
    private final ProjectRepository projectRepository;
    private final LanguageProjectRepository languageProjectRepository;
    private final FieldProjectRepository fieldProjectRepository;
    private final ProjectFile_ImgRepository projectFileImgRepository;

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

    public String createMainImg(MultipartFile file) throws IOException {
        if(file==null)
            return null;
        String fileOriginalName = file.getOriginalFilename();
        String fileExtension = file.getOriginalFilename().substring(fileOriginalName.lastIndexOf("."), fileOriginalName.length());
        String uploadPath = "C:\\Users\\PC\\Desktop\\ProjectMember Matching\\study\\src\\main\\resources\\static\\project_mainImg";
        UUID uuid = UUID.randomUUID();
        String realPath=uploadPath + "\\" + uuid.toString() + fileExtension;
        File file_server = new File(realPath);
        file.transferTo(file_server);
        return "/project_mainImg/"+uuid.toString()+fileExtension;
    }

    public void createFile(List<MultipartFile> fileList, Long projectId) throws IOException {
        String fileOriginalName;
        String uploadPath;
        String fileExtension;

        UUID uuid;
        for(MultipartFile f:fileList) {
            if(f!=null) {
                fileOriginalName = f.getOriginalFilename();
                fileExtension = f.getOriginalFilename().substring(fileOriginalName.lastIndexOf("."), fileOriginalName.length());
                uploadPath = "C:\\Users\\PC\\Desktop\\ProjectMember Matching\\study\\src\\main\\resources\\static\\project_img";
                uuid = UUID.randomUUID();
                File file_server = new File(uploadPath + "\\" + uuid.toString() + fileExtension);
                f.transferTo(file_server);

                ProjectFile_Img file_db = new ProjectFile_Img().builder()
                        .projectId(projectId)
                        .filename(uuid.toString())
                        .fileUrl(uploadPath)
                        .fileOriginName(fileOriginalName)
                        .fileExtension(fileExtension)
                        .build();
                projectFileImgRepository.save(file_db);
            }
        }

    }

    public List<ProjectFile_Img>findProjectImg(Long projectId){
       return projectFileImgRepository.findAllByProjectId(projectId);
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
