package post.study.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.study.dto.MemberDto;
import post.study.entity.*;
import post.study.repository.FieldMemberRepository;
import post.study.repository.LanguageMemberRepository;
import post.study.repository.MemberRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final FieldLanguageService fieldLanguageService;
    private final MemberRepository memberRepository;
    private final LanguageMemberRepository languageMemberRepository;
    private final FieldMemberRepository fieldMemberRepository;

    public MemberDto memberToDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .emailId(member.getEmailId())
                .username(member.getUsername())
                .password(member.getPassword())
                .age(member.getAge())
                .build();
    }
    public Member memberToEntity(MemberDto memberDto){
        return Member.builder()
                .id(memberDto.getId())
                .emailId(memberDto.getEmailId())
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .age(memberDto.getAge())
                .build();
    }
    public Boolean loginValidateByemailId(MemberDto memberDto, String password) {

        Member findMember = memberRepository.findByemailId(memberDto.getEmailId());
        if (findMember == null)
            return false;
        if (!findMember.getPassword().equals(password)) {
            return false;
        }

        return true;
    }

    //회원의 아이디를 이용해 패스워드가 알맞는지 판단
    public Boolean validateByPassword(String memberPassword, String password) {
        if (memberPassword != password)
            return false;

        return true;
    }
    /**
     회원가입
     */
    public Member join(MemberDto memberDto,String language, String field){
        if(joinValidateByEmailId(memberDto.getEmailId())==false)
            return null;
        else return save(memberDto,language,field);
    }
    public Boolean joinValidateByEmailId(String emailId){
        Member findMember = memberRepository.findByemailId(emailId);
        if(findMember==null)
            return true;

        else return false;
    }
    public Member findMember(String emailId) {
        Member member = memberRepository.findByemailId(emailId);
        return member;
    }

    public Member save(MemberDto memberDto, String language, String field){

        Member member=new Member();
        member.setEmailId(memberDto.getEmailId());
        member.setUsername(memberDto.getUsername());
        member.setPassword(memberDto.getPassword());
        member.setAge(memberDto.getAge());
        List<String> fieldList = fieldLanguageService.getFieldList(field);
        List<String> languageList = fieldLanguageService.getLanguageList(language);

        for(String s: languageList){
            Language_Member l = new Language_Member(s);
            member.addLanguage(l);
            languageMemberRepository.save(l);
        }

        for(String s: fieldList){
            Field_Member f = new Field_Member(s);
            member.addField(f);
            fieldMemberRepository.save(f);
        }

        memberRepository.save(member);
        return member;

    }

    public Member profileUpdate(MemberDto memberDto, String language, String field) {
        Member member = memberRepository.findByemailId(memberDto.getEmailId());
        member.setPassword(memberDto.getPassword());
        member.setUsername(memberDto.getUsername());
        member.setAge(memberDto.getAge());


        if (language != null) {
            List<String> languageList = fieldLanguageService.getLanguageList(language);
            memberRepository.deleteLanguage_MemberByMemberId(member.getId());
            for (String s : languageList) {
                Language_Member l = new Language_Member(s);
                member.addLanguage(l);
                languageMemberRepository.save(l);

            }
        }

        if (field != null) {
            List<String> fieldList = fieldLanguageService.getFieldList(field);
            memberRepository.deleteFeild_MemberByMemberId(member.getId());
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
