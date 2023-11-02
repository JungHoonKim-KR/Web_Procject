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
import org.springframework.web.bind.annotation.RequestMapping;
import post.study.config.jwt.TokenDto;
import post.study.dto.MemberDto;
import post.study.entity.Member;
import post.study.config.jwt.JwtService;
import post.study.service.FieldLanguageService;
import post.study.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final MemberService memberService;
    private final FieldLanguageService fieldLanguageService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login2(){
        return ResponseEntity.ok().body(jwtService.login("김정훈","1234"));
    }

    @PostMapping("/review")
    public ResponseEntity<String>review(Authentication authentication){
        return ResponseEntity.ok().body(authentication.getName() + " 리뷰 등록 완료");
    }


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
            jwtService.login(findMember.getEmailId(),findMember.getPassword());


            session.setAttribute("member",findMember);
            model.addAttribute("msg", "로그인 되었습니다.");
            model.addAttribute("url", "/");

        }
        return "popup";
    }



}
