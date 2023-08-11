package post.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import post.study.dto.MemberDto;
import post.study.dto.ProjectDto;
import post.study.entity.BookmarkProject;
import post.study.entity.Member;
import post.study.entity.Project;
import post.study.repository.BookmarkProjectRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkProjectService {
    private final ProjectService projectService;
    private final BookmarkProjectRepository bookmarkProjectRepository;
    private final MemberService memberService;

    public Boolean updateBookmarkProject(MemberDto memberDto, ProjectDto projectDto) {
        Member member = memberService.findMember(memberDto.getEmailId());
        Project project = projectService.findProject(projectDto.getProjectName());
        //북마크 되어 있는지 조회
        BookmarkProject findProject = bookmarkProjectRepository.updateBookmarkProject(member.getId(), project.getId());
        if (findProject == null) {
            BookmarkProject bookmarkProject = new BookmarkProject();
            bookmarkProject.setBookmarkProject(member, project);
            bookmarkProjectRepository.save(bookmarkProject);
            return true;
        } else {
            bookmarkProjectRepository.deleteBookmarkProject(member.getId(), project.getId());
            return false;
        }

    }


    public List<String> bookmarkImg(MemberDto memberDto, Page<Project> projectList) {

        List<String> bookmarkImg = new ArrayList<>();
        List<Long> bookmarkProject = bookmarkProjectRepository.findBookmarkProject(memberDto.getId(), projectList.getContent());
        for(Long id:bookmarkProject){
            System.out.println("번회 "+id);
        }
        if(!bookmarkProject.isEmpty()) {
            int bookmarkIndex = 0;
            for (Project p : projectList) {
                if (p.getId() .equals(bookmarkProject.get(bookmarkIndex))) {
                    bookmarkImg.add("./images/하트모양(빨강).jpg");
                    bookmarkIndex++;
                    if(bookmarkIndex == bookmarkProject.size())
                        break;

                } else {
                    bookmarkImg.add("./images/하트모양(회색).jpg");

                }
            }

        }
        return bookmarkImg;

    }

    public List<BookmarkProject> findBookmarkList(MemberDto memberDto) {
        List<BookmarkProject> bookmarkProjectByMemberId = bookmarkProjectRepository.findBookmarkProjectByMemberIdOrderByProjectCreateTime(memberDto.getId());
        return bookmarkProjectByMemberId;
    }
}
