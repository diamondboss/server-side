package com.diamondboss.wallet.service;

import java.util.List;

import com.diamondboss.wallet.vo.PartnerTotalWalletVo;
import com.diamondboss.wallet.vo.PartnerWalletVo;
import com.diamondboss.wallet.vo.WalletSummaryVo;
import com.diamondboss.wallet.vo.WithdrawalsVo;

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
	public boolean withdrawals(WithdrawalsVo vo);
	
	/**
	 * 查询钱包汇总
	 */
	public WalletSummaryVo querySummaryInfo(String partnerId);
	
	/**
	 * 查询钱包明细
	 */
	public List<PartnerTotalWalletVo> queryDetailed(String partnerId);
	
	/**
	 * 查询钱包明细
	 */
	public List<PartnerTotalWalletVo> queryTotalDetailed(String partnerId); 
}
