package com.jeecms.system.dao;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;

import com.jeecms.common.base.dao.IBaseDao;
import com.jeecms.system.domain.YfBj;

public interface YfBjDao extends IBaseDao<YfBj, Integer>{
	/**
	 * 
	 * @param nd
	 * @param xd
	 * @param bjmc
	 * @return
	 */
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	YfBj findByNdAndXdAndBjmcAndHasDeleted(String nd, String xd, String bjmc,Boolean hasDeleted);

}
