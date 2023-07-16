package post.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import post.study.dto.ProjectDto;
import post.study.entity.Member;
import post.study.entity.Project;
import post.study.norm.Category;
import post.study.service.ProjectMemberService;
import post.study.service.ProjectService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    @GetMapping("/project")
    public String project(HttpSession session , Model model){
        //임시 리스트
        List<Project> projectList = projectService.findAllProject();

        //paging: 한 페이지당 9개의 게시물 설정

        if(session.getAttribute("member")!=null) {
            Member member = (Member) session.getAttribute("member");

            System.out.println(member.getUsername());
            model.addAttribute("username",member.getUsername());
        }
        model.addAttribute("pList",projectList);



        return "project-post/post";
    }
//프로젝트 생성
    @GetMapping("/project-create")
    public String projectCreate(HttpSession session, Model model){
        ArrayList<String> categoryList=new ArrayList<>();
        for(Category c:Category.values()){
            categoryList.add(c.name());
        }
        Member member= (Member) session.getAttribute("member");
        model.addAttribute("cList",categoryList);
        return"project-post/create";
    }

    @PostMapping("/project-create")
    public String projectCreateJudge(HttpSession session, ProjectDto projectDto, String category, Model model){
        Member member = (Member) session.getAttribute("member");
        if(projectService.create(projectDto,member)==null){
            model.addAttribute("msg","이미 존재하는 프로젝트 입니다.");
            model.addAttribute("url","back");
        }
        else{
            model.addAttribute("msg","프로젝트가 생성되었습니다.");
            model.addAttribute("url","/project");
        System.out.println("projectName = " + projectDto);
        System.out.println("category = " + category);
        }

        return "popup";
    }

    @GetMapping("/project-refer")
    public String refer(HttpSession session , Model model){
        Member member = (Member) session.getAttribute("member");
        List<String> projectMembers = projectMemberService.findMyProject(member);
        for(String projectMember:projectMembers){
            System.out.println(projectMember);
        }
        model.addAttribute("username",member.getUsername());

        return "project-post/post";
    }
}
