package post.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import post.study.dto.MemberDto;
import post.study.entity.Member;
import post.study.norm.field;
import post.study.norm.language;
import post.study.service.LoginAndJoinService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class LoginAndJoinController {
    private final LoginAndJoinService memberService;


    @GetMapping("/")
    public String main(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", member.getUsername());
        }

        return "main_post";
    }

    @GetMapping("/login")
    public String login() {
        return "sign-in/login";
    }

    @PostMapping("/login-judge")
    public String judge(HttpSession session, MemberDto memberDto, Model model) {
        Boolean aBoolean = memberService.loginValidateByemailId(memberDto, memberDto.getPassword());
        if (aBoolean.equals(false)) {
            model.addAttribute("msg", "존재하지 않는 회원입니다.");
            model.addAttribute("url", "back");
        } else {
            Member findMember = memberService.findMember(memberDto.getEmailId());
            session.setAttribute("member", findMember);
            model.addAttribute("msg", "로그인 되었습니다.");
            model.addAttribute("url", "/");
        }
        return "popup";
    }

    @GetMapping("/join")
    public String join(Model model) {
        ArrayList<String> languageList = new ArrayList<>();
        ArrayList<String> fieldList = new ArrayList<>();
        for (language l : language.values()) {
            languageList.add(l.name());
        }
        for (field f : field.values()) {
            fieldList.add(f.name());
        }

        model.addAttribute("lList", languageList);
        model.addAttribute("fList", fieldList);
        return "sign-in/join";
    }

    @PostMapping("/join")
    public String save(MemberDto memberDto, String language, String field, Model model) {
        if (memberService.join(memberDto, language, field) == null) {
            model.addAttribute("msg", "이미 가입된 회원입니다.");
            model.addAttribute("url", "back");
        } else {
            System.out.println("memberDto = " + memberDto);
            model.addAttribute("msg", "회원가입 되었습니다.");
            model.addAttribute("url", "/");
        }

        return "popup";

    }

}
