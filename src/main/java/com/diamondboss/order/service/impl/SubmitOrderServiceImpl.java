package com.diamondboss.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.diamondboss.order.service.ISubmitOrderService;

public class SubmitOrderServiceImpl implements ISubmitOrderService{

	/**
	 * 根据用户id查询已经预定的订单
	 * @param userId
	 */
	@Override
	public void queryOrderByUser(String userId) {
		
		// 1.根据用户id查询已经预定的订单
		
	}

	
	/**
	 * 根据用户id选择日期预约订单
	 */
	@Override
	public List SubmitOrderByUser() {
		
		List<Object> list = new ArrayList<>();
		
		// 每一天的订单单独处理
		for(int i=0,len =list.size(); i<len; i++){
			
			// 黑名单过滤
			
			// 查询该小区符合条件不满的社区合伙人
			
			// 新增订单
			
			// 查询是否超过订单数量
			
			// 如果超过删除该订单
			
			// 将该订单日期加入列表
			
		}
		
		return list;
	}

	// 根据小区筛选黑名单
	private void blacklist(){
		
	}

}
