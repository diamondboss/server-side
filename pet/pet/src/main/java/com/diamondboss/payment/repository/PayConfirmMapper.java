package com.diamondboss.payment.repository;

public interface PayConfirmMapper {

	/**
	 * 查询订单状态
	 */
	public void queryOrderStatus();
	
	/**
	 * 更新订单状态
	 */
	public void updateOrderStatus();
	
}
