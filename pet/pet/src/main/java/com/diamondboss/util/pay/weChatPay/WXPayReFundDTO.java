package com.diamondboss.util.pay.weChatPay;

import java.math.BigDecimal;

public class WXPayReFundDTO {
	
	/**
	 * 用户Id
	 */
	private String userId;

	/**
	 * 订单号
	 */
	private String outTradeNo;
	
	/**
	 * 订单金额
	 */
	private BigDecimal totalFee;
	
	/**
	 * 退款金额
	 */
	private BigDecimal refundFee;
	
	/**
	 * 回调地址
	 */
	private String notifyUrl;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	
}
