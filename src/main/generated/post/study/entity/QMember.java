package post.study.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 305472882L;

    public static final QMember member = new QMember("member1");

    public final ListPath<Applicant, QApplicant> applicantList = this.<Applicant, QApplicant>createList("applicantList", Applicant.class, QApplicant.class, PathInits.DIRECT2);

    public final ListPath<BookmarkProject, QBookmarkProject> bookmarkProjectList = this.<BookmarkProject, QBookmarkProject>createList("bookmarkProjectList", BookmarkProject.class, QBookmarkProject.class, PathInits.DIRECT2);

    public final ListPath<ChatInvitation, QChatInvitation> chatInviteeList = this.<ChatInvitation, QChatInvitation>createList("chatInviteeList", ChatInvitation.class, QChatInvitation.class, PathInits.DIRECT2);

    public final StringPath emailId = createString("emailId");

    public final ListPath<Field_Member, QField_Member> fieldMemberList = this.<Field_Member, QField_Member>createList("fieldMemberList", Field_Member.class, QField_Member.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Language_Member, QLanguage_Member> languageList = this.<Language_Member, QLanguage_Member>createList("languageList", Language_Member.class, QLanguage_Member.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImg = createString("profileImg");

    public final ListPath<ProjectMember, QProjectMember> projectMemberList = this.<ProjectMember, QProjectMember>createList("projectMemberList", ProjectMember.class, QProjectMember.class, PathInits.DIRECT2);

    public final ListPath<Question, QQuestion> questionList = this.<Question, QQuestion>createList("questionList", Question.class, QQuestion.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

