/*
 * @Copyright:  江西金磊科技发展有限公司  All rights reserved.Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */

package com.jeecms.content.dao;

import com.jeecms.common.base.dao.IBaseDao;
import com.jeecms.content.dao.ext.ContentFrontDaoExt;
import com.jeecms.content.domain.Content;

/**
 * @author xiaohui
 * @version 1.0
 * @date 2019/7/19 9:28
 */

public interface ContentFrontDao extends IBaseDao<Content, Integer>, ContentFrontDaoExt {

}
