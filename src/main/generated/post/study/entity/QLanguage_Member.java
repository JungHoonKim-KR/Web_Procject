package post.study.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLanguage_Member is a Querydsl query type for Language_Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLanguage_Member extends EntityPathBase<Language_Member> {

    private static final long serialVersionUID = 377685417L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLanguage_Member language_Member = new QLanguage_Member("language_Member");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath language = createString("language");

    public final QMember member;

    public QLanguage_Member(String variable) {
        this(Language_Member.class, forVariable(variable), INITS);
    }

    public QLanguage_Member(Path<? extends Language_Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLanguage_Member(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLanguage_Member(PathMetadata metadata, PathInits inits) {
        this(Language_Member.class, metadata, inits);
    }

    public QLanguage_Member(Class<? extends Language_Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

