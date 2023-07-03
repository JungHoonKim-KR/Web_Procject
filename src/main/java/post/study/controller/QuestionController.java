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


    @GetMapping("/post")
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
        return "post/post";
    }

    @GetMapping("/write")
    public String write() {
        return "post/write";
    }

    @PostMapping("/write")
    public String save(HttpSession session, QuestionDto questionDto, Model model) {
        Member member = (Member) session.getAttribute("member");
        System.out.println("questionDto = " + questionDto);
        if (questionDto.getTitle() == null || questionDto.getContent() == null) {
            model.addAttribute("msg", "빈칸을 채워주세요");
            model.addAttribute("url", "/write");
        } else {

            questionService.save(member.getId(), questionDto);
            model.addAttribute("msg", "작성 완료");
            model.addAttribute("url", "/post");
        }

        return "popup";
    }

    @GetMapping("/content")
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
        return "post/content";
    }

    @GetMapping("/update")
    public String update(Long id, Model model) {
        QuestionDto questionDto = questionService.findQuestion(id);

        model.addAttribute("id",questionDto.getId());
        model.addAttribute("title", questionDto.getTitle());
        model.addAttribute("content", questionDto.getContent());
        return "post/update";
    }

    @PostMapping("/update")
    public String update2(Long id,String title, String content, Model model) {
        QuestionDto questionDto = questionService.findQuestion(id);
        questionDto.setTitle(title);
        questionDto.setContent(content);
        questionService.update(questionDto);
        model.addAttribute("msg", "작성 완료");
        model.addAttribute("url", "/post");
        return "popup";

    }

    @GetMapping("/delete")
    public String delete(HttpSession session, Model model) {
        questionService.delete((Long) session.getAttribute("questionId"));
        model.addAttribute("msg", "글이 삭제되었습니다.");
        model.addAttribute("url", "/post");

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
