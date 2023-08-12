package post.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.study.dto.MemberDto;
import post.study.dto.QuestionDto;
import post.study.entity.Member;
import post.study.entity.Question;
import post.study.repository.MemberRepository;
import post.study.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    public QuestionDto questionToDto(Question question){
        return  QuestionDto.builder()
                .id(question.getId())
                .username(question.getUsername())
                .title(question.getTitle())
                .content(question.getContent())
                .dateTime(question.getDateTime())
                .member(question.getMember())
                .build();

    }

    public Question questionToEntity(QuestionDto questionDto){
        return Question.builder()
                .id(questionDto.getId())
                .username(questionDto.getUsername())
                .title(questionDto.getTitle())
                .content(questionDto.getContent())
                .dateTime(questionDto.getDateTime())
                .member(questionDto.getMember())
                .build();
    }
    /**
     *
     * 조회
     */
    public List<Question> findAll() {
        List<Question> questions = questionRepository.findAll();
        return questions;
    }


    public Question create(Long memberId, String title, String content) {
        Member member = memberRepository.findById(memberId).get();
        Question question = new Question();
        question.setMember(member);
        question.setUsername(member.getUsername());
        question.setTitle(title);
        question.setContent(content);
        question.setMember(member);
        questionRepository.save(question);

        return question;
    }

    public Question findQuestion(Long id){
        Question question = questionRepository.findById(id).get();
        return question;
    }

    public Question update(QuestionDto question){
        Question findQuestion = questionRepository.findById(question.getId()).get();
        findQuestion.setTitle(question.getTitle());
        findQuestion.setContent(question.getContent());
        questionRepository.save(findQuestion);
        return findQuestion;
    }

    public void delete(Long id){
        questionRepository.deleteById(id);

    }

    /**
     * 페이징
     */

    public Page<Question> getQuestionList(int page){
        PageRequest pageRequest=PageRequest.of(page,4,Sort.by(Sort.Direction.DESC,"id"));
        Page<Question> questionList = questionRepository.findAll(pageRequest);
        return questionList;

    }


}