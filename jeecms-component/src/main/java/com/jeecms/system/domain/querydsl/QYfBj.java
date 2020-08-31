package com.jeecms.system.domain.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.jeecms.system.domain.YfBj;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QYfBj is a Querydsl query type for YfBj
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QYfBj extends EntityPathBase<YfBj> {

    private static final long serialVersionUID = 2112094114L;

    public static final QYfBj yfBj = new QYfBj("yfBj");

    public final com.jeecms.common.base.domain.querydsl.QAbstractDomain _super = new com.jeecms.common.base.domain.querydsl.QAbstractDomain(this);

    public final StringPath bjmc = createString("bjmc");

    //inherited
    public final DateTimePath<java.util.Date> createTime = _super.createTime;

    //inherited
    public final StringPath createUser = _super.createUser;

    //inherited
    public final BooleanPath hasDeleted = _super.hasDeleted;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nd = createString("nd");

    //inherited
    public final DateTimePath<java.util.Date> updateTime = _super.updateTime;

    //inherited
    public final StringPath updateUser = _super.updateUser;

    public final StringPath xd = createString("xd");

    public QYfBj(String variable) {
        super(YfBj.class, forVariable(variable));
    }

    public QYfBj(Path<? extends YfBj> path) {
        super(path.getType(), path.getMetadata());
    }

    public QYfBj(PathMetadata metadata) {
        super(YfBj.class, metadata);
    }

}

