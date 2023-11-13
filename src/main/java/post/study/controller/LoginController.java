package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import post.study.dto.MemberDto;
import post.study.entity.Member;
import post.study.service.FieldLanguageService;
import post.study.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final MemberService memberService;
//    private final JwtService jwtService;

//    @PostMapping("/login")
//    public ResponseEntity<TokenDto> login2(@RequestBody MemberDto memberDto, HttpSession session){
//        System.out.println(memberDto.getEmailId());
//        System.out.println(memberDto.getPassword());
//        Member findMember= memberService.loginValidate(memberDto);
//        if(findMember != null) {
//
//            System.out.println("member: "+findMember);
//            session.setAttribute("member", findMember);
//        }
//
//            return ResponseEntity.ok().body(jwtService.login(findMember.getEmailId(),findMember.getPassword()));
//    }
//
//    @PostMapping("/review")
//    public ResponseEntity<String>review(Authentication authentication){
//        return ResponseEntity.ok().body(authentication.getName() + " 리뷰 등록 완료 "+ authentication);
//    }
//

    @GetMapping("")
    public String login() {
        return "sign-in/login";
    }

    @PostMapping("/judge")
    public String judge(HttpSession session, MemberDto memberDto, Model model) {
        Member findMember= memberService.loginValidate(memberDto);

        if (findMember.equals(null)) {
            model.addAttribute("msg", "존재하지 않는 회원입니다.");
            model.addAttribute("url", "back");
        } else {
            //토큰 발행
//            jwtService.login(findMember.getEmailId(),findMember.getPassword());


            session.setAttribute("member",findMember);
            model.addAttribute("msg", "로그인 되었습니다.");
            model.addAttribute("url", "/");

        }
        return "popup";
    }



}
