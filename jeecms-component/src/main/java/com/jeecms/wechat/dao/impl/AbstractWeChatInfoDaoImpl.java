package com.jeecms.wechat.dao.impl;

import com.jeecms.common.base.dao.BaseDao;
import com.jeecms.wechat.dao.ext.AbstractWeChatInfoDaoExt;
import com.jeecms.wechat.domain.AbstractWeChatInfo;
import com.jeecms.wechat.domain.querydsl.QAbstractWeChatInfo;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

/**
 * AbstractWeChatToken
 *
 * @author: qqwang
 * @date: 2018年4月16日 上午11:05:40
 * @Copyright: 江西金磊科技发展有限公司 All rights reserved. Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。 JpaRepository Repository
 */
public class AbstractWeChatInfoDaoImpl extends BaseDao<AbstractWeChatInfo> implements AbstractWeChatInfoDaoExt {

    @Override
    public List<AbstractWeChatInfo> getList(Integer userId, Integer siteId, Short type) {
        QAbstractWeChatInfo qAbstractWeChatInfo = QAbstractWeChatInfo.abstractWeChatInfo;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qAbstractWeChatInfo.hasDeleted.eq(false));
        builder.and(qAbstractWeChatInfo.siteId.eq(siteId));
        builder.and(qAbstractWeChatInfo.type.eq(type));
        builder.and(qAbstractWeChatInfo.users.any().id.eq(userId));
        return super.getJpaQueryFactory().selectFrom(qAbstractWeChatInfo).where(builder).fetch();
    }
}
