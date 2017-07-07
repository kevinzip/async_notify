package com.qbao.pay.notify.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qbao.pay.notify.service.AsyncNotifyPushService;

/**
 * 异步回调失败推送定时任务
 * 
 * @author zouchanglong
 *
 */
@Component
@Configurable
@EnableScheduling
public class NotifyAutoPushTask {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(NotifyAutoPushTask.class);
	
	@Resource
	private AsyncNotifyPushService notifyPushService;
	
	//每1分钟执行一次
	@Scheduled(cron = "0 */10000 *  * * * ")
	public void asyncNotifyPush(){
		LOGGER.info("异步回调失败推送定时任务 start...");
		long start = System.currentTimeMillis();
		notifyPushService.push();
		long dis = System.currentTimeMillis() - start;
		LOGGER.info("异步回调失败推送定时任务 end. 耗时：" + dis/1000 + "S");
	}
	
}
