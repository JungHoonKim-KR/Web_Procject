package post.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import post.study.dto.MemberDto;
import post.study.dto.ProjectDto;
import post.study.entity.Member;
import post.study.entity.Project;
import post.study.entity.ProjectMember;
import post.study.repository.ProjectMemberRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;

    public void joinProjectMember(Project project,Member member){
        ProjectMember projectMember = new ProjectMember();
        projectMember.setMember(member);
        projectMember.setProject(project);
        projectMemberRepository.save(projectMember);

        project.addProjectMember(projectMember);

    }
    //로컬에서 확인하기 위한 메소드 내일 api용 메소드 만들 예쩡
    public List<String> findMyProject(Member member){
        List<ProjectMember> allProject = projectMemberRepository.findProject_idByMember_id(member.getId());
        List<String> tempProject=new ArrayList<>();
        for(ProjectMember projectMember:allProject)
            tempProject.add(projectMember.getProject().getProjectName());
       return tempProject;
    }

    public List<String> findMyMember(Project project){
        List<ProjectMember> allProject = projectMemberRepository.findProject_idByMember_id(project.getId());
        List<String> tempMEmber=new ArrayList<>();
        for(ProjectMember projectMember:allProject)
            tempMEmber.add(projectMember.getProject().getProjectName());
        return tempMEmber;
    }

}
