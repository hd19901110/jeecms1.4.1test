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
import com.jeecms.system.domain.YfBj;
import com.jeecms.system.service.YfBjService;

@RequestMapping(value = "/yf/bj")
@RestController
public class YfBjController extends BaseController<YfBj, Integer>{
	@PostConstruct
	public void init() {
		String[] queryParams = { "nd_LIKE", "bjmc_LIKE", "xd_EQ" };
		super.setQueryParams(queryParams);
	}
	
	/**
	 * yfXnXq学年学期含分页
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@MoreSerializeField({ @SerializeField(clazz = YfBj.class, includes = {"id","nd","xd","bjmc"} ) })
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
	public ResponseInfo save(@RequestBody @Valid YfBj yfXnXq, BindingResult result) throws GlobalException {
		super.validateBindingResult(result);
		yfXnXq = service.save(yfXnXq);
		return new ResponseInfo();
	}
	/**
	 * yfXnXq修改
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@Override
	public ResponseInfo update(@RequestBody @Valid YfBj yfXnXq, BindingResult result) throws GlobalException {
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
	@MoreSerializeField({ @SerializeField(clazz = YfBj.class) })
	@Override
	public ResponseInfo get(@PathVariable(name = "id") Integer id) throws GlobalException {
		return super.get(id);
	}
	
	/**
	 * 检查api地址是否唯一
	 */
	@RequestMapping(value = "/unique", method = RequestMethod.GET)
	public ResponseInfo checkRouting(@RequestParam String nd, @RequestParam String xd, String bjmc,
			@RequestParam(name = "id", required = false) Integer id) throws GlobalException {
		Boolean result = this.ckUrl(nd, xd, bjmc, id);
		return new ResponseInfo(result);
	}
    
	/**
	 * 检查学年+学期是否唯一
	 */
	private Boolean ckUrl(String nd, String xd,String bjmc , Integer id) throws GlobalException {
		YfBj yfXnxq = service.findByNdAndXdAndBjmc(nd, xd,bjmc);
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
    private YfBjService service;
	
	
	
	
}
