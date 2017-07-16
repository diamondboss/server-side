package com.diamondboss.order.vo;

public class WXPayOrderSubmitVo {

	/**
	 * 应用ID
	 */
	private String appid;
	
	/**
	 * 商户号
	 */
	private String partnerid;
	
	/**
	 * 预支付交易会话ID
	 */
	private String prepayid;
	
	/**
	 * 扩展字段
	 */
	private final String packageField = "Sign=WXPay";
	
	/**
	 * 随机字符串
	 */
	private String noncestr;
	
	/**
	 * 时间戳
	 */
	private String timestamp;
	
	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 应用ID
	 * @return
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * 应用ID
	 * @param appid
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * 商户号
	 * @return
	 */
	public String getPartnerid() {
		return partnerid;
	}

	/**
	 * 商户号
	 * @param partnerid
	 */
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	/**
	 * 预支付交易会话ID
	 * @return
	 */
	public String getPrepayid() {
		return prepayid;
	}

	/**
	 * 预支付交易会话ID
	 * @param prepayid
	 */
	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	/**
	 * 随机字符串
	 * @return
	 */
	public String getNoncestr() {
		return noncestr;
	}

	/**
	 * 随机字符串
	 * @param noncestr
	 */
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	/**
	 * 时间戳
	 * @return
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * 时间戳
	 * @param timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 签名
	 * @return
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * 签名
	 * @param sign
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * 扩展字段
	 * @return
	 */
	public String getPackageField() {
		return packageField;
	}
	
	
}
