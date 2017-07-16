package com.diamondboss.util.pay.weChatPay;

public class WXPayReFundDTO {
	
	/**
	 * 订单号
	 */
	private String outTradeNo;
	
	/**
	 * 订单金额
	 */
	private String totalFee;
	
	/**
	 * 退款金额
	 */
	private String refundFee;
	
	/**
	 * 回调地址
	 */
	private String notifyUrl;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	
}
