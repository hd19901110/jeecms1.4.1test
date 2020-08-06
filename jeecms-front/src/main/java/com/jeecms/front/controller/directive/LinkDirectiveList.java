/*
 * @Copyright:  江西金磊科技发展有限公司  All rights reserved.Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */

package com.jeecms.front.controller.directive;

import com.jeecms.common.page.Paginable;
import com.jeecms.common.page.PaginableRequest;
import com.jeecms.common.web.freemarker.DefaultObjectWrapperBuilderFactory;
import com.jeecms.common.web.freemarker.DirectiveUtils;
import com.jeecms.system.domain.CmsSite;
import com.jeecms.system.domain.Link;
import com.jeecms.system.service.LinkService;
import com.jeecms.util.FrontUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_LIST;

/**
 * 友情链接标签
 *
 * @author xiaohui
 * @version 1.0
 * @date 2019/7/16 14:49
 */

public class LinkDirectiveList implements TemplateDirectiveModel {

	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "cms_link_list";

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
						TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer linkTypeId = DirectiveUtils.getInt("linkTypeId", params);
		int count = FrontUtils.getCount(params);
		Map<String, String[]> map = new HashMap<String, String[]>(3);
		Integer siteId = DirectiveUtils.getInt("siteId", params);
		if (siteId == null) {
			CmsSite site = FrontUtils.getSite(env);
			if (site != null) {
				siteId = site.getId();
			}
		}
		map.put("EQ_siteId_Integer", new String[]{siteId != null ? siteId.toString() : ""});
		if (linkTypeId != null) {
			map.put("EQ_linkTypeId_Integer", new String[]{linkTypeId.toString()});
		}
		map.put("EQ_isEnable_Boolean", new String[]{"true"});
		Paginable paginable = new PaginableRequest(0, count);
		List<Link> links = linkService.getList(map, paginable, true);
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		paramWrap.put(OUT_LIST, DefaultObjectWrapperBuilderFactory.getDefaultObjectWrapper().wrap(links));
		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	@Autowired
	private LinkService linkService;
}
