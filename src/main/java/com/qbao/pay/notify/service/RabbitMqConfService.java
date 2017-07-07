package com.qbao.pay.notify.service;

import java.util.Map;

import com.qbao.pay.notify.entity.RabbitmqConfEntity;

/**
 * MQ信息配置服务
 * 
 * @author zouchanglong
 * 
 * @since 2017-4-06
 *
 * @version 0.0.1
 */
public interface RabbitMqConfService {

	/**
	 * 查询MQ队列消息记录
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param entity
	 * @return
	 */
	Map<String, Object> queryNotifyDetaills(int pageNum, int pageSize, RabbitmqConfEntity entity);
	
	/**
	 * 新增MQ队列消息
	 * 
	 * @param entity
	 * @return
	 */
	int insertRabbitMqConf(RabbitmqConfEntity entity);
	
	/**
	 * 删除MQ队列消息
	 * 
	 * @param mqId
	 */
	void deleteRabbitMqConf(Long mqId);
}
