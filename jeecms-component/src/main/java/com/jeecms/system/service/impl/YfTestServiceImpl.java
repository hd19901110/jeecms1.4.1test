package com.jeecms.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.base.service.BaseCacheServiceImpl;
import com.jeecms.system.dao.YfTestDao;
import com.jeecms.system.domain.YfTest;
import com.jeecms.system.service.YfTestService;

@Service
@Transactional(rollbackFor = Exception.class)
public class YfTestServiceImpl extends BaseCacheServiceImpl<YfTest, YfTestDao, Integer>implements YfTestService{
        
}
