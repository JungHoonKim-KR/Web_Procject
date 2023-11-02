package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import post.study.dto.MemberDto;
import post.study.dto.ProjectDto;
import post.study.entity.*;
import post.study.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
    private final FieldLanguageService fieldLanguageService;
    private final MemberService memberService;
    private final ProjectMemberService projectMemberService;
    private final ProjectService projectService;
    private final BookmarkProjectService bookmarkProjectService;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        MemberDto memberDto = memberService.memberToDto(member);
        if (member == null) {
            model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
            model.addAttribute("url", "back");
            return "popup";
        }
        List<String> myLanguage = memberService.findMyLanguageString(member);
        List<String> myField = memberService.findMyFieldString(member);

        List<String> fieldList = fieldLanguageService.fieldList();
        List<String> languageList = fieldLanguageService.languageList();


        model.addAttribute("member",memberDto);
        model.addAttribute("myFList",myField);
        model.addAttribute("myLList",myLanguage);
        model.addAttribute("fList",fieldList);
        model.addAttribute("lList",languageList);

        return "mypage/profile";
    }

    @PostMapping("/profile")
    public String profileUpdate(HttpSession session,MemberDto memberDto, String language, String field, MultipartFile imgFile,  Model model) throws IOException {
        if(imgFile.getOriginalFilename()!="") {
            String mainImg = projectService.createMainImg(imgFile);
            memberService.profileUpdate(memberDto, language, field, mainImg);
            Member member = (Member) session.getAttribute("member");
            member.setProfileImg(mainImg);
            session.setAttribute("member", member);
        }
        else{
            memberService.profileUpdate(memberDto,language,field,null);
        }

        model.addAttribute("msg", "변경되었습니다.");
        model.addAttribute("url", "/");
        return "popup";
    }

    @GetMapping("/projectList")
    public String projectList(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        List<ProjectMember> myProjectList = projectMemberService.findMyProjectList(member);
        model.addAttribute("member",member);
        model.addAttribute("pList", myProjectList);
        return "mypage/projectList";

    }

    @GetMapping("/project")
    public String project(String projectName, Model model) {
        Project project = projectService.findProject(projectName);
        List<ProjectFile_Img> projectImg = projectService.findProjectImg(project.getId());
        ProjectDto projectDto = projectService.projectToDto(project);
        List<Applicant> applicantList = projectMemberService.findApplicant(projectDto);
        List<String> fieldList = fieldLanguageService.fieldList();
        List<String> languageList = fieldLanguageService.languageList();
        model.addAttribute("project", projectDto);
        model.addAttribute("fList", fieldList);
        model.addAttribute("lList", languageList);
        model.addAttribute("imgList", projectImg);

        return "mypage/project";

    }

    @PostMapping("/project")
    public String project(ProjectDto projectDto, String language, String field, Model model) {
        Project project = projectService.update(projectDto, language, field);

        if (project == null) {
            model.addAttribute("msg", "프로젝트 명이 중복됩니다.");
            model.addAttribute("url", "back");
        } else {
            model.addAttribute("msg", "변경이 완료되었씁니다.");
            model.addAttribute("url", "/mypage/projectList");
        }
        return "popup";
    }

    @GetMapping("/bookmarkProjectList")
    public String bookmarProjectkList(HttpSession session, Model model) {
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        List<BookmarkProject> bookmarkList = bookmarkProjectService.findBookmarkList(memberDto);
        model.addAttribute("member",memberDto);
        model.addAttribute("bList", bookmarkList);
        return "mypage/bookmarkProjectList";
    }

    @GetMapping("/project/apply")
    public String applicant(ProjectDto projectDto, Model model) {
        List<Applicant> applicant = projectMemberService.findApplicant(projectDto);
        model.addAttribute("projectDto", projectDto);
        model.addAttribute("applicantList", applicant);
        return "mypage/applicant";
    }

    @GetMapping("/project/approve")
    @ResponseBody
    public String approving(String value, ProjectDto projectDto, MemberDto memberDto, Model model) {
        if (value.equals("승인")) {
            projectMemberService.joinProjectMember(projectDto, memberDto);
        }
        projectMemberService.deleteApplicant(projectDto, memberDto);
        return value;

    }


}
