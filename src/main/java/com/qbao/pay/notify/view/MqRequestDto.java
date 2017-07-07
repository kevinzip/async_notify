package com.qbao.pay.notify.view;


/**
 * mq相关操作dto
 * 
 * @author Administrator
 * 
 */
public class MqRequestDto {
	
	private String outTradeNo;
	
	private String notifyUrl;
	
	private String notifyMessage;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getNotifyMessage() {
		return notifyMessage;
	}

	public void setNotifyMessage(String notifyMessage) {
		this.notifyMessage = notifyMessage;
	}
	
	
	
}
