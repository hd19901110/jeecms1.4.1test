/*   
 * @Copyright:  江西金磊科技发展有限公司  All rights reserved.Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */  

package com.jeecms.content.dao;

import com.jeecms.common.base.dao.IBaseDao;
import com.jeecms.content.dao.ext.ContentDaoExt;
import com.jeecms.content.domain.Content;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * 内容主体dao接口
 * @author: chenming
 * @date:   2019年5月6日 下午2:32:49
 */
public interface ContentDao extends IBaseDao<Content, Integer>, ContentDaoExt {
	
	@Query("select bean from Content bean where 1 = 1 and bean.id = ?1 and bean.hasDeleted = 0")
	Content findContent(Integer id);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Content> findByChannelIdInAndHasDeleted(Integer[] channelIds,Boolean hasDeleted);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Content> findByChannelIdAndRecycleAndHasDeleted(Integer chanenlId,Boolean recycle,Boolean hasDeleted);

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Content findByIdAndRecycleAndHasDeleted(Integer contentId,Boolean recycle,Boolean hasDeleted);

	@Query("select bean.id from Content bean where bean.hasDeleted = ?1")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Content> findByHasDeleted(Boolean hasDeleted);

	/**
	 * 得到排序值最大的内容
	 * @return Content
	 */
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Content findFirstByOrderBySortNumDesc();


	/**
	 * 得到ID最大的内容
	 * @return Content
	 */
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Content findFirstByOrderByIdDesc();

	/**
	 * 按栏目和创建类型查询
	 * @param channelId
	 * @param types
	 * @return
	 */
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Content> findByChannelIdAndCreateTypeIn(Integer channelId, List<Integer> types);

	/**
	 * 按栏目和内容id查询
	 * @param channelId
	 * @param types
	 * @return
	 */
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Content> findByChannelIdAndOriContentIdAndCreateTypeIn(Integer channelId, Integer contentId, List<Integer> types);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	long countByChannelIdInAndRecycleAndHasDeleted(Integer[] chanenlIds,Boolean recycle,Boolean hasDeleted);

	List<Integer> findIdByHasDeleted(Boolean hasDeleted);
}
