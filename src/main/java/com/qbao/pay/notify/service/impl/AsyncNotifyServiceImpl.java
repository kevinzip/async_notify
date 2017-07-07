package com.qbao.pay.notify.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qbao.pay.notify.dao.mapper.AsyncCountMapper;
import com.qbao.pay.notify.dao.mapper.AsyncNotifyDetailMapper;
import com.qbao.pay.notify.entity.AsyncNotifyDetail;
import com.qbao.pay.notify.service.AsyncNotifyService;

@Service
public class AsyncNotifyServiceImpl implements AsyncNotifyService {

	
	@Resource
	AsyncCountMapper countMapper;
	
	@Resource
	AsyncNotifyDetailMapper detailMapper;

	/**
	 * 新增异步通知明细记录
	 */
	@Override
	public int saveAsyncNotifyDetail(AsyncNotifyDetail asyncNotifyDetail) {
		
		return detailMapper.insertAsyncNotify(asyncNotifyDetail);
	}
	
	
	
}
