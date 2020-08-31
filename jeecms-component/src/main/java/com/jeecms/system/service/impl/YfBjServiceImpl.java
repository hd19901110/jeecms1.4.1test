package com.jeecms.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.base.service.BaseCacheServiceImpl;
import com.jeecms.system.dao.YfBjDao;
import com.jeecms.system.domain.YfBj;
import com.jeecms.system.service.YfBjService;

@Service
@Transactional(rollbackFor = Exception.class)
public class YfBjServiceImpl extends BaseCacheServiceImpl<YfBj, YfBjDao, Integer>implements YfBjService{

	@Override
	public YfBj findByNdAndXdAndBjmc(String nd, String xd, String bjmc) {
		return dao.findByNdAndXdAndBjmcAndHasDeleted(nd, xd, bjmc,false);
	}

}
