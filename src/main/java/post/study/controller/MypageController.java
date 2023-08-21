package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import post.study.dto.MemberDto;
import post.study.dto.ProjectDto;
import post.study.entity.*;
import post.study.service.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MypageController {
    private final FieldLanguageService fieldLanguageService;
    private final MemberService memberService;
    private final ProjectMemberService projectMemberService;
    private final ProjectService projectService;
    private final BookmarkProjectService bookmarkProjectService;

    @GetMapping("/mypage-profile")
    public String profile(HttpSession session, Model model) {
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        if (memberDto == null) {
            model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
            model.addAttribute("url", "back");
            return "popup";
        }
        List<String> fieldList = projectMemberService.fieldList();
        List<String> languageList = projectMemberService.languageList();
        model.addAttribute("emailId", memberDto.getEmailId());
        model.addAttribute("password", memberDto.getPassword());
        model.addAttribute("username", memberDto.getUsername());
        model.addAttribute("fList", fieldList);
        model.addAttribute("lList", languageList);

        return "mypage/profile";
    }

    @PostMapping("/mypage-profile")
    public String profileUpdate(MemberDto memberDto, String language, String field, Model model) {

        memberService.profileUpdate(memberDto, language, field);
        model.addAttribute("msg", "변경되었습니다.");
        model.addAttribute("url", "/");
        return "popup";
    }

    @GetMapping("/mypage-projectList")
    public String projectList(HttpSession session, Model model) {
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        Member member = memberService.memberToEntity(memberDto);
        List<ProjectMember> myProjectList = projectMemberService.findMyProjectList(member);
        model.addAttribute("pList", myProjectList);
        return "mypage/projectList";

    }

    @GetMapping("/mypage-project")
    public String project(String projectName, Model model) {
        Project project = projectService.findProject(projectName);
        ProjectDto projectDto = projectService.projectToDto(project);
        List<Applicant> applicantList = projectMemberService.findApplicant(projectDto);
        List<String> fieldList = fieldLanguageService.fieldList();
        List<String> languageList = fieldLanguageService.languageList();
        model.addAttribute("project", projectDto);
        model.addAttribute("fList", fieldList);
        model.addAttribute("lList", languageList);

        return "mypage/project";

    }

    @PostMapping("/mypage-project")
    public String project(ProjectDto projectDto, String language, String field, Model model) {
        Project project = projectService.update(projectDto, language, field);

        if (project == null) {
            model.addAttribute("msg", "프로젝트 명이 중복됩니다.");
            model.addAttribute("url", "back");
        } else {
            model.addAttribute("msg", "변경이 완료되었씁니다.");
            model.addAttribute("url", "/mypage-projectList");
        }
        return "popup";
    }

    @GetMapping("/mypage-bookmarkProjectList")
    public String bookmarProjectkList(HttpSession session, Model model) {
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        List<BookmarkProject> bookmarkList = bookmarkProjectService.findBookmarkList(memberDto);
        model.addAttribute("bList", bookmarkList);
        return "mypage/bookmarkProjectList";
    }

    @GetMapping("/mypage-project/apply")
    public String applicant(ProjectDto projectDto, Model model) {
        List<Applicant> applicant = projectMemberService.findApplicant(projectDto);
        model.addAttribute("projectDto", projectDto);
        model.addAttribute("applicantList", applicant);
        return "mypage/applicant";
    }

    @GetMapping("/mypage-project/approve")
    @ResponseBody
    public String approving(String value,ProjectDto projectDto, MemberDto memberDto, Model model) {
        if(value.equals("승인")) {
            projectMemberService.joinProjectMember(projectDto, memberDto);
            System.out.println("value: "+value);

        }
        projectMemberService.deleteApplicant(projectDto, memberDto);
        System.out.println("value: "+value);
        System.out.println(value.equals("승인"));
        System.out.println("projectDto = " + projectDto);
        System.out.println("memberDto = " + memberDto);
        return value;

    }

//    @PostMapping("/mypage-project/reject")
//    public String rejecting(ProjectDto projectDto, MemberDto memberDto, Model model) {
//        projectMemberService.deleteApplicant(projectDto, memberDto);
//        model.addAttribute("msg", "거부하였습니다.");
//        model.addAttribute("url", "/");
//
//        return "popup";
//    }

}
