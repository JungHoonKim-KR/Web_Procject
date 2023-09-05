package post.study.service;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import post.study.config.S3Config;
import post.study.dto.MemberDto;
import post.study.dto.ProjectDto;
import post.study.entity.*;
import post.study.repository.FieldProjectRepository;
import post.study.repository.LanguageProjectRepository;
import post.study.repository.ProjectFile_ImgRepository;
import post.study.repository.ProjectRepository;
import post.study.service.FieldLanguageService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final FieldLanguageService fieldLanguageService;
    private final ProjectRepository projectRepository;
    private final LanguageProjectRepository languageProjectRepository;
    private final FieldProjectRepository fieldProjectRepository;
    private final ProjectFile_ImgRepository projectFileImgRepository;
    private final S3Config s3Config;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;




    @Builder
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
    }
    @Builder
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
        String uuid = String.valueOf(UUID.randomUUID());

        InputStream inputStream = file.getInputStream();
        File localFile = File.createTempFile(uuid, fileExtension);

        FileUtils.copyInputStreamToFile(inputStream, localFile);
//        File localFile = new File(localPath); 로컬 전용
//        file.transferTo(localFile);

        s3Config.amazonS3Client().putObject(new PutObjectRequest(bucket, uuid+fileExtension, localFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        String fileUrl = s3Config.amazonS3Client().getUrl(bucket, uuid+fileExtension).toString();

        return fileUrl;
    }

    @Builder
    public void createFile(List<MultipartFile> fileList, Long projectId) throws IOException {
        String fileOriginalName;
        String fileExtension;

        String uuid;
        for(MultipartFile f:fileList) {
            if(f!=null) {
                fileOriginalName = f.getOriginalFilename();
                fileExtension = f.getOriginalFilename().substring(fileOriginalName.lastIndexOf("."), fileOriginalName.length());
                uuid = String.valueOf(UUID.randomUUID());

                InputStream inputStream = f.getInputStream();
                File localFile = File.createTempFile(uuid, fileExtension);
                FileUtils.copyInputStreamToFile(inputStream, localFile);
//                File localFile = new File(localPath); 로컬 전용
//                f.transferTo(localFile);

                s3Config.amazonS3Client().putObject(new PutObjectRequest(bucket, uuid+fileExtension, localFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                String fileUrl = s3Config.amazonS3Client().getUrl(bucket, uuid+fileExtension).toString();


                ProjectFile_Img file_db = new ProjectFile_Img().builder()
                        .projectId(projectId)
                        .filename(uuid.toString())
                        .fileUrl(fileUrl)
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
            project.setProjectLeader(memberDto.getUsername());
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
