package post.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.study.dto.MemberDto;
import post.study.entity.Field_Member;
import post.study.entity.Language_Member;
import post.study.entity.Member;
import post.study.repository.FieldMemberRepository;
import post.study.repository.LanguageMemberRepository;
import post.study.repository.MemberRepository;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class LoginAndJoinService {
    private final FieldLanguageService fieldLanguageService;
    private final FieldMemberRepository fieldMemberRepository;
    private final LanguageMemberRepository languageMemberRepository;
    private final MemberRepository memberRepository;
    /**
     로그인
     회원의 아이디로 조회
     **/

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
    /**
     회원 조회
     **/
    public Member findMember(String emailId){
        Member member = memberRepository.findByemailId(emailId);
        return member;

    }


}
