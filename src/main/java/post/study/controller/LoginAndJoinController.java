package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import post.study.dto.MemberDto;
import post.study.entity.Member;
import post.study.service.FieldLanguageService;
import post.study.service.MemberService;
import post.study.service.ProjectMemberService;
import post.study.service.ProjectService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginAndJoinController {
    private final MemberService memberService;
    private final FieldLanguageService fieldLanguageService;
    private final ProjectMemberService projectMemberService;
    private final ProjectService projectService;

    @GetMapping("/")
    public String firstMain(){
        return "first_main";
    }
    @GetMapping("/main_post")
    public String main(HttpSession session, Model model) {
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        if (memberDto == null) {
            model.addAttribute("member", null);
        } else {
            model.addAttribute("member", memberDto);
        }

        return "main_post";
    }

    @GetMapping("/login")
    public String login() {
        return "sign-in/login";
    }

    @PostMapping("/login/judge")
    public String judge(HttpSession session, MemberDto memberDto, Model model) {
        Boolean aBoolean = memberService.loginValidate(memberDto);

        if (aBoolean.equals(false)) {
            model.addAttribute("msg", "존재하지 않는 회원입니다.");
            model.addAttribute("url", "back");
        } else {
            Member findMember = memberService.findMember(memberDto.getEmailId());
            session.setAttribute("member", memberService.memberToDto(findMember));
            model.addAttribute("msg", "로그인 되었습니다.");
            model.addAttribute("url", "/");

        }
        return "popup";
    }

    @GetMapping("/join")
    public String join(Model model) {
        List<String> fieldList = fieldLanguageService.fieldList();
        List<String> languageList = fieldLanguageService.languageList();

        model.addAttribute("fList", fieldList);
        model.addAttribute("lList", languageList);
        return "sign-in/join";
    }

    @PostMapping("/join")
    public String save(MemberDto memberDto, String language, String field, Model model) {
        if (memberService.join(memberDto, language, field) == null) {
            model.addAttribute("msg", "아이디 혹은 닉네임 중복입니다.");
            model.addAttribute("url", "back");
        } else {
            model.addAttribute("msg", "회원가입 되었습니다.");
            model.addAttribute("url", "/");
        }
        return "popup";
    }

}
