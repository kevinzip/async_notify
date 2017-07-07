package com.qbao.pay.notify.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qbao.pay.notify.dao.IBaseMapperDao;
import com.qbao.pay.notify.entity.AsyncCount;

@Repository
public interface AsyncCountMapper extends IBaseMapperDao<AsyncCount, Long> {
	
	/**
	 * 根据异步结算通知ID查询统计数据
	 * 
	 * @param asyncNotifyId
	 * @return
	 */
	AsyncCount selectAsyncNotifyById(Long asyncNotifyId);
	
	/**
	 * 查询异步通知失败列表
	 * 
	 * @return
	 */
	List<AsyncCount> queryAsyncNotifyList();
	
	/**
	 * 更新异步回调统计明细
	 * 
	 * @param record
	 * @return
	 */
	int updateAsyncCountDetail(AsyncCount record);
	
	/**
	 * 新增异步通知统计记录
	 * 
	 * @param record
	 * @return
	 */
	int insertNotifyRecord(AsyncCount record);
}