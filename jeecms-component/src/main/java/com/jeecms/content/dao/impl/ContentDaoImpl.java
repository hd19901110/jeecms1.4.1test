/**
 * @Copyright: 江西金磊科技发展有限公司  All rights reserved.Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */

package com.jeecms.content.dao.impl;

import com.jeecms.common.base.dao.BaseDao;
import com.jeecms.common.jpa.QuerydslUtils;
import com.jeecms.common.page.Paginable;
import com.jeecms.common.util.MyDateUtils;
import com.jeecms.content.constants.ContentConstant;
import com.jeecms.content.dao.ext.ContentDaoExt;
import com.jeecms.content.domain.Content;
import com.jeecms.content.domain.dto.ContentSearchDto;
import com.jeecms.content.domain.querydsl.QContent;
import com.jeecms.content.domain.querydsl.QContentExt;
import com.jeecms.content.domain.vo.ContentVo;
import com.jeecms.content.service.ContentService;
import com.jeecms.system.domain.querydsl.QContentType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.jeecms.content.constants.ContentConstant.STATUS_PIGEONHOLE;
import static com.jeecms.content.constants.ContentConstant.STATUS_TEMPORARY_STORAGE;

/**
 * 内容扩展查询dao实现
 *
 * @author: tom
 * @date: 2019年5月11日 下午1:49:25
 */
public class ContentDaoImpl extends BaseDao<Content> implements ContentDaoExt {

    @Override
    public Page<Content> getPage(ContentSearchDto dto, Pageable pageable) {
        QContent qContent = QContent.content;
        JPAQuery<Integer> query = getJpaQueryFactory().select(qContent.id)
                .from(qContent);
        BooleanBuilder builder = new BooleanBuilder();
      //  appendOrderTypeQueryBuild(builder, qContent, dto.getOrderType());
        query = appendQuery(query, qContent, builder, dto);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        if (dto.getOrderType() != null && dto.getOrderType() != 0) {
            query.orderBy(orderType(qContent, dto.getOrderType()));
        } else {
            // 归档倒序(使用修改时间)
            query.orderBy(qContent.updateTime.desc());
        }
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        QueryResults<Integer> result = query.fetchResults();
        List<Content> contents = new ArrayList<>(10);
        for (Integer tuple : result.getResults()) {
            //Content vo = new Content();
            //vo = tuple.get(0, Content.class);
            Content vo = contentService.findById(tuple);
            contents.add(vo);
        }
        Page<Content> page = new PageImpl<>(contents, pageable, result.getTotal());
        return page;
    }

    @Override
    public long getCount(ContentSearchDto dto) {
        QContent qContent = QContent.content;
        JPAQuery<Integer> query = getJpaQueryFactory().select(qContent.id)
                .from(qContent);
        BooleanBuilder builder = new BooleanBuilder();
        //query = appendQuery(query, contentChannel.content, builder, dto);
        query = appendQuery(query,qContent, builder, dto);
        return query.fetchCount();
    }

    @Override
    public long getSum(Date beginTime, Date endTime, Integer siteId, Integer status, Integer createType) {
        QContent qContent = QContent.content;
        BooleanBuilder builder = new BooleanBuilder();
        if (beginTime != null) {
            builder.and(qContent.releaseTime.goe(beginTime));
        }
        if (endTime != null) {
            builder.and(qContent.releaseTime.loe(endTime));
        }
        if (siteId != null) {
            builder.and(qContent.siteId.eq(siteId));
        }
        if (status != null) {
            builder.and(qContent.status.eq(status));
        }
        if (createType != null) {
            builder.and(qContent.createType.eq(createType));
        }
        JPAQuery<Content> query = new JPAQuery<Content>(this.em);
        return query.from(qContent).where(builder).fetchCount();
    }

    /**
     * 处理排序
     *
     * @param qContent 内容对象
     * @param type     类型
     * @return
     */
    @SuppressWarnings("rawtypes")
    private OrderSpecifier orderType(QContent qContent, Integer type) {
        switch (type) {
            case ContentConstant.ORDER_TYPE_CREATETIME_DESC:
                return qContent.id.desc();
            case ContentConstant.ORDER_TYPE_CREATETIME_ASC:
                return qContent.id.asc();

            case ContentConstant.ORDER_TYPE_VIEWS_DESC:
                return qContent.views.desc();
            case ContentConstant.ORDER_TYPE_VIEWS_ASC:
                return qContent.views.asc();
            case ContentConstant.ORDER_TYPE_VIEWS_MONTH_DESC:
                return qContent.contentExt.viewsMonth.desc();
            case ContentConstant.ORDER_TYPE_VIEWS_MONTH_ASC:
                return qContent.contentExt.viewsMonth.asc();
            case ContentConstant.ORDER_TYPE_VIEWS_WEEK_DESC:
                return qContent.contentExt.viewsWeek.desc();
            case ContentConstant.ORDER_TYPE_VIEWS_WEEK_ASC:
                return qContent.contentExt.viewsWeek.asc();
            case ContentConstant.ORDER_TYPE_VIEWS_DAY_DESC:
                return qContent.contentExt.viewsDay.desc();
            case ContentConstant.ORDER_TYPE_VIEWS_DAY_ASC:
                return qContent.contentExt.viewsDay.asc();

            case ContentConstant.ORDER_TYPE_COMMENTS_DESC:
                return qContent.comments.desc();
            case ContentConstant.ORDER_TYPE_COMMENTS_ASC:
                return qContent.comments.asc();
            case ContentConstant.ORDER_TYPE_COMMENTS_MONTH_DESC:
                return qContent.contentExt.commentsMonth.desc();
            case ContentConstant.ORDER_TYPE_COMMENTS_MONTH_ASC:
                return qContent.contentExt.commentsMonth.asc();
            case ContentConstant.ORDER_TYPE_COMMENTS_WEEK_DESC:
                return qContent.contentExt.commentsWeek.desc();
            case ContentConstant.ORDER_TYPE_COMMENTS_WEEK_ASC:
                return qContent.contentExt.commentsWeek.asc();
            case ContentConstant.ORDER_TYPE_COMMENTS_DAY_DESC:
                return qContent.contentExt.commentsDay.desc();
            case ContentConstant.ORDER_TYPE_COMMENTS_DAY_ASC:
                return qContent.contentExt.commentsDay.asc();
            case ContentConstant.ORDER_TYPE_UPS_DESC:
                return qContent.ups.desc();
            case ContentConstant.ORDER_TYPE_UPS_ASC:
                return qContent.ups.asc();
            case ContentConstant.ORDER_TYPE_UPS_MONTH_DESC:
                return qContent.contentExt.upsMonth.desc();
            case ContentConstant.ORDER_TYPE_UPS_MONTH_ASC:
                return qContent.contentExt.upsMonth.asc();
            case ContentConstant.ORDER_TYPE_UPS_WEEK_DESC:
                return qContent.contentExt.upsWeek.desc();
            case ContentConstant.ORDER_TYPE_UPS_WEEK_ASC:
                return qContent.contentExt.upsWeek.asc();
            case ContentConstant.ORDER_TYPE_UPS_DAY_DESC:
                return qContent.contentExt.upsDay.desc();
            case ContentConstant.ORDER_TYPE_UPS_DAY_ASC:
                return qContent.contentExt.upsDay.asc();
            case ContentConstant.ORDER_TYPE_RELEASE_TIME_DESC:
                return qContent.releaseTime.desc();
            case ContentConstant.ORDER_TYPE_RELEASE_TIME_ASC:
                return qContent.releaseTime.asc();
            case ContentConstant.ORDER_TYPE_UPDATETIME_DESC:
                return qContent.updateTime.desc();
            default:
                break;
        }
        return qContent.releaseTime.desc();
    }

    private BooleanBuilder appendOrderTypeQueryBuild(BooleanBuilder builder, QContent qContent, Integer type) {
        if (type == null) {
            type = ContentConstant.ORDER_TYPE_ID_DESC;
        }
        switch (type) {
            case ContentConstant.ORDER_TYPE_CREATETIME_DESC:
                builder.and(qContent.id.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_CREATETIME_ASC:
                builder.and(qContent.id.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_VIEWS_DESC:
                builder.and(qContent.views.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_VIEWS_ASC:
                builder.and(qContent.views.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_VIEWS_MONTH_DESC:
                builder.and(qContent.contentExt.viewsMonth.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_VIEWS_MONTH_ASC:
                builder.and(qContent.contentExt.viewsMonth.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_VIEWS_WEEK_DESC:
                builder.and(qContent.contentExt.viewsWeek.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_VIEWS_WEEK_ASC:
                builder.and(qContent.contentExt.viewsWeek.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_VIEWS_DAY_DESC:
                builder.and(qContent.contentExt.viewsDay.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_VIEWS_DAY_ASC:
                builder.and(qContent.contentExt.viewsDay.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_COMMENTS_DESC:
                builder.and(qContent.comments.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_COMMENTS_ASC:
                builder.and(qContent.comments.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_COMMENTS_MONTH_DESC:
                builder.and(qContent.contentExt.commentsMonth.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_COMMENTS_MONTH_ASC:
                builder.and(qContent.contentExt.commentsMonth.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_COMMENTS_WEEK_DESC:
                builder.and(qContent.contentExt.commentsWeek.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_COMMENTS_WEEK_ASC:
                builder.and(qContent.contentExt.commentsWeek.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_COMMENTS_DAY_DESC:
                builder.and(qContent.contentExt.commentsDay.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_COMMENTS_DAY_ASC:
                builder.and(qContent.contentExt.commentsDay.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_UPS_DESC:
                builder.and(qContent.ups.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_UPS_ASC:
                builder.and(qContent.ups.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_UPS_MONTH_DESC:
                builder.and(qContent.contentExt.upsMonth.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_UPS_MONTH_ASC:
                builder.and(qContent.contentExt.upsMonth.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_UPS_WEEK_DESC:
                builder.and(qContent.contentExt.upsWeek.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_UPS_WEEK_ASC:
                builder.and(qContent.contentExt.upsWeek.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_UPS_DAY_DESC:
                builder.and(qContent.contentExt.upsDay.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_UPS_DAY_ASC:
                builder.and(qContent.contentExt.upsDay.gt(-1));
                return builder;
            case ContentConstant.ORDER_TYPE_RELEASE_TIME_DESC:
                builder.and(qContent.releaseTime.isNotNull());
                return builder;
            case ContentConstant.ORDER_TYPE_RELEASE_TIME_ASC:
                builder.and(qContent.releaseTime.isNotNull());
                return builder;
            case ContentConstant.ORDER_TYPE_UPDATETIME_DESC:
                builder.and(qContent.updateTime.loe(Calendar.getInstance().getTime()));
                return builder;
            default:
                break;
        }
        builder.and(qContent.releaseTime.isNotNull());
        return builder;
    }

    @Override
    public List<Content> getList(ContentSearchDto dto, Paginable paginable) {
        List<Content> contents = new ArrayList<Content>(10);
        QContent qContent = QContent.content;
        JPAQuery<Integer> query = getJpaQueryFactory().select(qContent.id)
                .from(qContent);
        BooleanBuilder builder = new BooleanBuilder();
      //  appendOrderTypeQueryBuild(builder, qContent, dto.getOrderType());
        query = appendQuery(query, qContent, builder, dto);
        if (dto.getOrderType() != null && dto.getOrderType() != 0) {
            query.orderBy(orderType(qContent, dto.getOrderType()));
        } else {
            // 默认置顶最前，排序值升序，比重降序
            //query.orderBy(qContent.top.desc()).orderBy(qContent.sortNum.desc());
            query.orderBy(qContent.id.desc());
        }
        List<Integer> tuples = query.fetch();
        for (Integer tuple : tuples) {
            //Content vo = new Content();
            //vo = tuple.get(0, Content.class);
            Content vo = contentService.findById(tuple);
            contents.add(vo);
        }
        return contents;
    }

    private JPAQuery<Integer> appendQuery(JPAQuery<Integer> query, QContent qContent, BooleanBuilder builder, ContentSearchDto dto) {
        // 站点ID
        if (dto.getSiteId() != null) {
            builder.and(qContent.siteId.eq(dto.getSiteId()));
        }
        builder.and(qContent.recycle.eq(false));
        // 过滤删除
        builder.and(qContent.hasDeleted.eq(false));
        // 内容状态
        if (dto.getStatus() != null) {
            if (dto.getStatus().size() == 1) {
                builder.and(qContent.status.eq(dto.getStatus().get(0)));
            } else {
                if (ContentConstant.DEF_QUERY_STATUS.equals(dto.getStatus())) {
                    /**查询所有优化查询默认的这些状态就是查询内容列表*/
                    //builder.and(qContent.status.lt(ContentConstant.STATUS_SMART_AUDIT));
                    builder.and(qContent.pos.eq(ContentConstant.POS_CONTENT_LIST));
                }else if (ContentConstant.AUDIT_QUERY_STATUS.equals(dto.getStatus())) {
                    /**查询智能审核列表优化查询默认的这些状态就是查询内容列表*/
                    //builder.and(qContent.status.lt(ContentConstant.STATUS_SMART_AUDIT));
                    builder.and(qContent.pos.eq(ContentConstant.POS_AUDIT));
                } else {
                    builder.and(qContent.status.in(dto.getStatus()));
                }
            }
        }
        // 创建方式
        if (dto.getCreateType() != null) {
            builder.and(qContent.createType.eq(dto.getCreateType()));
        }
        // 栏目ID
        if (dto.getChannelIds() != null && dto.getChannelIds().length > 0) {
            builder.and(qContent.channelId.in(dto.getChannelIds()));
//            QChannel channel = QChannel.channel;
//            query.innerJoin(qContent.channel,channel);
//            builder.and(contentChannel.channel.lft.between(contentChannel.channel.parent.lft,contentChannel.channel.parent.rgt)
//                    .and(qContent.siteId.eq(contentChannel.channel.parent.siteId))
//                    .and(contentChannel.channel.parent.id.in(dto.getChannelIds())));
        }
        // 如果内容id存在则排除内容id
        if (dto.getContentId() != null) {
            builder.and(qContent.id.ne(dto.getContentId()));
        }
        /**查询字段顺序和前台查询保持一致，利于索引查询*/
        // 关键字类型
        if (dto.getKeyType() != null && StringUtils.isNotEmpty(dto.getKey())) {
            switch (dto.getKeyType()) {
                case ContentConstant.KEY_TYPE_TITLE:
                    // 标题
                    builder.and(qContent.title.like("%" + dto.getKey() + "%"));
                    break;
                case ContentConstant.KEY_TYPE_AUTHOR:
                    // 作者名称
                    builder.and(qContent.contentExt.author.like("%" + dto.getKey() + "%"));
                    break;
                case ContentConstant.KEY_TYPE_SOURCE:
                    // 来源
                    builder.and(qContent.contentExt.contentSource.sourceName
                            .like("%" + dto.getKey() + "%"));
                    break;
                case ContentConstant.KEY_TYPE_DESCRIBE:
                    // 简短标题
                    builder.and(qContent.shortTitle.like("%" + dto.getKey() + "%"));
                    break;
                case ContentConstant.KEY_TYPE_USER:
                    // 创建人
                    builder.and(qContent.createUser.like("%" + dto.getKey() + "%"));
                    break;
                default:
                    break;
            }
        }
        // 内容类型
        if (dto.getContentType() != null) {
            QContentType contentType = QContentType.contentType;
            query.innerJoin(qContent.contentTypes, contentType);
            builder.and(contentType.id.eq(dto.getContentType()));
        }
        // 是否我创建的
        if (dto.getMyself() != null && dto.getMyself()) {
            builder.and(qContent.userId.eq(dto.getUserId()));
        }
        // 重新编辑
        if (dto.getUpdate() != null && dto.getUpdate()) {
            builder.and(qContent.edit.eq(dto.getUpdate()));
        }
        // 起始创建时间
        if (dto.getCreateStartTime() != null && dto.getCreateEndTime() != null) {
            builder.and(qContent.createTime.goe(dto.getCreateStartTime()).and(
                    qContent.createTime.loe(MyDateUtils.getFinallyDate(dto.getCreateEndTime()))));
        }
        // 起始发布时间
        if (dto.getReleaseStartTime() != null && dto.getReleaseEndTime() != null) {
            builder.and(qContent.releaseTime.goe(dto.getReleaseStartTime()).and(
                    qContent.releaseTime.loe(MyDateUtils.getFinallyDate(dto.getReleaseEndTime()))));
        }
        // 归档时间（使用内容对象的updateTime）
        if (dto.getFileStartTime() != null && dto.getFileEndTime() != null) {
            builder.and(qContent.updateTime.goe(dto.getFileStartTime()).and(
                    qContent.updateTime.loe(MyDateUtils.getFinallyDate(dto.getFileEndTime()))));
        }
        // 内容模型
        if (dto.getModelId() != null) {
            builder.and(qContent.modelId.eq(dto.getModelId()));
        }
        // 内容密级
        /**默认值为0 方便索引查询，null不利于查询使用索引*/
        if (dto.getSecret()) {
            if (dto.getContentSecretIds() != null && dto.getContentSecretIds().length != 0) {
                // 给密级做筛选
                builder.and(qContent.contentSecretId.in(dto.getContentSecretIds()));
            } else {
                builder.and(
                        qContent.contentSecretId.in(dto.getContentSecretIds())
                                .or(qContent.contentSecretId.eq(0)));
            }
        }
        // 发文字号-机关代字
        if (dto.getIssueOrg() != null) {
            builder.and(qContent.contentExt.issueOrg.eq(dto.getIssueOrg()));
        }
        // 发文字号-年份
        if (dto.getIssueYear() != null) {
            builder.and(qContent.contentExt.issueYear.eq(dto.getIssueYear()));
        }
        // 发文顺序号
        if (StringUtils.isNotBlank(dto.getIssueNum())) {
            builder.and(qContent.contentExt.issueNum.like("%" + dto.getIssueNum() + "%"));
        }
        if (dto.getStatus() == null) {
            // 默认过滤暂存状态和已归档
            builder.and(qContent.status.ne(STATUS_TEMPORARY_STORAGE))
                    .and(qContent.status.ne(STATUS_PIGEONHOLE));
        }
        return query.where(builder);
    }


    @Override
    public Integer findByTitle(String title, Integer channelId) {
        QContent content = QContent.content;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(content.hasDeleted.eq(false));
        builder.and(content.recycle.eq(false));
        if (title != null) {
            builder.and(content.title.eq(title));
        }
        if (channelId != null) {
            builder.and(content.channelId.eq(channelId));
        }
        return getJpaQueryFactory().select(content).from(content).where(builder).fetch().size();
    }

    @Override
    public Page<ContentVo> getPages(ContentSearchDto dto, Pageable pageable) {
        QContent qContent = QContent.content;
        JPAQuery<Integer> query = getJpaQueryFactory().select(qContent.id)
                .from(qContent);
        BooleanBuilder builder = new BooleanBuilder();
      //  appendOrderTypeQueryBuild(builder, qContent, dto.getOrderType());
        query = appendQuery(query, qContent, builder, dto);
        if (dto.getOrderType() != null && dto.getOrderType() != 0) {
            query.orderBy(orderType(qContent, dto.getOrderType()));
        } else {
            // 默认置顶最前，排序值升序，比重降序，默认10000页默认排序有效，超过则id降序
            query.orderBy(qContent.top.desc()).orderBy(qContent.sortNum.desc());
        }
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        //新增查询缓存
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        List<Integer> result = query.fetch();
        List<ContentVo> contents = new ArrayList<>(10);
        for (Integer id : result) {
            ContentVo vo = new ContentVo();
            //vo.setCmsContent(tuple.get(0, Content.class));
            Content c = contentService.findById(id);
            vo.setCmsContent(c);
            //vo.setId(tuple.get(0, Integer.class));
            vo.setStatus(c.getStatus());
            vo.setCreateType(c.getCreateType());
            vo.setChannelId(c.getChannelId());
            vo.setQuote(false);
            if(c.getOriContentId()!=null){
                vo.setQuote(true);
            }
            contents.add(vo);
        }
        Page<ContentVo> page = new PageImpl<ContentVo>(contents, pageable, query.fetchCount());
        return page;
    }

    @Override
    public long getSum(Integer siteId, Boolean recycle) {
        QContent qContent = QContent.content;
        BooleanBuilder builder = new BooleanBuilder();
        if (siteId != null) {
            builder.and(qContent.siteId.eq(siteId));
        }
        if (recycle != null) {
            builder.and(qContent.recycle.eq(recycle));
        }
        JPAQuery<Content> query = new JPAQuery<Content>(this.em);
        return query.from(qContent).where(builder).fetchCount();
    }

    @Override
    public List<Content> getRecycle(Integer siteId, Boolean recycle) {
        QContent qContent = QContent.content;
        JPAQuery<Content> query = new JPAQuery<Content>(this.em);
        BooleanBuilder builder = new BooleanBuilder();
        if (siteId != null) {
            builder.and(qContent.siteId.eq(siteId));
        }
        if (recycle != null) {
            builder.and(qContent.recycle.eq(recycle));
        }
        return query.from(qContent).where(builder).fetch();
    }

    /**
     * 根据排序值排序移动
     *
     * @param sortNum   排序值
     * @param move      平移值
     * @param direction true在后半段false为前半段
     * @param sort      true排序值之前，false排序值之后
     */
    @Override
    public void updateSortNum(Integer sortNum, Integer move, boolean direction, boolean sort) {
        QContent qContent = QContent.content;
        BooleanBuilder builder = new BooleanBuilder();
        //表示后半段
        if (direction) {
            if (sort) {
                //排序值之前
                builder.and(qContent.sortNum.goe(sortNum));
            } else {
                //排序值之后
                builder.and(qContent.sortNum.gt(sortNum));
            }
            getJpaQueryFactory().update(qContent).set(qContent.sortNum, qContent.sortNum.add(move))
                    .where(builder).execute();
        } else {
            //表示前半段
            if (sort) {
                builder.and(qContent.sortNum.lt(sortNum));
            } else {
                builder.and(qContent.sortNum.loe(sortNum));
            }
            getJpaQueryFactory().update(qContent).set(qContent.sortNum, qContent.sortNum.subtract(move))
                    .where(builder).execute();
        }
    }

    @Override
    public long countByTpl(Integer siteId,String pcTpl,String mobileTpl) {
        QContent content = QContent.content;
        QContentExt ext = QContentExt.contentExt;
        JPAQuery<Content> query = getJpaQueryFactory().select(content);
        query.from(content);
        BooleanBuilder builder = new BooleanBuilder();
        if (siteId != null) {
            builder.and(content.siteId.eq(siteId));
        }
        builder.and(content.recycle.eq(false));
        builder.and(content.hasDeleted.eq(false));
        //builder.and(content.channel.hasDeleted.eq(false))
        if(StringUtils.isNotBlank(pcTpl)||StringUtils.isNotBlank(mobileTpl)){
            query.join(ext).on(ext.id.eq(content.id));
        }
        if(StringUtils.isNotBlank(pcTpl)){
            builder.and(ext.tplPc.eq(pcTpl));
        }
        if(StringUtils.isNotBlank(mobileTpl)){
            builder.and(ext.tplMobile.eq(mobileTpl));
        }
        return query.where(builder).fetchCount();
    }

    @Autowired
    private ContentService contentService;

    private EntityManager em;

    @javax.persistence.PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Content> getListForCache(List<Integer> ids) {
        QContent qContent = QContent.content;
        BooleanBuilder builder = new BooleanBuilder();
        if (!ids.isEmpty()) {
            builder.and(qContent.id.in(ids));
        }
        return getJpaQueryFactory().select(qContent).from(qContent).where(builder)
                .setHint(QueryHints.HINT_CACHEABLE, true).fetch();
    }

    @Override
    public Page<Content> getPage(Pageable pageable, List<Integer> modelIds) {
        QContent content = QContent.content;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(content.hasDeleted.eq(false));
        builder.and(content.modelFieldSet.isNull());
        builder.and(content.modelId.in(modelIds));
        JPAQuery<Content> query = new JPAQuery<Content>(this.em);
        query.from(content).where(builder);
        return QuerydslUtils.page(query, pageable, content);
    }

}
