package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import post.study.dto.MemberDto;
import post.study.entity.Member;
import post.study.service.LoginAndJoinService;
import post.study.service.ProjectMemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginAndJoinController {
    private final LoginAndJoinService memberService;
    private final ProjectMemberService projectMemberService;


    @GetMapping("/")
    public String main(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", member.getUsername());
        }
        List<Member> members = projectMemberService.find();
        for(Member m : members){
            System.out.println("member : "+ m.getId());
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
        List<String> fieldList = projectMemberService.fieldList();
        List<String> languageList = projectMemberService.languageList();

        model.addAttribute("fList", fieldList);
        model.addAttribute("lList", languageList);
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
