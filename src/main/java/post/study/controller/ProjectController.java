package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import post.study.service.PagingService;
import post.study.dto.ProjectDto;
import post.study.entity.*;
import post.study.norm.field;
import post.study.norm.language;
import post.study.repository.ProjectRepository;
import post.study.repository.QuestionRepository;
import post.study.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final QuestionRepository questionRepository;
    private final MemberService memberService;
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;
    private final BookmarkProjectService BookmarkProjectService;
    @GetMapping("/project")
    public String project(HttpSession session , Model model,@PageableDefault(size = 6,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        String type="ALL";
        PagingService paging = new PagingService(projectRepository,questionRepository,projectService);
        paging.setProjectPaging(null,null,type,pageable);

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
        model.addAttribute("type",type);
        model.addAttribute("lList", languageList);
        model.addAttribute("fList", fieldList);
        model.addAttribute("pList",paging.getProjectList());
        model.addAttribute("prevPage", paging.getPrevPage());
        model.addAttribute("nextPage", paging.getNextPage());
        model.addAttribute("startPage",paging.getStartPage());
        model.addAttribute("endPage",paging.getEndPage());
        model.addAttribute("page",pageable.getPageNumber());
        model.addAttribute("totalPage",paging.getTotalPage());
        model.addAttribute("pageLine",paging.getPageLine());
        model.addAttribute("totalPageLine",paging.getTotalPageLine());

        return "project-post/post";
    }

    @GetMapping("/projectSearch")
    public String projectSearch(HttpSession session, String language, String field,Model model,@PageableDefault(size = 6,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        String type="SEARCH";
        String searchField,searchLanguage;
        if(session.getAttribute("field")!=null){
            searchField = (String) session.getAttribute("field");
        }
        else {
            session.setAttribute("field",field);
            searchField = field;
        }
        if(session.getAttribute("language")!=null){
            searchLanguage= (String) session.getAttribute("language");
        }
        else {
            session.setAttribute("language",language);
            searchLanguage = language;
        }
        PagingService paging = new PagingService(projectRepository,questionRepository,projectService);
        paging.setProjectPaging(searchField,searchLanguage,type,pageable);

        List<String> languageList = projectMemberService.languageList();
        List<String> fieldList = projectMemberService.fieldList();
        if(session.getAttribute("member")!=null) {
            Member testmember = (Member) session.getAttribute("member");
            Member member = memberService.findMember(testmember.getEmailId());
            //북마크 여부
            List<String> bookmarkImg = BookmarkProjectService.bookmarkImg(member);

            model.addAttribute("username",member.getUsername());
            model.addAttribute("bList",bookmarkImg);

        }
        if(paging.getProjectList()==null){

            type="NONE";
        }
        model.addAttribute("type",type);
        model.addAttribute("lList", languageList);
        model.addAttribute("fList", fieldList);
        model.addAttribute("pList",paging.getProjectList());
        model.addAttribute("prevPage", paging.getPrevPage());
        model.addAttribute("nextPage", paging.getNextPage());
        model.addAttribute("startPage",paging.getStartPage());
        model.addAttribute("endPage",paging.getEndPage());
        model.addAttribute("page",pageable.getPageNumber());
        model.addAttribute("totalPage",paging.getTotalPage());
        model.addAttribute("pageLine",paging.getPageLine());
        model.addAttribute("totalPageLine",paging.getTotalPageLine());

        return "project-post/post";

    }
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
