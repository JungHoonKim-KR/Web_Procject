package post.study.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = -378467519L;

    public static final QProject project = new QProject("project");

    public final ListPath<Applicant, QApplicant> applicantList = this.<Applicant, QApplicant>createList("applicantList", Applicant.class, QApplicant.class, PathInits.DIRECT2);

    public final ListPath<BookmarkProject, QBookmarkProject> bookmarkProjectList = this.<BookmarkProject, QBookmarkProject>createList("bookmarkProjectList", BookmarkProject.class, QBookmarkProject.class, PathInits.DIRECT2);

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> curHeadcount = createNumber("curHeadcount", Integer.class);

    public final ListPath<Field_Project, QField_Project> fieldList = this.<Field_Project, QField_Project>createList("fieldList", Field_Project.class, QField_Project.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath img = createString("img");

    public final StringPath introduction = createString("introduction");

    public final ListPath<Language_Project, QLanguage_Project> languageList = this.<Language_Project, QLanguage_Project>createList("languageList", Language_Project.class, QLanguage_Project.class, PathInits.DIRECT2);

    public final StringPath projectLeader = createString("projectLeader");

    public final ListPath<ProjectMember, QProjectMember> projectMemberList = this.<ProjectMember, QProjectMember>createList("projectMemberList", ProjectMember.class, QProjectMember.class, PathInits.DIRECT2);

    public final StringPath projectName = createString("projectName");

    public final StringPath scale = createString("scale");

    public final NumberPath<Integer> totalHeadcount = createNumber("totalHeadcount", Integer.class);

    public QProject(String variable) {
        super(Project.class, forVariable(variable));
    }

    public QProject(Path<? extends Project> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProject(PathMetadata metadata) {
        super(Project.class, metadata);
    }

}

