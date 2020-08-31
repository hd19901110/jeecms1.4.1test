package com.jeecms.system.domain.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.jeecms.system.domain.YfXnXq;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QXnxq is a Querydsl query type for Xnxq
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QXnxq extends EntityPathBase<YfXnXq> {

    private static final long serialVersionUID = 2112073692L;

    public static final QXnxq xnxq = new QXnxq("xnxq");

    public final com.jeecms.common.base.domain.querydsl.QAbstractDomain _super = new com.jeecms.common.base.domain.querydsl.QAbstractDomain(this);

    //inherited
    public final DateTimePath<java.util.Date> createTime = _super.createTime;

    //inherited
    public final StringPath createUser = _super.createUser;

    //inherited
    public final BooleanPath hasDeleted = _super.hasDeleted;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> updateTime = _super.updateTime;

    //inherited
    public final StringPath updateUser = _super.updateUser;

    public final StringPath xn = createString("xn");

    public final StringPath xq = createString("xq");

    public QXnxq(String variable) {
        super(YfXnXq.class, forVariable(variable));
    }

    public QXnxq(Path<? extends YfXnXq> path) {
        super(path.getType(), path.getMetadata());
    }

    public QXnxq(PathMetadata metadata) {
        super(YfXnXq.class, metadata);
    }

}

