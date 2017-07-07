package com.qbao.pay.notify.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.pay.notify.dao.mapper.RabbitmqConfMapper;
import com.qbao.pay.notify.entity.RabbitmqConfEntity;
import com.qbao.pay.notify.service.RabbitMqConfService;

/**
 * MQ明细配置服务实现
 * 
 * @author zouchanglong
 *
 * @since 2017-4-18
 * 
 * @version 0.1
 */
@Service
public class RabbitMqConfServiceImpl implements RabbitMqConfService {

	/**
	 * MQ数据访问层接口
	 */
	@Autowired
	private RabbitmqConfMapper mqConfMapper;

	@Override
	public  Map<String, Object> queryNotifyDetaills(int pageNum, int pageSize, RabbitmqConfEntity entity) {
		
		try {
			entity.setStartNum((pageNum-1)*pageSize);
			entity.setEndNum(pageSize);
			List<RabbitmqConfEntity> userList = mqConfMapper.queryDetailByKey(entity);
			int count = mqConfMapper.getCount(entity);
			Map<String, Object> tableData = new HashMap<>();
			tableData.put("list", userList);
			tableData.put("count", count);
			return tableData;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int insertRabbitMqConf(RabbitmqConfEntity entity) {
		int count = 0;
		if(entity !=null){
			RabbitmqConfEntity entityResult = mqConfMapper.selectByPrimaryKey(entity);
			if(entityResult ==null){
				if(StringUtils.isNotEmpty(entity.getQueueName())&&StringUtils.isNotEmpty(entity.getExchangeName())
						&&StringUtils.isNotEmpty(entity.getRoutingKey())){
					count = mqConfMapper.insertRabbitMqConf(entity);
				}
			}else{
				count=Integer.valueOf(entityResult.getMqId()+"");
			}
		}
		return count;
	}

	@Override
	public void deleteRabbitMqConf(Long mqId) {
		mqConfMapper.deleteRabbitMqConf(mqId);
	}

}
