package com.jeecms.admin.controller.system;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeecms.common.base.controller.BaseController;
import com.jeecms.common.base.domain.DeleteDto;
import com.jeecms.common.exception.GlobalException;
import com.jeecms.common.jsonfilter.annotation.MoreSerializeField;
import com.jeecms.common.jsonfilter.annotation.SerializeField;
import com.jeecms.common.response.ResponseInfo;
import com.jeecms.system.domain.YfXnXq;
import com.jeecms.system.service.YfXnXqService;
@RequestMapping(value = "/yf/xnxq")
@RestController
public class YfXnxqController extends BaseController<YfXnXq, Integer>{
	@PostConstruct
	public void init() {
		String[] queryParams = { "xn_LIKE", "xq_EQ" };
		super.setQueryParams(queryParams);
	}
	
	/**
	 * yfXnXq学年学期含分页
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@MoreSerializeField({ @SerializeField(clazz = YfXnXq.class, includes = {"id","xn","xq"} ) })
	public ResponseInfo page(HttpServletRequest request,
			@PageableDefault(sort = "id", direction = Direction.DESC) 
		Pageable pageable) throws GlobalException {
		ResponseInfo responseInfo = super.getPage(request, pageable, false);
		return responseInfo;
	}
	
	/**
	 * yfXnXq添加
	 */
	@RequestMapping(method = RequestMethod.POST)
	@Override
	public ResponseInfo save(@RequestBody @Valid YfXnXq yfXnXq, BindingResult result) throws GlobalException {
		super.validateBindingResult(result);
		yfXnXq = service.save(yfXnXq);
		return new ResponseInfo();
	}
	/**
	 * yfXnXq修改
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@Override
	public ResponseInfo update(@RequestBody @Valid YfXnXq yfXnXq, BindingResult result) throws GlobalException {
		super.validateBindingResult(result);
		service.update(yfXnXq);
		return new ResponseInfo();
	}
	
	/**
	 * yfXnXq删除
	 */
	@Override
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseInfo delete(@RequestBody @Valid DeleteDto dels, BindingResult result) throws GlobalException {
		super.validateBindingResult(result);
		return super.deleteBeatch(dels.getIds());
	}
	
	/**
	 * yfXnXq详情
	 */
	@RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.GET)
	@MoreSerializeField({ @SerializeField(clazz = YfXnXq.class) })
	@Override
	public ResponseInfo get(@PathVariable(name = "id") Integer id) throws GlobalException {
		return super.get(id);
	}
	
	/**
	 * 检查api地址是否唯一
	 */
	@RequestMapping(value = "/unique", method = RequestMethod.GET)
	public ResponseInfo checkRouting(@RequestParam String xn, @RequestParam String xq,
			@RequestParam(name = "id", required = false) Integer id) throws GlobalException {
		Boolean result = this.ckUrl(xn, xq, id);
		return new ResponseInfo(result);
	}
    
	/**
	 * 检查学年+学期是否唯一
	 */
	private Boolean ckUrl(String xn, String xq , Integer id) throws GlobalException {
		YfXnXq yfXnxq = service.findByXnAndXq(xn, xq);
		if (yfXnxq == null) {
			return true;
		}
		if (id == null) {
			return false;
		} else {
			return yfXnxq.getId().equals(id);
		}
	}
    @Autowired
    private YfXnXqService service;	
	
}
