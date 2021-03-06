package com.jeecms.system.domain.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.jeecms.system.domain.ContentType;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContentType is a Querydsl query type for ContentType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContentType extends EntityPathBase<ContentType> {

    private static final long serialVersionUID = -1417734330L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContentType contentType = new QContentType("contentType");

    public final com.jeecms.common.base.domain.querydsl.QAbstractDomain _super = new com.jeecms.common.base.domain.querydsl.QAbstractDomain(this);

    //inherited
    public final DateTimePath<java.util.Date> createTime = _super.createTime;

    //inherited
    public final StringPath createUser = _super.createUser;

    //inherited
    public final BooleanPath hasDeleted = _super.hasDeleted;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> logoId = createNumber("logoId", Integer.class);

    public final com.jeecms.resource.domain.querydsl.QResourcesSpaceData logoResource;

    public final StringPath typeName = createString("typeName");

    //inherited
    public final DateTimePath<java.util.Date> updateTime = _super.updateTime;

    //inherited
    public final StringPath updateUser = _super.updateUser;

    public QContentType(String variable) {
        this(ContentType.class, forVariable(variable), INITS);
    }

    public QContentType(Path<? extends ContentType> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContentType(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContentType(PathMetadata metadata, PathInits inits) {
        this(ContentType.class, metadata, inits);
    }

    public QContentType(Class<? extends ContentType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.logoResource = inits.isInitialized("logoResource") ? new com.jeecms.resource.domain.querydsl.QResourcesSpaceData(forProperty("logoResource"), inits.get("logoResource")) : null;
    }

}

