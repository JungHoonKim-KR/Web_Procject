package post.study.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QField_Project is a Querydsl query type for Field_Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QField_Project extends EntityPathBase<Field_Project> {

    private static final long serialVersionUID = 1235587804L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QField_Project field_Project = new QField_Project("field_Project");

    public final StringPath field = createString("field");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProject project;

    public QField_Project(String variable) {
        this(Field_Project.class, forVariable(variable), INITS);
    }

    public QField_Project(Path<? extends Field_Project> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QField_Project(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QField_Project(PathMetadata metadata, PathInits inits) {
        this(Field_Project.class, metadata, inits);
    }

    public QField_Project(Class<? extends Field_Project> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

