/*
 * @Copyright:  江西金磊科技发展有限公司  All rights reserved.Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */

package com.jeecms.statistics.dao.impl;

import com.jeecms.common.util.MyDateUtils;
import com.jeecms.statistics.dao.ext.StatisticsSourceDaoExt;
import com.jeecms.statistics.domain.StatisticsSource;
import com.jeecms.statistics.domain.querydsl.QStatisticsSource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * @author xiaohui
 * @version 1.0
 * @date 2019/7/5 14:24
 */

public class StatisticsSourceDaoImpl implements StatisticsSourceDaoExt {
	@Override
	public List<StatisticsSource> getList(Date beginTime,
										  Date endTime,
										  Integer siteId,
										  Boolean newVisitor,
										  Integer visitorDeviceType,
										  Integer sorceUrlType) {
		BooleanBuilder exp = new BooleanBuilder();
		QStatisticsSource statisticsSource = QStatisticsSource.statisticsSource;
		JPAQuery<StatisticsSource> jpaQuery = new JPAQuery<StatisticsSource>(this.em);
		jpaQuery.from(statisticsSource);
		if (siteId != null) {
			exp.and(statisticsSource.siteId.eq(siteId));
		}
		if (beginTime != null) {
			exp.and(statisticsSource.statisticsDay.goe(MyDateUtils.formatDate(beginTime)));
		}
		if (endTime != null) {
			exp.and(statisticsSource.statisticsDay.loe(MyDateUtils.formatDate(endTime)));
		}
		if (visitorDeviceType != null) {
			exp.and(statisticsSource.visitorDeviceType.eq(visitorDeviceType));
		}
		if (sorceUrlType != null) {
			exp.and(statisticsSource.sorceUrlType.eq(sorceUrlType));
		}
		if (newVisitor != null) {
			exp.and(statisticsSource.isNewVisitor.eq(newVisitor));
		}
		jpaQuery.setHint(QueryHints.HINT_CACHEABLE, true);
		return jpaQuery.where(exp).fetch();
	}

	@Override
	public List<StatisticsSource> findByTime(Date date, Integer siteId) {
		JPAQuery<StatisticsSource> jpaQuery = new JPAQuery<StatisticsSource>(this.em);
		BooleanBuilder exp = new BooleanBuilder();
		QStatisticsSource statisticsSource = QStatisticsSource.statisticsSource;
		if (siteId != null) {
			exp.and(statisticsSource.siteId.eq(siteId));
		}
		if (date != null) {
			exp.and(statisticsSource.statisticsDay.goe(MyDateUtils.formatDate(date)));
		}
		return jpaQuery.from(statisticsSource).where(exp).fetch();
	}

	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
}
