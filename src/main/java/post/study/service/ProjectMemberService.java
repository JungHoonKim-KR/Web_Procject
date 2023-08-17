package post.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.study.dto.MemberDto;
import post.study.dto.ProjectDto;
import post.study.entity.Applicant;
import post.study.entity.Member;
import post.study.entity.Project;
import post.study.entity.ProjectMember;
import post.study.norm.field;
import post.study.norm.language;
import post.study.repository.ApplicationRepository;
import post.study.repository.MemberRepository;
import post.study.repository.ProjectMemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectMemberService {
    private final ApplicationRepository applicationRepository;
    private final ProjectService projectService;
    private final MemberService memberService;
    private final ProjectMemberRepository projectMemberRepository;
    private final MemberRepository memberRepository;


    public void joinProjectMember(ProjectDto projectDto, MemberDto memberDto) {
        Member member = memberService.findMember(memberDto.getEmailId());
        Project project = projectService.findProject(projectDto.getId());
//        Project project = projectService.projectToEntity(projectDto);
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProjectMember(member, project);
        projectMemberRepository.save(projectMember);
    }

    public void deleteApplicant(ProjectDto projectDto, MemberDto memberDto){
        Member member = memberService.findMember(memberDto.getEmailId());
        applicationRepository.deleteApplicant(projectDto.getId(),member.getId());
    }

    public void applyMemberToProject(ProjectDto projectDto,MemberDto memberDto){
        Member member = memberRepository.findByemailId(memberDto.getEmailId());
        Project project = projectService.projectToEntity(projectDto);

        Applicant applicant = new Applicant();
        applicant.setApplicant(member,project);
        applicationRepository.save(applicant);

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

    public List<Member> find() {
        String s = "C,JAVA";
        List<String> list = new ArrayList<>();
        String arr[] = s.split(",");
        for (String c : arr) {
            list.add(c);
        }
        return memberRepository.findMember(list);
    }

    public List<Applicant> findApplicant(ProjectDto projectDto){
       return applicationRepository.findAllByProjectId(projectDto.getId());
    }

}
