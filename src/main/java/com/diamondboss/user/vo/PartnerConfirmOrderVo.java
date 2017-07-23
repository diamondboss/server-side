package com.diamondboss.user.vo;

public class PartnerConfirmOrderVo {

	public PartnerConfirmOrderVo(){
		
	}
	
	/**
	 * 合伙人id
	 */
	private String partnerId;
	
	/**
	 * 用户id
	 */
	private String userId;

	private String outTradeNo;

	/**
	 * 合伙人id
	 * @return
	 */
	public String getPartnerId() {
		return partnerId;
	}

	/**
	 * 合伙人id
	 * @param partnerId
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * 用户id
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	
}
