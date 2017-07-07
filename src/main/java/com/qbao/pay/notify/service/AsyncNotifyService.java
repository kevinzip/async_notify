package com.qbao.pay.notify.service;

import com.qbao.pay.notify.entity.AsyncNotifyDetail;

public interface AsyncNotifyService {

	/**
	 * 新增异步通知明细记录
	 * 
	 * @param asyncNotifyDetail
	 * @return
	 */
	public int saveAsyncNotifyDetail(AsyncNotifyDetail asyncNotifyDetail);
}
