package com.qbao.pay.notify.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qbao.pay.notify.autoregister.BaseConnector;
import com.qbao.pay.notify.entity.MqConfigEntity;
import com.qbao.pay.notify.entity.RabbitmqConfEntity;
import com.qbao.pay.notify.service.RabbitMqConfService;

@RestController
public class RabbitMqConfController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConfController.class);

	@Autowired
	private RabbitMqConfService mqConfService;
	
	@Autowired
	private Environment env;

	/**
	 * 查询异步回调明细
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param entity
	 * @return
	 */
	@GetMapping("/getRabbitMqData")
	public Map<String, Object> getRabbitMqData(int pageNum, int pageSize,
			RabbitmqConfEntity entity) {
		LOGGER.info("RabbitmqConfEntity对象：{}" + entity);
		try {
			return mqConfService.queryNotifyDetaills(pageNum, pageSize, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增MQ队列消息Quene
	 * @param entity
	 * @return
	 */
	@GetMapping("/addRabbitMqData")
	public Map<String, Object> addRabbitMqData(RabbitmqConfEntity entity) {
		LOGGER.info("RabbitmqConfEntity对象：{}" + entity);
		Map<String, Object> tableData = new HashMap<>();
		
		MqConfigEntity confEntity = new MqConfigEntity();
		confEntity.setExchangeName(entity.getExchangeName());
		confEntity.setQueueName(entity.getQueueName());
		confEntity.setRoutingKey(entity.getRoutingKey());
		confEntity.setHost(env.getProperty("spring.rabbitmq.host"));
		confEntity.setPort(Integer.valueOf(env.getProperty("spring.rabbitmq.port")));
		confEntity.setUsername(env.getProperty("spring.rabbitmq.username"));
		confEntity.setPassword(env.getProperty("spring.rabbitmq.password"));
		try {
			new BaseConnector(confEntity);
			int count = mqConfService.insertRabbitMqConf(entity);
			if (count > 0) {
				tableData.put("code", 10000);
			} else {
				tableData.put("code", 99999);
			}
			return tableData;
		} catch (IOException e1) {
			LOGGER.error("创建MQ消息队列失败!");
			tableData.put("code", 99999);
			return tableData;
		} catch (TimeoutException e1) {
			LOGGER.error("连接RabbitMq服务器异常!");
			tableData.put("code", 99999);
			return tableData;
		}
	}
	
	/**
	 * 删除MQ队列消息
	 * 
	 * @param entity
	 * @return
	 */
	@GetMapping("/delRabbitMqData")
	public Map<String, Object> delRabbitMqData(RabbitmqConfEntity entity) {
		LOGGER.info("RabbitmqConfEntity对象：{}" + entity);
		try {
			mqConfService.deleteRabbitMqConf(entity.getMqId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
