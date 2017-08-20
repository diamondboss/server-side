package com.diamondboss.petsHotel.service;

/**
 * 用户下单类
 * 
 * @author John
 * @since 2017-08-16
 *  
 */
public interface UserOrderService {

	/**
	 * 预约登记--支付宝
	 */
	public void placeOrderAli();
	
	/**
	 * 预约登记--微信
	 */
	public void placeOrderWx();
	
	/**
	 * 查询酒店用户订单
	 */
	public void queryOrder();
}
