package com.qbao.pay.notify.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qbao.pay.notify.dao.mapper.AsyncNotifyDetailMapper;
import com.qbao.pay.notify.entity.AsyncNotifyDetail;
import com.qbao.pay.notify.enumeration.NotifyTypeEnum;
import com.qbao.pay.notify.service.NotifyFlowService;
import com.qbao.pay.notify.until.HttpClientUtil;
import com.qbao.pay.notify.until.JSONUtils;

@RestController
public class NotifyFlowController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotifyFlowController.class);

	@Autowired
	private NotifyFlowService notifyFlowService;
	
	@Resource
	private AsyncNotifyDetailMapper asyncNotifyDetailMapper;

	/**
	 * 查询异步回调明细
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param entity
	 * @return
	 */
	@GetMapping("/getNotifyData")
	public Map<String, Object> getNotifyData(int pageNum, int pageSize, AsyncNotifyDetail entity) {
		try {
			return notifyFlowService.queryNotifyDetaills(pageNum, pageSize, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 手动回调异步通知消息
	 * 
	 * @param asyncNotifyId
	 * @return
	 */
	@GetMapping("/sendNotifyData")
	public Map<String, Object> sendNotifyData(Long asyncNotifyId) {

		if(asyncNotifyId>0){
			AsyncNotifyDetail notifyEntity = asyncNotifyDetailMapper.selectByPrimaryKey(asyncNotifyId);
			System.out.println(asyncNotifyId);
			/**
			 * 将数据发送给指定的notify_url
			 */
			boolean isSuc = processNotice(notifyEntity);
			if(isSuc){
				//更新异步通知明细表
				notifyEntity.setAsyncStatus(Byte.parseByte(NotifyTypeEnum.NOTIFY_SUCCESS.getCode()));
				asyncNotifyDetailMapper.updateNotifyDetail(notifyEntity);
			}
		}
		return null;
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
