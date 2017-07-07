package com.qbao.pay.notify.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qbao.pay.notify.dao.IBaseMapperDao;
import com.qbao.pay.notify.entity.AsyncNotifyDetail;

@Repository
public interface AsyncNotifyDetailMapper extends IBaseMapperDao<AsyncNotifyDetail, Long> {
	
	/**
	 * 新增异步通知明细记录
	 * 
	 * @param notifyEntity
	 * @return
	 */
	int insertAsyncNotify(AsyncNotifyDetail notifyEntity);
	
	/**
	 * 根据主键ID查询异步通知记录
	 * 
	 * @param asyncNotifyId
	 * @return
	 */
	AsyncNotifyDetail selectByPrimaryKey(Long asyncNotifyId);
	
	/**
	 * 	根据业务流水号查询异步通知明细
	 * 
	 * @param outTradeNo
	 * @return
	 */
	AsyncNotifyDetail selectByOutTradNo(String outTradeNo);

	/**
	 * 更新异步回调明细记录
	 * 
	 * @param record
	 * @return
	 */
	int updateNotifyDetail(AsyncNotifyDetail record);
	
	/**
	 * 查询所有异步通知明细
	 * 
	 * @param detaill
	 * @return
	 */
	List<AsyncNotifyDetail> queryDetailByKey(AsyncNotifyDetail detaill);
	
	/**
	 * 查询异步通知明细总数
	 * 
	 * @param entity
	 * @return
	 */
	int getCount(AsyncNotifyDetail entity);
}