package post.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import post.study.dto.MemberDto;
import post.study.dto.QuestionDto;
import post.study.entity.Member;
import post.study.entity.Question;
import post.study.service.MemberService;
import post.study.service.QuestionService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;


    @GetMapping("/question")
    public String post(HttpSession session, @RequestParam(required = false, defaultValue = "0", value = "page") int page, Model model) {
        Member member = (Member) session.getAttribute("member");
        Page<Question> questionPage = questionService.getQuestionList(page);
        int realTotalPage = questionPage.getTotalPages();
        int totalPage = realTotalPage - 1; //0부터 시작하기 때문에
        List<Question> content = questionPage.getContent();
        System.out.println("totalPage = " + totalPage);

        int displayPage = 5;

        int startNum = 0;

        //startPage
        int startPage = (page / displayPage) * displayPage;

        //endPage

        int endPage = (page / displayPage) * displayPage + 4;

        //pageLine
        int pageLine = page / displayPage;

        int totalPageLine=totalPage/displayPage;
        //페이지 라인 : 0~displayPage를 한 페이지 라인을 정의

        //이전 페이지
        model.addAttribute("prevPage", (pageLine-1)*displayPage);
        //다음 페이지
        model.addAttribute("nextPage", (pageLine+1)*displayPage);

        //페이지 리스트

        System.out.println(endPage);
        if (totalPage < endPage) {
            endPage = totalPage;
        }
        System.out.println("startNum = " + startNum);
        model.addAttribute("username", member.getUsername());
        model.addAttribute("qList", questionPage.getContent());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", page);
        model.addAttribute("pageLine",pageLine);
        model.addAttribute("displayPage", displayPage);
        model.addAttribute("totalPageLine",totalPageLine);
        System.out.println("page = " + page);
        return "question/post";
    }

    @GetMapping("/question-write")
    public String write() {
        return "question/write";
    }

    @PostMapping("/question-write")
    public String save(HttpSession session, String title, String content,Model model) {
        System.out.println("----------");

        Member member = (Member) session.getAttribute("member");
        if (title== null || content== null) {
            model.addAttribute("msg", "빈칸을 채워주세요");
            model.addAttribute("url", "/question-write");
        } else {
            questionService.save(member.getId(), title,content);
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