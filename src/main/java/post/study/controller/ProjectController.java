package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import post.study.Paging;
import post.study.dto.ProjectDto;
import post.study.entity.*;
import post.study.norm.field;
import post.study.norm.language;
import post.study.repository.ProjectRepository;
import post.study.repository.QuestionRepository;
import post.study.service.*;

import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final QuestionRepository questionRepository;
    private final MemberService memberService;
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;
    private final BookmarkProjectService BookmarkProjectService;
    private final ProjectRepository projectRepository;
    @GetMapping("/project")
    public String project(HttpSession session , @RequestParam(required = false,defaultValue = "0",value = "page")int page, Model model){
        Paging paging = new Paging(projectRepository,projectService,questionRepository);
        paging.setProjectPaging(page);

        //paging: 한 페이지당 9개의 게시물 설정
//        Page<Project> projectList = pagingService.getProjectList(page);
//        for(Project p: projectList){
//            List<Language_Project> allLanguage = projectService.findAllLanguage(p);
//            p.setLanguageList(allLanguage);
//
//            List<Field_Project> allField = projectService.findAllField(p);
//            p.setFieldList(allField);
//        }
        List<String> languageList = projectMemberService.languageList();
        List<String> fieldList = projectMemberService.fieldList();

        //로그인 정보
        if(session.getAttribute("member")!=null) {
            Member testmember = (Member) session.getAttribute("member");
            Member member = memberService.findMember(testmember.getEmailId());
            //북마크 여부
            List<String> bookmarkImg = BookmarkProjectService.bookmarkImg(member);

            model.addAttribute("username",member.getUsername());
            model.addAttribute("bList",bookmarkImg);

        }
        model.addAttribute("lList", languageList);
        model.addAttribute("fList", fieldList);
        model.addAttribute("pList",paging.getProjectList());
        //이전 페이지

        model.addAttribute("prevPage", paging.getPrevPage());
        //다음 페이지
        model.addAttribute("nextPage", paging.getNextPage());
        model.addAttribute("startPage",paging.getStartPage());
        model.addAttribute("endPage",paging.getEndPage());
        model.addAttribute("page",page);
        model.addAttribute("totalPage",paging.getTotalPage());
        model.addAttribute("pageLine",paging.getPageLine());
        model.addAttribute("totalPageLine",paging.getTotalPageLine());



        return "project-post/post";
    }

//    @PostMapping("/project")
//    public String projectSearch(HttpSession session,String language, String field,Model model){
//
//    }
    //프로젝트 생성
    @GetMapping("/project-create")
    public String projectCreate(HttpSession session, Model model){
        Member member = (Member) session.getAttribute("member");
        if(member==null){
            model.addAttribute("msg","로그인이 필요한 서비스입니다.");
            model.addAttribute("url","back");
            return "popup";
        }
        else {
            ArrayList<String> languageList=new ArrayList<>();
            ArrayList<String> fieldList=new ArrayList<>();
            for (language l : language.values()) {
                languageList.add(l.name());
            }
            for(field f: field.values()){
                fieldList.add(f.name());
            }

            model.addAttribute("lList", languageList);
            model.addAttribute("fList",fieldList);
            return "project-post/create";
        }
    }

    @PostMapping("/project-create")
    public String projectCreateJudge(HttpSession session, ProjectDto projectDto, String language,String field, Model model){
        Member member = (Member) session.getAttribute("member");

        Project project;
        if((project=projectService.create(projectDto,language,field,member))==null){
            model.addAttribute("msg","이미 존재하는 프로젝트 입니다.");
            model.addAttribute("url","back");
        }
        else{
            projectMemberService.joinProjectMember(project,member);
            model.addAttribute("msg","프로젝트가 생성되었습니다.");
            model.addAttribute("url","/project?page=0");

        }

        return "popup";
    }

    @GetMapping("/project-bookmark")
    @ResponseBody
    public Boolean projectBookmark(HttpSession session,Long projectId,Model model){
        Member member = (Member) session.getAttribute("member");
        Project project = projectService.findProject(projectId);

        Boolean result = BookmarkProjectService.updateBookmarkProject(member, project);

        return result;
    }

    @GetMapping("/project-introduce")
    public String projectIntroduce(String projectName,Model model){
        Project project = projectService.findProject(projectName);
        System.out.println("프로젝트명: "+projectName);
        model.addAttribute("project",project);
        return"project-post/project";
    }


}
