package post.study.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import post.study.dto.MemberDto;
import post.study.entity.Field_Member;
import post.study.entity.Member;
import post.study.entity.ProjectFile_Img;
import post.study.service.MemberService;
import post.study.service.ProjectMemberService;
import post.study.service.ProjectService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LoginAndJoinController {
    private final MemberService memberService;
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
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", memberDto.getUsername());
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
            session.setAttribute("member", memberService.memberToDto(findMember));
//            projectService.createFile(fileList,0L);
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
        System.out.println(language);
        System.out.println(field);
        if (memberService.join(memberDto, language, field) == null) {
            model.addAttribute("msg", "이미 가입된 회원입니다.");
            model.addAttribute("url", "back");
        } else {
            model.addAttribute("msg", "회원가입 되었습니다.");
            model.addAttribute("url", "/");
        }

        return "popup";

    }

}
