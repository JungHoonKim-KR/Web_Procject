package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import post.study.dto.MemberDto;
import post.study.service.PagingService;
import post.study.dto.ProjectDto;
import post.study.entity.*;
import post.study.repository.ProjectRepository;
import post.study.repository.QuestionRepository;
import post.study.service.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final FieldLanguageService fieldLanguageService;
    private final ProjectRepository projectRepository;
    private final QuestionRepository questionRepository;
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;
    private final BookmarkProjectService BookmarkProjectService;

    @GetMapping("")
    public String project(HttpSession session, Model model, @PageableDefault(size = 4, sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable) {
        String type = "ALL";
        PagingService paging = new PagingService(fieldLanguageService, projectRepository, questionRepository, projectService);
        paging.setProjectPaging(null, null, type, pageable);
        if (paging.getProjectList() == null) {
            type = "NONE";
        }
        //로그인 정보
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            //북마크 여부
            List<String> bookmarkImg = new ArrayList<>();
            if (type != "NONE")
                bookmarkImg = BookmarkProjectService.bookmarkImg(member, paging.getProjectList());
            model.addAttribute("member", member);
            model.addAttribute("bList", bookmarkImg);

        }


        model.addAttribute("type", type);
        model.addAttribute("paging", paging);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("pList", paging.getProjectList());

        return "project-post/project_bulletin";
    }

    @GetMapping("/Search")
    public String projectSearch(HttpSession session, String language, String field, Model model, @PageableDefault(size = 4, sort = "createTime", direction = Sort.Direction.ASC) Pageable pageable) throws UnsupportedEncodingException {
        //검색 시 카테고리 선택 필수
        if (field == null && language == null) {
            model.addAttribute("msg", "카테고리를 선택해주세요");
            model.addAttribute("url", "back");
            return "popup";
        }

        String type = "SEARCH";
        PagingService paging = new PagingService(fieldLanguageService, projectRepository, questionRepository, projectService);
        paging.setProjectPaging(field, language, type, pageable);
        if (paging.getProjectList() == null) {
            type = "NONE";
        }
        //로그인 정보
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            //북마크 여부
            List<String> bookmarkImg = new ArrayList<>();
            if (type != "NONE")
                bookmarkImg = BookmarkProjectService.bookmarkImg(member, paging.getProjectList());
            model.addAttribute("member", member);
            model.addAttribute("bList", bookmarkImg);

        }

        // field & language List
        List<String> fieldList = fieldLanguageService.getFieldList(field);
        model.addAttribute("fList", fieldList);
        model.addAttribute("searchField", fieldList);

        List<String> languageList = fieldLanguageService.getLanguageList(language);
        model.addAttribute("lList", languageList);
        model.addAttribute("searchLanguage", languageList);
        model.addAttribute("type", type);
        model.addAttribute("paging", paging);
        model.addAttribute("page", pageable.getPageNumber());

        if (paging.getProjectList() == null||paging.getProjectList().isEmpty())
            model.addAttribute("pList", null);
        else
            model.addAttribute("pList", paging.getProjectList());

        return "project-post/project_bulletin";

    }

    //프로젝트 생성
    @GetMapping("/create")
    public String projectCreate(HttpSession session, Model model) {
        Member memberDto = (Member) session.getAttribute("member");
        if (memberDto == null) {
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

    @PostMapping("/create")
    public String projectCreateJudge(HttpSession session, ProjectDto projectDto, String language, String field,MultipartFile mainFile, MultipartHttpServletRequest files,Model model) throws IOException {
        Member memberDto = (Member) session.getAttribute("member");
        List<MultipartFile> fileList = files.getFiles("files");
        String mainImg = projectService.createMainImg(mainFile);
        projectDto.setImg(mainImg);
        //프로젝트 생성 실패
        Project project;
        if ((project = projectService.create(projectDto, language, field, memberDto)) == null) {
            model.addAttribute("msg", "이미 존재하는 프로젝트 입니다.");
            model.addAttribute("url", "back");
        } else {
            //생성 후 멤버 설정
            projectMemberService.joinProjectMember(projectDto,memberDto);
            projectService.createFile(fileList,project.getId());

            model.addAttribute("msg", "프로젝트가 생성되었습니다.");
            model.addAttribute("url", "/project?page=0");

        }

        return "popup";
    }

    @GetMapping("/bookmark")
    @ResponseBody
    @Transactional
    public Boolean projectBookmark(HttpSession session, Long projectId, Model model) {
        Member member = (Member) session.getAttribute("member");
        Project project = projectService.findProject(projectId);
        Boolean result = BookmarkProjectService.updateBookmarkProject(member, projectService.projectToDto(project));
        System.out.println("결과: "+ result);
        return result;
    }

    @GetMapping("/introduce")
    public String projectIntroduce(HttpSession session, String projectName, Model model) {
        Member memberDto = (Member) session.getAttribute("member");
        if (memberDto != null) {
            model.addAttribute("member", memberDto);
        }
        Project project = projectService.findProject(projectName);
        List<String> fList = projectService.findAllFieldString(project);
        List<String> lList = projectService.findAllLanguageString(project);

        List<ProjectFile_Img> projectImg = projectService.findProjectImg(project.getId());
        ProjectDto projectDto = projectService.projectToDto(project);
        model.addAttribute("project",projectDto);
        model.addAttribute("imgList",projectImg);
        model.addAttribute("fList",fList);
        model.addAttribute("lList",lList);
        return "project-post/project";
    }

    @GetMapping("/apply")
    public String projectApply(HttpSession session, ProjectDto projectDto, Model model) {
        Member memberDto = (Member) session.getAttribute("member");
        if(projectMemberService.applyMemberToProject(projectDto, memberDto)==true) {
            model.addAttribute("msg", "신청 완료");
        }
        else model.addAttribute("msg","이미 가입되어 있습니다.");
        model.addAttribute("url", "back");
        return "popup";
    }



}
