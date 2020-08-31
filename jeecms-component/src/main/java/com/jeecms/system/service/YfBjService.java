package com.jeecms.system.service;

import com.jeecms.common.base.service.IBaseService;
import com.jeecms.system.domain.YfBj;

public interface YfBjService extends IBaseService<YfBj, Integer>{
	YfBj findByNdAndXdAndBjmc(String nd, String xd, String bjmc);

}
