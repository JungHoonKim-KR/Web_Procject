package post.study.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectFile_Img is a Querydsl query type for ProjectFile_Img
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectFile_Img extends EntityPathBase<ProjectFile_Img> {

    private static final long serialVersionUID = 1453180097L;

    public static final QProjectFile_Img projectFile_Img = new QProjectFile_Img("projectFile_Img");

    public final StringPath fileExtension = createString("fileExtension");

    public final StringPath filename = createString("filename");

    public final StringPath fileOriginName = createString("fileOriginName");

    public final StringPath fileUrl = createString("fileUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> projectId = createNumber("projectId", Long.class);

    public QProjectFile_Img(String variable) {
        super(ProjectFile_Img.class, forVariable(variable));
    }

    public QProjectFile_Img(Path<? extends ProjectFile_Img> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectFile_Img(PathMetadata metadata) {
        super(ProjectFile_Img.class, metadata);
    }

}

