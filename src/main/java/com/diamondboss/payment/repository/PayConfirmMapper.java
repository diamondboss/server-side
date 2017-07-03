package com.diamondboss.payment.repository;

import java.util.Map;

public interface PayConfirmMapper {

	/**
	 * 查询订单状态
	 */
	public void queryOrderStatus(Map<String, Object> map);
	
	/**
	 * 更新订单状态
	 */
	public void updateOrderStatus(Map<String, Object> map);
	
}
