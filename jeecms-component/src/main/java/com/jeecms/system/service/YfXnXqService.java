package com.jeecms.system.service;

import com.jeecms.common.base.service.IBaseService;
import com.jeecms.system.domain.YfXnXq;

public interface YfXnXqService  extends IBaseService<YfXnXq, Integer>{
	   /**
     * 根据apiurl查询
     * 
     * @param apiUrl
     *                apiUrl地址
     * @param requestMethod
     *                request method
     * @return api
     */
	YfXnXq findByXnAndXq(String xn, String xq);

}
