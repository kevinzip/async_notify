/**
 * 
 */
package com.qbao.pay.notify.listener;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zouchanglong
 *
 */
@Component
public class RabbitMqSend {

	@Resource
	private AmqpTemplate rabbitTemplate;
	
	/**
	 * 发送信息 
	 * 
	 * @param content
	 */
	 public void send(String content) {  
//	        this.rabbitTemplate.convertAndSend("smt.exchange.test", "smt.item.queue.test", content);  
	        this.rabbitTemplate.convertAndSend("trade.async.callback.exchange", "trade.async.callback.queue", content);  
	        System.out.println("Sender : " + content);  
	    }  
	 
}
