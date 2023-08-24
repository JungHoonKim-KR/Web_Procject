package post.study.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApplicant is a Querydsl query type for Applicant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplicant extends EntityPathBase<Applicant> {

    private static final long serialVersionUID = 169768234L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplicant applicant = new QApplicant("applicant");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final QProject project;

    public QApplicant(String variable) {
        this(Applicant.class, forVariable(variable), INITS);
    }

    public QApplicant(Path<? extends Applicant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApplicant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApplicant(PathMetadata metadata, PathInits inits) {
        this(Applicant.class, metadata, inits);
    }

    public QApplicant(Class<? extends Applicant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

