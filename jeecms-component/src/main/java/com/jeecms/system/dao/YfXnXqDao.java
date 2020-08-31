package com.jeecms.system.dao;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;

import com.jeecms.common.base.dao.IBaseDao;
import com.jeecms.system.domain.YfXnXq;

public interface YfXnXqDao extends IBaseDao<YfXnXq, Integer> {
	/**
	 * 
	 * @param xn 学年
	 * @param xq 学期
	 * @param hasDeleted 是否删除
	 * @return
	 */
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	YfXnXq findByXnAndXqAndHasDeleted(String xn, String xq, Boolean hasDeleted);

}
