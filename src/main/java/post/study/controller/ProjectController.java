package post.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import post.study.dto.ProjectDto;
import post.study.entity.BookmarkProject;
import post.study.entity.Member;
import post.study.entity.Project;
import post.study.entity.ProjectMember;
import post.study.norm.Category;
import post.study.service.MemberService;
import post.study.service.ProjectMemberService;
import post.study.service.ProjectService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final MemberService memberService;
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    @GetMapping("/project")
    public String project(HttpSession session , @RequestParam(required = false,defaultValue = "0",value = "page")int page,Model model){

        //paging: 한 페이지당 9개의 게시물 설정
        Page<Project> projectList = projectService.getProjectList(page);
        int realTotalPage=projectList.getTotalPages();
        int totalPage=realTotalPage-1;

        int displayPage=5;


        int startPage=page/displayPage*displayPage;
        int endPage=page/displayPage*displayPage+4;
        if(totalPage<endPage)
            endPage=totalPage;


        int pageLine=page/displayPage;
        int totalPageLine=totalPage/displayPage;

        if(session.getAttribute("member")!=null) {
            Member testmember = (Member) session.getAttribute("member");
            Member member = memberService.findMember(testmember.getEmailId());
            for(ProjectMember m:member.getProjectMemberList())
                System.out.println("멤버 "+m.getProject().getProjectName());

            //북마크 여부
            List<String> bookmarkImg = memberService.bookmarkImg(member);

            model.addAttribute("username",member.getUsername());
            model.addAttribute("bList",bookmarkImg);

        }
        model.addAttribute("pList",projectList);
        //이전 페이지
        model.addAttribute("prevPage", (pageLine-1)*displayPage);
        //다음 페이지
        model.addAttribute("nextPage", (pageLine+1)*displayPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("page",page);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("pageLine",pageLine);
        model.addAttribute("totalPageLine",totalPageLine);



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
        Project project;
        if((project=projectService.create(projectDto,member))==null){
            model.addAttribute("msg","이미 존재하는 프로젝트 입니다.");
            model.addAttribute("url","back");
        }
        else{
            projectMemberService.joinProjectMember(project,member);
            model.addAttribute("msg","프로젝트가 생성되었습니다.");
            model.addAttribute("url","/project?page=0");
        System.out.println("projectName = " + projectDto);
        System.out.println("category = " + category);
        }

        return "popup";
    }

    @GetMapping("/project-bookmark")
    @ResponseBody
    public Boolean projectBookmark(HttpSession session,Long projectId,Model model){
        Member member = (Member) session.getAttribute("member");
        Project project = projectService.findProject(projectId);

        Boolean result = memberService.updateBookmarkProject(member, project);
        System.out.println("결과  "+result);

        return result;
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
