package post.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import post.study.dto.MemberDto;
import post.study.dto.ProjectDto;
import post.study.entity.Member;
import post.study.entity.Project;
import post.study.entity.ProjectMember;
import post.study.norm.field;
import post.study.norm.language;
import post.study.repository.ProjectMemberRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectMemberService {
    private final MemberService memberService;
    private final ProjectMemberRepository projectMemberRepository;

    public void joinProjectMember(Project project, Member tempMember) {
        Member member = memberService.findMember(tempMember.getEmailId());
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProjectMember(member, project);
        projectMemberRepository.save(projectMember);

    }

    //로컬에서 확인하기 위한 메소드 내일 api용 메소드 만들 예쩡
    public List<String> findMyProject(Member member) {
        List<ProjectMember> allProject = projectMemberRepository.findProject_idByMember_id(member.getId());
        List<String> tempProject = new ArrayList<>();
        for (ProjectMember projectMember : allProject)
            tempProject.add(projectMember.getProject().getProjectName());
        return tempProject;
    }

    public List<ProjectMember> findMyProjectList(Member member) {
        List<ProjectMember> projectIdByMemberId = projectMemberRepository.findProject_idByMember_id(member.getId());
        return projectIdByMemberId;
    }

    public List<String> languageList() {
        ArrayList<String> languageList = new ArrayList<>();
        for (language l : language.values()) {
            languageList.add(l.name());
        }
        return languageList;
    }

    public List<String> fieldList() {
        ArrayList<String> fieldList = new ArrayList<>();

        for (field f : field.values()) {
            fieldList.add(f.name());
        }
        return fieldList;
    }


}
