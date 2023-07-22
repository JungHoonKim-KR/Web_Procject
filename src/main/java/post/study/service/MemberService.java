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
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final ProjectService projectService;
    private final MemberRepository memberRepository;
    private final BookmarkProjectRepository bookmarkProjectRepository;

    public Member findMember(String emailId) {
        Member member = memberRepository.findByemailId(emailId);
        return member;
    }

    public Member profileUpdate(MemberDto memberDto) {
        Member member = memberRepository.findByemailId(memberDto.getEmailId());
        member.setPassword(memberDto.getPassword());
        member.setUsername(memberDto.getUsername());
        member.setAge(memberDto.getAge());
        memberRepository.save(member);
        return member;
    }

    public Boolean updateBookmarkProject(Member tempMember, Project tempProject) {
        Member member = findMember(tempMember.getEmailId());
        Project project = projectService.findProject(tempProject.getId());
        BookmarkProject findProject = bookmarkProjectRepository.findBookmarkProject(member.getId(), project.getId());
        if (findProject == null) {
            BookmarkProject bookmarkProject = new BookmarkProject();
            bookmarkProject.setBookmarkProject(member, project);
            bookmarkProjectRepository.save(bookmarkProject);
            System.out.println("북마크 성공");
            return true;
        } else {
            bookmarkProjectRepository.deleteBookmarkProject(member.getId(), project.getId());
            System.out.println("북마크 취소");
            return false;
        }

    }

    public List<BookmarkProject> bookmarkProjectList() {
        List<BookmarkProject> all = bookmarkProjectRepository.findAll();
        return all;
    }

    public List<String> bookmarkImg(Member member){
        if(member==null)
            return null;
        List<String> bookmarkImg=new ArrayList<>();
        List<Project> projectList = projectService.findAllProject();
        for(int i=projectList.size()-1; i>=0;i--){
            BookmarkProject bookmarkProject = bookmarkProjectRepository.findBookmarkProject(member.getId(), projectList.get(i).getId());

            if(bookmarkProject==null){
                bookmarkImg.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0vuu68IX7IaUcqqSAKbqaNoiKDoDxIiplypOuz5QXBmOqOEJ2AScWwuKidHZbW-LTXKo&usqp=CAU");
            }
            else{
                bookmarkImg.add("https://w7.pngwing.com/pngs/67/599/png-transparent-star-favorite-bookmark-3d-gold-yellow-thumbnail.png");
            }
        }
        return  bookmarkImg;
    }


//    public List<BookmarkProject> bookmarkProjectList(MemberDto memberDto){
//        bookmarkProjectRepository.findAll()
//    }


    //프로젝트 멤버 초대 미구현
}
