package com.qbao.pay.notify.service;

import java.util.Map;

import com.qbao.pay.notify.entity.AsyncNotifyDetail;

/**
 * 异步查询明细服务
 * 
 * @author zouchanglong
 * 
 * @since 2017-4-06
 *
 * @version 0.0.1
 */
public interface NotifyFlowService {

	Map<String, Object> queryNotifyDetaills(int pageNum, int pageSize, AsyncNotifyDetail entity);
	
}
