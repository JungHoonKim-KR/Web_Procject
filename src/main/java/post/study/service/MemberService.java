package post.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public Member profileUpdate(){
        return null;
    }

}
