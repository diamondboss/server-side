package com.diamondboss.order.vo;

/**
 * 用户下单
 * 
 * @author John
 * @since 2017-06-27
 *  
 */
public class AlipayOrderSubmitVo {

	/**
	 * 订单信息
	 */
	private String orderInfo;
	
	/**
	 * 订单金额
	 */
	private String orderAmt;
	
	/**
	 * 支付宝支付签名
	 */
	private String alipaySign;

	/**
	 * 订单信息
	 * @return
	 */
	public String getOrderInfo() {
		return orderInfo;
	}

	/**
	 * 订单信息
	 * @param orderInfo
	 */
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	/**
	 * 订单金额
	 * @return
	 */
	public String getOrderAmt() {
		return orderAmt;
	}

	/**
	 * 订单金额
	 * @param orderAmt
	 */
	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}

	/**
	 * 支付宝支付签名
	 * @return
	 */
	public String getAlipaySign() {
		return alipaySign;
	}

	/**
	 * 支付宝支付签名
	 * @param alipaySign
	 */
	public void setAlipaySign(String alipaySign) {
		this.alipaySign = alipaySign;
	}

}
