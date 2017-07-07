/**
 * 
 */
package com.qbao.pay.notify.listener;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Configuration;


/**
 * @author zouchanglong
 *
 */
@EnableRabbit
@Configuration
public class RabbitConfig {

//	@Bean
//	public Queue quene() {
//		return new Queue("async.notify.callback.queue");
//	}
}
