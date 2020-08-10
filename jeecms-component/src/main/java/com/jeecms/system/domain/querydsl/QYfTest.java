package com.jeecms.system.domain.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.jeecms.system.domain.YfTest;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QYfTest is a Querydsl query type for YfTest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QYfTest extends EntityPathBase<YfTest> {

    private static final long serialVersionUID = -1796552340L;

    public static final QYfTest yfTest = new QYfTest("yfTest");

    public final com.jeecms.common.base.domain.querydsl.QAbstractDomain _super = new com.jeecms.common.base.domain.querydsl.QAbstractDomain(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> createTime = _super.createTime;

    //inherited
    public final StringPath createUser = _super.createUser;

    //inherited
    public final BooleanPath hasDeleted = _super.hasDeleted;

    public final StringPath height = createString("height");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath sex = createString("sex");

    //inherited
    public final DateTimePath<java.util.Date> updateTime = _super.updateTime;

    //inherited
    public final StringPath updateUser = _super.updateUser;

    public final StringPath weight = createString("weight");

    public QYfTest(String variable) {
        super(YfTest.class, forVariable(variable));
    }

    public QYfTest(Path<? extends YfTest> path) {
        super(path.getType(), path.getMetadata());
    }

    public QYfTest(PathMetadata metadata) {
        super(YfTest.class, metadata);
    }

}

