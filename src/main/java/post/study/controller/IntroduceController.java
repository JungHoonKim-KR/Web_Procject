package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import post.study.entity.Member;

@Controller
public class IntroduceController {
    @GetMapping("/")
    public String firstMain() {
        return "first_main";
    }

    @GetMapping("/tutorial")
    public String main(HttpSession session, Model model) {
        Member member =(Member) session.getAttribute("member");
        if (member == null) {
            model.addAttribute("member", null);
        } else {
            model.addAttribute("member", member);
        }

        return "tutorial";
    }
}
