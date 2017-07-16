package com.diamondboss.util.vo;

/**
 * 检查前台微信支付结果请求
 * @author xzf
 *
 */
public class CheckWXPayResultVo {
	/**
	 * 外部订单号（微信）
	 */
	private String outTradeNo;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
}
