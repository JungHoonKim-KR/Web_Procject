package post.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import post.study.dto.MemberDto;
import post.study.entity.*;
import post.study.repository.BookmarkProjectRepository;
import post.study.repository.FieldMemberRepository;
import post.study.repository.LanguageMemberRepository;
import post.study.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final ProjectService projectService;
    private final MemberRepository memberRepository;
    private final BookmarkProjectRepository bookmarkProjectRepository;
    private final LanguageMemberRepository languageMemberRepository;
    private final FieldMemberRepository fieldMemberRepository;

    public Member findMember(String emailId) {
        Member member = memberRepository.findByemailId(emailId);
        return member;
    }

    public Member profileUpdate(MemberDto memberDto, String language, String field) {
        Member member = memberRepository.findByemailId(memberDto.getEmailId());
        member.setPassword(memberDto.getPassword());
        member.setUsername(memberDto.getUsername());
        member.setAge(memberDto.getAge());

        if(language!=null) {
            String[] languageList = language.split(",");
            languageMemberRepository.deleteLanguage_MemberByMemberId(member.getId());
            for (String s : languageList) {
                Language_Member l = new Language_Member(s);
                member.addLanguage(l);
                languageMemberRepository.save(l);

            }
        }

        if(field!=null) {
            String[] fieldList = field.split(",");
            fieldMemberRepository.deleteFeild_MemberByMemberId(member.getId());
            for (String s : fieldList) {
                Field_Member f = new Field_Member(s);
                member.addField(f);
                fieldMemberRepository.save(f);
            }
        }
        memberRepository.save(member);

        return member;
    }

}
