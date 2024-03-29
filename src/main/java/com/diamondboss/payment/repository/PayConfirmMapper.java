package com.diamondboss.payment.repository;

import java.util.Map;

import com.diamondboss.order.pojo.OrderUserPojo;

public interface PayConfirmMapper {

	/**
	 * 查询订单状态
	 */
	public void queryOrderStatus(Map<String, Object> map);
	
	/**
	 * 更新订单状态
	 */
	public void updateOrderStatus(Map<String, Object> map);
	
	/**
	 * 更新订单状态-微信支付
	 */
	public int updateOrderStatusByWXPay(Map<String, Object> map);
	
	/**
	 * 根据id查询用户订单
	 */
	public OrderUserPojo queryUserOrderById(Map<String, Object> map);
	
	/**
	 * 根据交易Id查询订单状态
	 * @param tradeNo
	 * @return
	 */
	public String queryOrderState(String tradeNo);
}
