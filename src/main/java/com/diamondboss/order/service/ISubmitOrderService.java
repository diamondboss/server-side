package com.diamondboss.order.service;

import java.util.List;

public interface ISubmitOrderService {

	/**
	 * 根据用户id查询已经预定的订单
	 * @param userId
	 */
	public List<String> queryOrderByUser(String userId);
	
	/**
	 * 根据用户id选择日期预约订单
	 */
	public List submitOrderByUser();

	
	public List queryOrderTotal();
}
