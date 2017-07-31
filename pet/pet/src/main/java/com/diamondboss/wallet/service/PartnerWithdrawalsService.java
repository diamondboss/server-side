package com.diamondboss.wallet.service;

import java.util.List;

import com.diamondboss.wallet.vo.PartnerTotalWalletVo;
import com.diamondboss.wallet.vo.PartnerWalletVo;

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
	public List<PartnerWalletVo> queryDetailed(String partnerId);
	
	/**
	 * 查询钱包明细
	 */
	public List<PartnerTotalWalletVo> queryTotalDetailed(String partnerId); 
}
