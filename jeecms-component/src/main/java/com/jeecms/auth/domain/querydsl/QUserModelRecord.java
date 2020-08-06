package com.jeecms.auth.domain.querydsl;

import com.jeecms.auth.domain.UserModelRecord;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QUserModelRecord is a Querydsl query type for UserModelRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserModelRecord extends EntityPathBase<UserModelRecord> {

    private static final long serialVersionUID = -458125719L;

    public static final QUserModelRecord userModelRecord = new QUserModelRecord("userModelRecord");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> modelId = createNumber("modelId", Integer.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QUserModelRecord(String variable) {
        super(UserModelRecord.class, forVariable(variable));
    }

    public QUserModelRecord(Path<? extends UserModelRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserModelRecord(PathMetadata metadata) {
        super(UserModelRecord.class, metadata);
    }

}

