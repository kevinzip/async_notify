package com.qbao.pay.notify.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.pay.notify.dao.mapper.AsyncNotifyDetailMapper;
import com.qbao.pay.notify.entity.AsyncNotifyDetail;
import com.qbao.pay.notify.service.NotifyFlowService;

/**
 * 查询异步通知明细服务实现
 * 
 * @author zouchanglong
 *
 * @since 2017-4-06
 * 
 * @version 0.1
 */
@Service
public class NotifyFlowServiceImpl implements NotifyFlowService {

	/**
	 * 异步通知数据访问层接口
	 */
	@Autowired
	private AsyncNotifyDetailMapper notifyDetaillMapper;

	@Override
	public  Map<String, Object> queryNotifyDetaills(int pageNum, int pageSize, AsyncNotifyDetail entity) {
		
		try {
			entity.setStartNum((pageNum-1)*pageSize);
			entity.setEndNum(pageSize);
			List<AsyncNotifyDetail> userList = notifyDetaillMapper.queryDetailByKey(entity);
			int count = notifyDetaillMapper.getCount(entity);
			Map<String, Object> tableData = new HashMap<>();
			tableData.put("list", userList);
			tableData.put("count", count);
			return tableData;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
