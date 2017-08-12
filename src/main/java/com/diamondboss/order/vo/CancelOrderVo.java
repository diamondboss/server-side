package com.diamondboss.order.vo;

/**
 * 用户取消预约
 * 
 * @author John
 * @since 2017-08-10
 *  
 */
public class CancelOrderVo {

	/**
	 * 用户编号
	 */
	private String userId;
	
	/**
	 * 订单编号
	 */
	private String orderId;

	/**
	 * 用户编号
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户编号
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 订单编号
	 * @return
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 订单编号
	 * @param orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
