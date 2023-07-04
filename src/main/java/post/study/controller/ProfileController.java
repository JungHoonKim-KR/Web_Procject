package post.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import post.study.entity.Member;
import post.study.service.MemberService;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final MemberService memberService;
    @GetMapping("/profile")
    public String profile(String emailId, Model model){
        Member member = memberService.findMember(emailId);
        model.addAttribute("emailId",emailId);
        model.addAttribute("pw",member.getPassword());
        model.addAttribute("age",member.getAge());

        return "profile";
    }

    @PostMapping("/profile-update")
    public String profileUpdate(String emailId,String changeDto,Model model){
        memberService.profileUpdate();

        return"popup";
    }
}
