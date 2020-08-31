package com.jeecms.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.base.service.BaseCacheServiceImpl;
import com.jeecms.system.dao.YfXnXqDao;
import com.jeecms.system.domain.YfXnXq;
import com.jeecms.system.service.YfXnXqService;
@Service
@Transactional(rollbackFor = Exception.class)
public class YfXnXqServiceImpl extends BaseCacheServiceImpl<YfXnXq, YfXnXqDao, Integer>implements YfXnXqService{

	@Override
	 @Transactional(readOnly = true, rollbackFor = Exception.class)
	public YfXnXq findByXnAndXq(String xn, String xq) {
		YfXnXq result = null;
		result = dao.findByXnAndXqAndHasDeleted(xn,xq,false);
		return result;
	}

}
