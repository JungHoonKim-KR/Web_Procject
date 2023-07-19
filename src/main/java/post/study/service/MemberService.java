package post.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import post.study.dto.MemberDto;
import post.study.entity.BookmarkProject;
import post.study.entity.Member;
import post.study.entity.Project;
import post.study.repository.BookmarkProjectRepository;
import post.study.repository.MemberRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final ProjectService projectService;
    private final MemberRepository memberRepository;
    private final BookmarkProjectRepository bookmarkProjectRepository;

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
    public Boolean updateBookmarkProject(Member tempMember,Project tempProject){
        Member member = findMember(tempMember.getEmailId());
        Project project = projectService.findProject(tempProject.getId());
        BookmarkProject findProject = bookmarkProjectRepository.findBookmarkProject(member.getId(),project.getId());
        if(findProject==null){
            BookmarkProject bookmarkProject = new BookmarkProject();
            bookmarkProject.setBookmarkProject(member,project);
            bookmarkProjectRepository.save(bookmarkProject);
            System.out.println("북마크 성공");
            return true;
        }
        else{
            bookmarkProjectRepository.deleteBookmarkProject(member.getId(),project.getId());
            System.out.println("북마크 취소");
            return false;
        }



    }



//    public List<BookmarkProject> bookmarkProjectList(MemberDto memberDto){
//        bookmarkProjectRepository.findAll()
//    }


    //프로젝트 멤버 초대 미구현
}
