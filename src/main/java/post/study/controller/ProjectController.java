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
    private final FieldLanguageService fieldLanguageService;
    private final ProjectRepository projectRepository;
    private final QuestionRepository questionRepository;
    private final MemberService memberService;
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;
    private final BookmarkProjectService BookmarkProjectService;

    @GetMapping("/project")
    public String project(HttpSession session, Model model, @PageableDefault(size = 8, sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable) {
        String type = "ALL";
        PagingService paging = new PagingService(fieldLanguageService, projectRepository, questionRepository, projectService);
        paging.setProjectPaging(null, null, type, pageable);
        if (paging.getProjectList() == null) {

            type = "NONE";
        }

        //로그인 정보
        if (session.getAttribute("member") != null) {
            Member testmember = (Member) session.getAttribute("member");
            Member member = memberService.findMember(testmember.getEmailId());
            //북마크 여부
            List<String> bookmarkImg = new ArrayList<>();
            if (type != "NONE")
                bookmarkImg = BookmarkProjectService.bookmarkImg(member, paging.getProjectList());
            model.addAttribute("username", member.getUsername());
            model.addAttribute("bList", bookmarkImg);

        }


        model.addAttribute("type", type);
        model.addAttribute("pList", paging.getProjectList());
        model.addAttribute("prevPage", paging.getPrevPage());
        model.addAttribute("nextPage", paging.getNextPage());
        model.addAttribute("startPage", paging.getStartPage());
        model.addAttribute("endPage", paging.getEndPage());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("totalPage", paging.getTotalPage());
        model.addAttribute("pageLine", paging.getPageLine());
        model.addAttribute("totalPageLine", paging.getTotalPageLine());

        return "project-post/project_bulletin";
    }

    @GetMapping("/projectSearch")
    public String projectSearch(HttpSession session, String language, String field, Model model, @PageableDefault(size = 8, sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable) {
        String type = "SEARCH";

        if(session.getAttribute("field")==null && field==null)
            session.setAttribute("field",null);
        else if(field!=null)
            session.setAttribute("field",field);

        if(session.getAttribute("language")==null && language==null)
            session.setAttribute("language",null);
        else if(language!=null)
            session.setAttribute("language",language);
        System.out.println("필듸"+field);
        System.out.println("언어: "+language);

        String fields= (String) session.getAttribute("field");
        String languages= (String) session.getAttribute("language");


        PagingService paging = new PagingService(fieldLanguageService, projectRepository, questionRepository, projectService);
        paging.setProjectPaging(fields, languages, type, pageable);

        if (paging.getProjectList() == null) {

            type = "NONE";

        }
        if (session.getAttribute("member") != null) {
            Member testmember = (Member) session.getAttribute("member");
            Member member = memberService.findMember(testmember.getEmailId());
            model.addAttribute("username", member.getUsername());

            //북마크 여부
            if (paging.getProjectList() != null) {
                List<String> bookmarkImg = BookmarkProjectService.bookmarkImg(member, paging.getProjectList());
                model.addAttribute("bList", bookmarkImg);
            }

        }
        List<String> fieldList = fieldLanguageService.getFieldList(fields);
        model.addAttribute("fList",fieldList);
        model.addAttribute("searchField",fieldList);

        List<String> languageList = fieldLanguageService.getLanguageList(languages);
        model.addAttribute("lList",languageList);
        model.addAttribute("searchLanguage",languageList);



        System.out.println(paging.getProjectList());

        model.addAttribute("type", type);
        model.addAttribute("pList", paging.getProjectList());
        model.addAttribute("prevPage", paging.getPrevPage());
        model.addAttribute("nextPage", paging.getNextPage());
        model.addAttribute("startPage", paging.getStartPage());
        model.addAttribute("endPage", paging.getEndPage());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("totalPage", paging.getTotalPage());
        model.addAttribute("pageLine", paging.getPageLine());
        model.addAttribute("totalPageLine", paging.getTotalPageLine());

        return "project-post/project_bulletin";

    }

    //프로젝트 생성
    @GetMapping("/project-create")
    public String projectCreate(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
            model.addAttribute("url", "back");
            return "popup";
        } else {

            List<String> fieldList = fieldLanguageService.fieldList();
            List<String> languageList = fieldLanguageService.languageList();

            model.addAttribute("lList", languageList);
            model.addAttribute("fList", fieldList);
            return "project-post/create";
        }
    }

    @PostMapping("/project-create")
    public String projectCreateJudge(HttpSession session, ProjectDto projectDto, String language, String field, Model model) {
        Member member = (Member) session.getAttribute("member");

        Project project;
        //프로젝트 생성 실패
        if ((project = projectService.create(projectDto, language, field, member)) == null) {
            model.addAttribute("msg", "이미 존재하는 프로젝트 입니다.");
            model.addAttribute("url", "back");
        } else {
            //생성 후 멤버 설정
            projectMemberService.joinProjectMember(project, member);
            model.addAttribute("msg", "프로젝트가 생성되었습니다.");
            model.addAttribute("url", "/project?page=0");

        }

        return "popup";
    }

    @GetMapping("/project-bookmark")
    @ResponseBody
    public Boolean projectBookmark(HttpSession session, Long projectId, Model model) {
        Member member = (Member) session.getAttribute("member");
        Project project = projectService.findProject(projectId);

        Boolean result = BookmarkProjectService.updateBookmarkProject(member, project);

        return result;
    }

    @GetMapping("/project-introduce")
    public String projectIntroduce(String projectName, Model model) {
        Project project = projectService.findProject(projectName);
        model.addAttribute("project", project);
        return "project-post/project";
    }


}
