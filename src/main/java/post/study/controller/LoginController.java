package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import post.study.dto.MemberDto;
import post.study.entity.Member;
import post.study.service.FieldLanguageService;
import post.study.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final MemberService memberService;

    @GetMapping("")
    public String login() {
        return "sign-in/login";
    }

    @PostMapping("/judge")
    public String judge(HttpSession session, MemberDto memberDto, Model model) {
        Member findMember= memberService.loginValidate(memberDto);

        if (findMember==null) {
            model.addAttribute("msg", "존재하지 않는 회원입니다.");
            model.addAttribute("url", "back");
        } else {
            session.setAttribute("member",findMember);
            model.addAttribute("msg", "로그인 되었습니다.");
            model.addAttribute("url", "/");

        }
        return "popup";
    }



}
