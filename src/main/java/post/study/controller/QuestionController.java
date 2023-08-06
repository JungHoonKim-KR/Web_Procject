package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import post.study.service.FieldLanguageService;
import post.study.service.PagingService;
import post.study.dto.QuestionDto;
import post.study.entity.Member;
import post.study.entity.Question;
import post.study.repository.ProjectRepository;
import post.study.repository.QuestionRepository;
import post.study.service.ProjectService;
import post.study.service.QuestionService;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final FieldLanguageService fieldLanguageService;
    private final ProjectRepository projectRepository;
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    private final ProjectService projectService;

    @GetMapping("/question")
    public String post(HttpSession session, @RequestParam(required = false, defaultValue = "0", value = "page") int page, Model model) {
        PagingService paging = new PagingService(fieldLanguageService,projectRepository,questionRepository,projectService);
        paging.setQuestionPaging(page);
        if(session.getAttribute("member")!=null){
            Member member = (Member) session.getAttribute("member");
            model.addAttribute("username", member.getUsername());
        }
        else{
           model.addAttribute("username",null);

        }
        Page<Question> questionPage = questionService.getQuestionList(page);

        //페이지 라인 : 0~displayPage를 한 페이지 라인을 정의

        //페이지 리스트
        model.addAttribute("qList", paging.getQuestionList());
        model.addAttribute("prevPage", paging.getPrevPage());
        model.addAttribute("nextPage", paging.getNextPage());
        model.addAttribute("startPage",paging.getStartPage());
        model.addAttribute("endPage",paging.getEndPage());
        model.addAttribute("page",page);
        model.addAttribute("totalPage",paging.getTotalPage());
        model.addAttribute("pageLine",paging.getPageLine());
        model.addAttribute("totalPageLine",paging.getTotalPageLine());

        return "question/post";
    }

    @GetMapping("/question-write")
    public String write(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        if(member==null){
            model.addAttribute("msg","로그인이 필요한 서비스입니다.");
            model.addAttribute("url","back");
            return "popup";
        }

        return "question/write";
    }

    @PostMapping("/question-write")
    public String save(HttpSession session, String title, String content,Model model) {
        //question은 dto로 받지 않았음 : 1. 여러가지 방식, 2. 멤버 변수가 적어서
        Member member = (Member) session.getAttribute("member");
        if (title== null || content== null) {
            model.addAttribute("msg", "빈칸을 채워주세요");
            model.addAttribute("url", "/question-write");
        } else {
            questionService.create(member.getId(), title,content);
            model.addAttribute("msg", "작성 완료");
            model.addAttribute("url", "/question");
        }

        return "popup";
    }

    @GetMapping("/question-content")
    public String content(HttpSession session, Long id, Model model) {
        QuestionDto questionDto = questionService.findQuestion(id);

        session.setAttribute("questionId", id);
        Member member = (Member) session.getAttribute("member");
        session.setAttribute("myId", member.getId());
        model.addAttribute("id", questionDto.getId());
        model.addAttribute("title", questionDto.getTitle());
        model.addAttribute("content", questionDto.getContent());
        model.addAttribute("userId",questionDto.getMember().getId());
        model.addAttribute("myId", session.getAttribute("myId"));
        return "question/content";
    }

    @GetMapping("/question-update")
    public String update(Long id, Model model) {
        QuestionDto questionDto = questionService.findQuestion(id);

        model.addAttribute("id",questionDto.getId());
        model.addAttribute("title", questionDto.getTitle());
        model.addAttribute("content", questionDto.getContent());
        return "question/update";
    }

    @PostMapping("/question-update")
    public String update2(Long id,String title, String content, Model model) {
        QuestionDto questionDto = questionService.findQuestion(id);
        questionDto.setTitle(title);
        questionDto.setContent(content);
        questionService.update(questionDto);
        model.addAttribute("msg", "작성 완료");
        model.addAttribute("url", "/question");
        return "popup";

    }

    @GetMapping("/question-delete")
    public String delete(HttpSession session, Model model) {
        questionService.delete((Long) session.getAttribute("questionId"));
        model.addAttribute("msg", "글이 삭제되었습니다.");
        model.addAttribute("url", "/question");

        return "popup";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("msg", "로그아웃 되었습니다.");
        model.addAttribute("url", "/");

        return "popup";

    }
}