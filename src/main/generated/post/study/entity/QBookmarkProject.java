package post.study.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookmarkProject is a Querydsl query type for BookmarkProject
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookmarkProject extends EntityPathBase<BookmarkProject> {

    private static final long serialVersionUID = 1218391339L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookmarkProject bookmarkProject = new QBookmarkProject("bookmarkProject");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final QProject project;

    public final DateTimePath<java.time.LocalDateTime> projectCreateTime = createDateTime("projectCreateTime", java.time.LocalDateTime.class);

    public QBookmarkProject(String variable) {
        this(BookmarkProject.class, forVariable(variable), INITS);
    }

    public QBookmarkProject(Path<? extends BookmarkProject> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookmarkProject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookmarkProject(PathMetadata metadata, PathInits inits) {
        this(BookmarkProject.class, metadata, inits);
    }

    public QBookmarkProject(Class<? extends BookmarkProject> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

