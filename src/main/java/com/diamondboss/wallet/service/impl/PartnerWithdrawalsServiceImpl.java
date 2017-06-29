package com.diamondboss.wallet.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.diamondboss.wallet.pojo.PartnerWalletPojo;
import com.diamondboss.wallet.repository.PartnerWithdrawalsMapper;
import com.diamondboss.wallet.service.PartnerWithdrawalsService;

/**
 * 合伙人提现
 * 
 * @author John
 * @since 2017-06-28
 *  
 */
public class PartnerWithdrawalsServiceImpl implements PartnerWithdrawalsService{

	private PartnerWithdrawalsMapper partnerWithdrawalsMapper;
	
	/**
	 * 提现
	 */
	@Override
	public void withdrawals() {
		
		BigDecimal value = new BigDecimal("0");
		
		String date = LocalDate.now().toString(); 
		 
		// 查询可提现额度
		PartnerWalletPojo pojo = partnerWithdrawalsMapper.queryPartnerWallet(null);
		if(pojo == null || pojo.getAmt() == null){
			pojo = new PartnerWalletPojo();
			pojo.setAmt(new BigDecimal("0"));
		}
		
		
		List<PartnerWalletPojo> pojoList = 
				partnerWithdrawalsMapper.queryPartnerWalletDetailed(null);
		BigDecimal unavailable = new BigDecimal("0");
		for(PartnerWalletPojo i:pojoList){
			unavailable = unavailable.add(i.getAmt());
		}
		
		BigDecimal quota = pojo.getAmt().subtract(unavailable);
		
		
		if(value.compareTo(quota) < 1){
			
		}
		
		
		
		
		partnerWithdrawalsMapper.insertPartnerWalletDetailed(null);
		
		partnerWithdrawalsMapper.updatePartnerWallet(null);
	}

	/**
	 * 查询钱包汇总
	 */
	@Override
	public String querySummaryInfo(String partnerId) {
		
		PartnerWalletPojo pojo = partnerWithdrawalsMapper.queryPartnerWallet(partnerId);
		if(pojo == null || pojo.getAmt() == null){
			return "0";
		}else{
			return pojo.getAmt().toString();
		}
	}

	/**
	 * 查询钱包明细
	 */
	@Override
	public void queryDetailed() {
		
		partnerWithdrawalsMapper.queryPartnerWalletDetailed(null);
	}

}
