package com.jeecms.interact.domain.vo;/**
 * @Copyright: 江西金磊科技发展有限公司  All rights reserved.Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */

import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;

/**
 * 采集结果
 * @author: tom
 * @date: 2019/11/18 14:27   
 */
public class CollectResult implements Serializable {

    /**
     * 是否还有更多数据（任务运行中、数据尚未处理完）
     */
    private boolean next;
    /***
     * 结果
     */
    private JSONArray datas;

    public CollectResult() {
    }

    public CollectResult(boolean next, JSONArray datas) {
        this.next = next;
        this.datas = datas;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public JSONArray getDatas() {
        return datas;
    }

    public void setDatas(JSONArray datas) {
        this.datas = datas;
    }
}
