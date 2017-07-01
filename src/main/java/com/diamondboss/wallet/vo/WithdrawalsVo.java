package com.diamondboss.wallet.vo;

public class WithdrawalsVo {

	/**
	 * 合伙人id
	 */
	private String partnerId;
	
	/**
	 * 提现金额
	 */
	private String value;
	
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
	 * 提现金额
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 提现金额
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
