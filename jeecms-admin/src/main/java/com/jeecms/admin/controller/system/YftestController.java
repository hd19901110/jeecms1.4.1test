package com.jeecms.admin.controller.system;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.jeecms.common.base.controller.BaseController;
import com.jeecms.common.base.domain.DeleteDto;
import com.jeecms.common.exception.GlobalException;
import com.jeecms.common.jsonfilter.annotation.MoreSerializeField;
import com.jeecms.common.jsonfilter.annotation.SerializeField;
import com.jeecms.common.response.ResponseInfo;
import com.jeecms.system.domain.YfTest;
@RequestMapping(value = "/yftest")
@RestController
public class YftestController extends BaseController<YfTest, Integer>{

	@PostConstruct
	public void init() {
		String[] queryParams = { "name_LIKE", "sex_EQ","age_EQ" };
		super.setQueryParams(queryParams);
	}
	
	
	
	/**
	 * 测试列表含分页
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@MoreSerializeField({ @SerializeField(clazz = YfTest.class, includes = {"id","name","sex","age","weight","height"} ) })
	public ResponseInfo page(HttpServletRequest request,
			@PageableDefault(sort = "id", direction = Direction.DESC) 
		Pageable pageable) throws GlobalException {
		ResponseInfo responseInfo = super.getPage(request, pageable, false);
		return responseInfo;
	}

	
	/**
	 * 添加
	 */
	@RequestMapping(method = RequestMethod.POST)
	@Override
	public ResponseInfo save(@RequestBody @Valid YfTest yftest, BindingResult result) throws GlobalException {
		super.validateBindingResult(result);
		yftest = service.save(yftest);
		return new ResponseInfo();
	}
	
	/**
	 * api修改
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@Override
	public ResponseInfo update(@RequestBody @Valid YfTest bean, BindingResult result) throws GlobalException {
		super.validateBindingResult(result);
		service.update(bean);
		return new ResponseInfo();
	}

	/**
	 * api删除
	 */
	@Override
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseInfo delete(@RequestBody @Valid DeleteDto dels, BindingResult result) throws GlobalException {
		super.validateBindingResult(result);
		return super.deleteBeatch(dels.getIds());
	}
    
	/**
	 * api详情
	 */
	@RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.GET)
	@MoreSerializeField({ @SerializeField(clazz = YfTest.class) })
	@Override
	public ResponseInfo get(@PathVariable(name = "id") Integer id) throws GlobalException {
		return super.get(id);
	}

	
	
	
	
}
