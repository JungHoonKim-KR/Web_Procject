package post.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import post.study.dto.MemberDto;
import post.study.entity.Member;
import post.study.service.LoginAndJoinService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginAndJoinController {
    private final LoginAndJoinService memberService;


    @GetMapping("/")
    public String login() {
////        Member member = new Member();
////        member.setEmailId("kik12321@1234");
////        member.setPassword("kik@0811012");
////        member.setAge(24);
////        member.setUsername("kim");
////        memberRepository.save(member);
        return "sign-in/login";
    }

    @PostMapping("/question-judge")
    public String judge(HttpSession session, MemberDto memberDto, Model model) {
        Boolean aBoolean = memberService.loginValidateByemailId(memberDto, memberDto.getPassword());
        if (aBoolean.equals(false)) {
            model.addAttribute("msg", "존재하지 않는 회원입니다.");
            model.addAttribute("url", "back");
        } else {
            Member findMember = memberService.findMember(memberDto.getEmailId());
            session.setAttribute("member",findMember);
            model.addAttribute("msg", "로그인 되었습니다.");
            model.addAttribute("url", "/question");
        }

        return "popup";
    }

    @GetMapping("/join")
    public String join(){
        return"sign-in/join";
    }

    @PostMapping("/join")
    public String save(MemberDto memberDto,Model model){
        if(memberService.join(memberDto)==null){
            model.addAttribute("msg","이미 가입된 회원입니다.");
            model.addAttribute("url","back");
        }
        else {
            System.out.println("memberDto = " + memberDto);
            model.addAttribute("msg","회원가입 되었습니다.");
            model.addAttribute("url","/");
        }

        return"popup";

    }

}