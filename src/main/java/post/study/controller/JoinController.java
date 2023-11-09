package post.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import post.study.entity.Member;
import post.study.service.FieldLanguageService;
import post.study.service.MemberService;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class JoinController {
    private final FieldLanguageService fieldLanguageService;
    private final MemberService memberService;
    @GetMapping("/join")
    public String join(Model model) {
        List<String> fieldList = fieldLanguageService.fieldList();
        List<String> languageList = fieldLanguageService.languageList();

        model.addAttribute("fList", fieldList);
        model.addAttribute("lList", languageList);
        return "sign-in/join";
    }

    @PostMapping("/join")
    public String save(Member member, String language, String field, Model model) {
        if (memberService.join(member, language, field) == null) {
            model.addAttribute("msg", "아이디 혹은 닉네임 중복입니다.");
            model.addAttribute("url", "back");
        } else {
            model.addAttribute("msg", "회원가입 되었습니다.");
            model.addAttribute("url", "/");
        }
        return "popup";
    }
}
