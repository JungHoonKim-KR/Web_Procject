package post.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import post.study.dto.MemberDto;
import post.study.dto.ProjectDto;
import post.study.entity.BookmarkProject;
import post.study.entity.Member;
import post.study.entity.Project;
import post.study.entity.ProjectMember;
import post.study.norm.field;
import post.study.norm.language;
import post.study.service.BookmarkProjectService;
import post.study.service.MemberService;
import post.study.service.ProjectMemberService;
import post.study.service.ProjectService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MypageController {
    private final MemberService memberService;
    private final ProjectMemberService projectMemberService;
    private final ProjectService projectService;
    private final BookmarkProjectService bookmarkProjectService;
    @GetMapping("/mypage-profile")
    public String profile(HttpSession session, Model model){
        Member member = (Member) session.getAttribute("member");
        if(member==null){
            model.addAttribute("msg","로그인이 필요한 서비스입니다.");
            model.addAttribute("url","back");
            return "popup";
        }
        ArrayList<String> languageList = new ArrayList<>();
        ArrayList<String> fieldList = new ArrayList<>();
        for (language l : language.values()) {
            languageList.add(l.name());
        }
        for (field f : field.values()) {
            fieldList.add(f.name());
        }

        model.addAttribute("emailId",member.getEmailId());
        model.addAttribute("password",member.getPassword());
        model.addAttribute("username",member.getUsername());
        model.addAttribute("age",member.getAge());
        model.addAttribute("lList",languageList);
        model.addAttribute("fList",fieldList);

        return "mypage/profile";
    }

    @PostMapping("/mypage-profile")
    public String profileUpdate(MemberDto memberDto,String language, String field, Model model){

            System.out.println("memberDto = " + memberDto);
            memberService.profileUpdate(memberDto,language,field);
            model.addAttribute("msg","변경되었습니다.");
            model.addAttribute("url","/");
        return"popup";
    }
    @GetMapping("/mypage-projectList")
    public String projectList(HttpSession session, Model model){
        Member member = (Member) session.getAttribute("member");
        List<ProjectMember> myProjectList = projectMemberService.findMyProjectList(member);
        model.addAttribute("pList",myProjectList);
        return "mypage/projectList";

    }

    @GetMapping("/mypage-project")
    public String project(String projectName,Model model){
        System.out.println("프로젝트명:"+ projectName);
        Project project = projectService.findProject(projectName);


        ArrayList<String> languageList=new ArrayList<>();
        ArrayList<String> fieldList=new ArrayList<>();
        for (language l : language.values()) {
            languageList.add(l.name());
        }
        for(field f: field.values()){
            fieldList.add(f.name());
        }
        model.addAttribute("projectName",project.getProjectName());
        model.addAttribute("projectLeader",project.getProjectLeader());
        model.addAttribute("lList", languageList);
        model.addAttribute("fList",fieldList);
        return"mypage/project";

    }

    @PostMapping("/mypage-project")
    public String project(ProjectDto projectDto,String language, String field ,Model model){
        Project project = projectService.update(projectDto, language, field);
        System.out.println("projectDto = " + projectDto.getProjectLeader());
        System.out.println("projectDto = " + projectDto.getProjectName());

        if(project==null){
            model.addAttribute("msg","프로젝트 명이 중복됩니다.");
            model.addAttribute("url","back");
        }
        else{
            model.addAttribute("msg","변경이 완료되었씁니다.");
            model.addAttribute("url","/mypage-projectList");
        }
        return"popup";
    }

    @GetMapping("/mypage-bookmarkProjectList")
    public String bookmarProjectkList(HttpSession session, Model model){
        Member member = (Member) session.getAttribute("member");
        List<BookmarkProject> bookmarkList = bookmarkProjectService.findBookmarkList(member);
        model.addAttribute("bList",bookmarkList);
        return "mypage/bookmarkProjectList";
    }



}
