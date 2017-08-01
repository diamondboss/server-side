package com.diamondboss.wallet.vo;

public class WalletSummaryVo {

	/**
	 * 可用余额
	 */
	private String availableBalance;
	
	/**
	 * 真实余额
	 */
	private String realBalance;

	/**
	 * 今日收益
	 */
	private String earningsToday;

	
	public String getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getRealBalance() {
		return realBalance;
	}

	public void setRealBalance(String realBalance) {
		this.realBalance = realBalance;
	}

	public String getEarningsToday() {
		return earningsToday;
	}

	public void setEarningsToday(String earningsToday) {
		this.earningsToday = earningsToday;
	}
	
	
}
