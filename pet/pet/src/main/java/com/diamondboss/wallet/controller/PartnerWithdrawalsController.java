package com.diamondboss.wallet.controller;

import com.diamondboss.wallet.service.PartnerWithdrawalsService;
import com.diamondboss.wallet.vo.PartnerWalletVo;

/**
 * 合伙人提现
 * 
 * @author John
 * @since 2017-06-29
 *  
 */
public class PartnerWithdrawalsController {

	private PartnerWithdrawalsService withdrawalsService;
	
	/**
	 * 提现
	 */
	public void withdrawals(PartnerWalletVo vo){
		
		withdrawalsService.withdrawals();
	}
	
	/**
	 * 查询钱包汇总
	 */
	public void querySummaryInfo(String partnerId){
		
		withdrawalsService.querySummaryInfo(partnerId);
	}
	
	/**
	 * 查询钱包明细
	 */
	public void queryDetailed(PartnerWalletVo vo){
		
		withdrawalsService.queryDetailed();
	}
	
}
