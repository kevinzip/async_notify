package com.qbao.pay.notify.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qbao.pay.notify.dao.mapper.AsyncCountMapper;
import com.qbao.pay.notify.dao.mapper.AsyncNotifyDetailMapper;
import com.qbao.pay.notify.entity.AsyncCount;
import com.qbao.pay.notify.entity.AsyncNotifyDetail;
import com.qbao.pay.notify.enumeration.NotifyTypeEnum;
import com.qbao.pay.notify.service.AsyncNotifyPushService;
import com.qbao.pay.notify.until.DateUtil;
import com.qbao.pay.notify.until.HttpClientUtil;
import com.qbao.pay.notify.until.JSONUtils;

/**
 * 异步回调失败推送  实现
 * 
 * @author zouchanglong
 *
 */
@Service
public class AsyncNotifyPushServiceImpl implements AsyncNotifyPushService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncNotifyPushServiceImpl.class);
	
	@Resource
	private AsyncCountMapper asynCountMapper;
	
	@Resource
	private AsyncNotifyDetailMapper asyncNotifyDetailMapper;

	@Override
	public void push() {
		/**
		 * 查询异步推送失败，且推送次数5次以内的数据
		 */
		List<AsyncCount> list = asynCountMapper.queryAsyncNotifyList();
		if(list !=null && list.size()>0) {
			
			for(int i=0;i<list.size();i++){
				AsyncNotifyDetail notifyDetail= asyncNotifyDetailMapper.selectByPrimaryKey(list.get(i).getAsyncNotifyId());
				
				try {
					/**
					 * 单条数据推送
					 */
					boolean isSuc = processNotice(notifyDetail);
					
					/**
					 * 更新异步通知统计表
					 */
					AsyncCount countEntity = new AsyncCount();
					countEntity.setAsyncNotifyId(list.get(i).getAsyncNotifyId());
					countEntity.setAsynncTimes((byte) (list.get(i).getAsynncTimes()+1));
					countEntity.setCreateTime(new Date());
					countEntity.setModifyTime(new Date());
					if(countEntity.getAsynncTimes()==5 || isSuc){
						countEntity.setOpenFlag(Byte.parseByte("1"));
					}else{
						countEntity.setOpenFlag(Byte.parseByte("0"));
					}
					asynCountMapper.updateAsyncCountDetail(countEntity);
					
					/**
					 * 更新异步通知明细表
					 */
					if(isSuc){
						notifyDetail.setAsyncStatus(Byte.parseByte(NotifyTypeEnum.NOTIFY_SUCCESS.getCode()));
						asyncNotifyDetailMapper.updateNotifyDetail(notifyDetail);
					}else{
						if(countEntity.getAsynncTimes()==5){
							notifyDetail.setAsyncStatus(Byte.parseByte(NotifyTypeEnum.NOTIFY_FAILE.getCode()));
							asyncNotifyDetailMapper.updateNotifyDetail(notifyDetail);
						}
					}
					
					
				} catch (Exception e) {
					LOGGER.error("外部订单号：" +notifyDetail.getOutTradeNo() + "回调失败，" + e.getMessage(), e);
					AsyncCount countEntity = new AsyncCount();
					countEntity.setAsyncNotifyId(list.get(i).getAsyncNotifyId());
					countEntity.setAsynncTimes((byte) (countEntity.getAsynncTimes()+1));
					countEntity.setCreateTime(new Date());
					countEntity.setModifyTime(new Date());
					if(countEntity.getAsynncTimes()==5){
						countEntity.setOpenFlag(Byte.parseByte("1"));
					}else{
						countEntity.setOpenFlag(Byte.parseByte("0"));
					}
					countEntity.setSendTime(DateUtil.getInternalDateByMinute(new Date(), 5));
					asynCountMapper.updateAsyncCountDetail(countEntity);
				}
			}
		}else{
			LOGGER.info("没查询到任何数据！");
		}
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

}
