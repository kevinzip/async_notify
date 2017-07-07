package com.qbao.pay.notify.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qbao.pay.notify.dao.IBaseMapperDao;
import com.qbao.pay.notify.entity.AsyncNotifyDetail;
import com.qbao.pay.notify.entity.RabbitmqConfEntity;

@Repository
public interface RabbitmqConfMapper extends IBaseMapperDao<AsyncNotifyDetail, Long> {
	
	/**
	 * 查询所有MQ配置明细
	 * 
	 * @param detaill
	 * @return
	 */
	List<RabbitmqConfEntity> queryDetailByKey(RabbitmqConfEntity detaill);
	
	/**
	 * 查询MQ消息队列总数
	 * 
	 * @param entity
	 * @return
	 */
	int getCount(RabbitmqConfEntity entity);
	
	/**
	 * 新增MQ消息队列
	 * 
	 * @param notifyEntity
	 * @return
	 */
	int insertRabbitMqConf(RabbitmqConfEntity notifyEntity);
	
	/**
	 * 删除MQ消息队列
	 * 
	 * @param mqId
	 */
	void deleteRabbitMqConf(Long mqId);
	
	/**
	 * 查询MQ消息记录
	 * 
	 * @param notifyEntity
	 * @return
	 */
	RabbitmqConfEntity selectByPrimaryKey(RabbitmqConfEntity notifyEntity);
   
}