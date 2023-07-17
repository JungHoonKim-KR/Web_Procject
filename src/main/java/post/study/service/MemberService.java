package post.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import post.study.dto.MemberDto;
import post.study.entity.Member;
import post.study.repository.MemberRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findMember(String emailId){
        Member member = memberRepository.findByemailId(emailId);
        return member;
    }

    public Member profileUpdate(MemberDto memberDto){
        Member member = memberRepository.findByemailId(memberDto.getEmailId());
        member.setPassword(memberDto.getPassword());
        member.setUsername(memberDto.getUsername());
        member.setAge(memberDto.getAge());
        member.setPosition(memberDto.getPosition());
        memberRepository.save(member);
        return member;
    }



    //프로젝트 멤버 초대 미구현
}
