package com.diamondboss.wallet.service;

/**
 * 合伙人提现
 * 
 * @author John
 * @since 2017-06-28
 *  
 */
public interface PartnerWithdrawalsService {

	/**
	 * 提现
	 */
	public void withdrawals();
	
	/**
	 * 查询钱包汇总
	 */
	public String querySummaryInfo(String partnerId);
	
	/**
	 * 查询钱包明细
	 */
	public void queryDetailed(String partnerId);
	
}
