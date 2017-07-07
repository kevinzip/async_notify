package com.qbao.pay.notify.listener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.qbao.pay.notify.dao.mapper.AsyncCountMapper;
import com.qbao.pay.notify.dao.mapper.AsyncNotifyDetailMapper;
import com.qbao.pay.notify.entity.AsyncCount;
import com.qbao.pay.notify.entity.AsyncNotifyDetail;
import com.qbao.pay.notify.enumeration.AsyncNotifyEnum;
import com.qbao.pay.notify.enumeration.NotifyTypeEnum;
import com.qbao.pay.notify.exception.DataValidateException;
import com.qbao.pay.notify.until.DateUtil;
import com.qbao.pay.notify.until.HttpClientUtil;
import com.qbao.pay.notify.until.JSONUtils;
import com.qbao.pay.notify.until.StringUtil;

/**
 * @author zouchanglong
 * 异步回调
 */
@Component
public class AsyncNotifyCallbackListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncNotifyCallbackListener.class);

	
	@Resource
	private AsyncCountMapper asynCountMapper;
	
	@Resource
	private AsyncNotifyDetailMapper asyncNotifyDetailMapper;

	/**
	 * 异步回调MQ消息
	 * @param message
	 */
	@RabbitListener(queues="trade.async.callback.queue")
//	@RabbitListener(queues="smt.item.queue.test")
	public void asyncNotifyListener(Message message) throws DataValidateException {
		LOGGER.info("异步回调MQ消息内容："+message);
		
		String messageBody = "";
		try {
			messageBody = new String(message.getBody(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			LOGGER.error("获取异步回调MQ消息内容失败,"+e.getMessage());
		}
		System.out.println(messageBody);
		/**
		 * 1、获取Mq消息内容
		 */
//		String outTradeNo = "";
//		String notifyUrl = "";
//		String notifyMessage = "";
//		if(!StringUtil.isNullString(messageBody)){
//			try {
//				JSONObject jb = JSONUtils.toJSONObject(messageBody);
//				outTradeNo = jb.getString("outTradeNo");
//				notifyUrl = jb.getString("notifyUrl");
//				notifyMessage = jb.getString("result");
//				LOGGER.info(jb.getString("outTradeNo"));
//				JSONObject jb1 = JSONUtils.toJSONObject(jb.getString("result"));
//				LOGGER.info(jb1.getString("creator"));
//			} catch (Exception e) {
//				LOGGER.error("解析MQ消息失败,"+e.getMessage());
//			}
//		}
//		
//		/**
//		 * 2、数据入库
//		 */
//		AsyncNotifyDetail notifyEntity = new AsyncNotifyDetail();
//		notifyEntity.setChannelCode(AsyncNotifyEnum.ASYNC_NOTIFY_BALANCE.getCode());
//		notifyEntity.setAsyncStatus(Byte.parseByte(NotifyTypeEnum.NOTIFY_ING.getCode()));
//		notifyEntity.setAsyncType(Byte.parseByte("1"));//结算
//		notifyEntity.setOutTradeNo(outTradeNo);
//		notifyEntity.setNotifyUrl(notifyUrl);
//		notifyEntity.setNotifyMessage(notifyMessage);
//		
//		notifyEntity = saveNotifyDetail(notifyEntity);
//		
//		/**
//		 * 3、数据校验
//		 */
//		if(!StringUtil.isNullString(outTradeNo)&& !StringUtil.isNullString(notifyUrl)&&
//				!StringUtil.isNullString(notifyMessage)){
//
//			/**
//			 * 1.将数据发送给指定的notify_url
//			 */
//			boolean isSuc = processNotice(notifyEntity);
//			
//			/**
//			 * 2.根据数据异步回调结果，更新统计表和明细表数据
//			 */
//			if(isSuc){
//				/**
//				 * 3.更新异步通知明细表
//				 */
//				notifyEntity.setAsyncStatus(Byte.parseByte(NotifyTypeEnum.NOTIFY_SUCCESS.getCode()));
//				asyncNotifyDetailMapper.updateNotifyDetail(notifyEntity);
//			}else{
//				/**
//				 * 4.根据步骤1 的结果，发送失败则在异步通知统计表中记录一条数据
//				 * 并将下次发送时间插入为当前时间+5分钟
//				 */
//				AsyncCount countEntity = new AsyncCount();
//				AsyncCount asyncCont = asynCountMapper.selectAsyncNotifyById(notifyEntity.getAsyncNotifyId());
//				countEntity.setAsyncNotifyId(notifyEntity.getAsyncNotifyId());
//				if(asyncCont==null){
//					countEntity.setAsynncTimes((byte) 1);
//				}else{
//					countEntity.setAsynncTimes((byte) (asyncCont.getAsynncTimes()+1));
//				}
//				countEntity.setCreateTime(new Date());
//				countEntity.setModifyTime(new Date());
//				countEntity.setOpenFlag(Byte.parseByte("0"));
//				countEntity.setSendTime(DateUtil.getInternalDateByMinute(new Date(), 5));
//				asynCountMapper.insertNotifyRecord(countEntity);
//			}
//		}
	}
	
	/**
	 * 推送单条
	 * @param entity
	 */
	private boolean processNotice(AsyncNotifyDetail entity) {
		try {
			List<NameValuePair> listPair = new ArrayList<NameValuePair>();
			listPair.add(new BasicNameValuePair("outTradeNo", entity.getOutTradeNo()));
			listPair.add(new BasicNameValuePair("notifyMessage", entity.getNotifyMessage()));
			String returnJson = HttpClientUtil.doPost(listPair, entity.getNotifyUrl(), "UTF-8");
			LOGGER.info("异步回调返回数据:{}" + returnJson);
			JSONObject jb = JSONUtils.toJSONObject(returnJson);
			String respCode = jb.getString("respCode");
			if("Y".equals(respCode)){
				return true;
			}else{
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 回调明细入库
	 * 
	 * @param notifyEntity
	 * @return
	 */
	public AsyncNotifyDetail saveNotifyDetail(AsyncNotifyDetail notifyEntity){
		
		AsyncNotifyDetail notifyResult = asyncNotifyDetailMapper.selectByOutTradNo(notifyEntity.getOutTradeNo());
		if(notifyResult==null){
			try {
				asyncNotifyDetailMapper.insertAsyncNotify(notifyEntity);
			} catch (Exception e) {
				LOGGER.error("插入数据库async_notify_detail失败，"+e.getMessage());
			}
		}else{
			notifyEntity.setAsyncNotifyId(notifyResult.getAsyncNotifyId());
		}
		return notifyEntity;
		
	}
	
//	public static void main(String[] args) {
//		String str = "{\"outTradeNo\":1122009987,\"notifyUrl\":\"http://192.168.1.8:8080/acturl.html\",\"result\":{\"creator\":\"admin\",\"email\":\"9123344569@qq.com\",\"gmtCreated\":\"1490760835000\"}}";
//		// 以employee为例解析，map类似  
//        JSONObject jb = JSONUtils.toJSONObject(str);
//        System.out.println(jb.getString("outTradeNo"));
//        JSONObject jb1 = JSONUtils.toJSONObject(jb.getString("result"));
//        System.out.println(jb1.getString("creator"));
//	}
	
}
