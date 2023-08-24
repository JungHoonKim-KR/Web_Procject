package post.study.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLanguage_Project is a Querydsl query type for Language_Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLanguage_Project extends EntityPathBase<Language_Project> {

    private static final long serialVersionUID = 1860121066L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLanguage_Project language_Project = new QLanguage_Project("language_Project");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath language = createString("language");

    public final QProject project;

    public QLanguage_Project(String variable) {
        this(Language_Project.class, forVariable(variable), INITS);
    }

    public QLanguage_Project(Path<? extends Language_Project> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLanguage_Project(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLanguage_Project(PathMetadata metadata, PathInits inits) {
        this(Language_Project.class, metadata, inits);
    }

    public QLanguage_Project(Class<? extends Language_Project> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

