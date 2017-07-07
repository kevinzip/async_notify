package com.qbao.pay.notify.autoregister;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.StringUtils;

import com.qbao.pay.notify.entity.MqConfigEntity;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * MQ消息Exchange和Quene创建
 * 
 * @author zouchanglong
 *
 */
public class BaseConnector {
	protected Channel channel;
	protected Connection connection;
	protected String queueName="spring-boot-quene";
	protected String exchangeName="spring-boot-exchange";
	protected String routingKey="spring-boot-routingKey";
	
	public BaseConnector(MqConfigEntity confEntity) 
			throws IOException, TimeoutException {
		if(StringUtils.isNotEmpty(confEntity.getQueueName())){
			this.queueName = confEntity.getQueueName();
		}
		if(StringUtils.isNotEmpty(confEntity.getExchangeName())){
			this.exchangeName = confEntity.getExchangeName();
		}
		if(StringUtils.isNotEmpty(confEntity.getRoutingKey())){
			this.routingKey = confEntity.getRoutingKey();
		}
		// 打开连接和创建频道
		ConnectionFactory factory = new ConnectionFactory();
		// 设置MabbitMQ所在主机ip
		factory.setHost(confEntity.getHost());
		factory.setPort(confEntity.getPort());
		factory.setUsername(confEntity.getUsername());
		factory.setPassword(confEntity.getPassword());
		factory.setVirtualHost("/");
		// 创建连接
		connection = factory.newConnection();
		// 创建频道
		channel = connection.createChannel();
		// 声明创建队列
		channel.exchangeDeclare(exchangeName, "direct", true);
		// 指定一个队列
		channel.queueDeclare(queueName, true, false, false, null);
		//绑定
		channel.queueBind(queueName, exchangeName, routingKey);
	}
}
