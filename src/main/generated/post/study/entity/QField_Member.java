package post.study.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QField_Member is a Querydsl query type for Field_Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QField_Member extends EntityPathBase<Field_Member> {

    private static final long serialVersionUID = 634633847L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QField_Member field_Member = new QField_Member("field_Member");

    public final StringPath field = createString("field");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QField_Member(String variable) {
        this(Field_Member.class, forVariable(variable), INITS);
    }

    public QField_Member(Path<? extends Field_Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QField_Member(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QField_Member(PathMetadata metadata, PathInits inits) {
        this(Field_Member.class, metadata, inits);
    }

    public QField_Member(Class<? extends Field_Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

