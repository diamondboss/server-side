package com.diamondboss.wallet.repository;

import java.util.List;

import com.diamondboss.wallet.pojo.PartnerWalletPojo;

/**
 * 合伙人提现
 * 
 * @author John
 * @since 2017-06-29
 *  
 */
public interface PartnerWithdrawalsMapper {

	/**
	 * 根据合伙人id查询钱包汇总
	 * @param partnerId
	 * @return
	 */
	public PartnerWalletPojo queryPartnerWallet(String partnerId);
	
	/**
	 * 根据合伙人id更新合伙人钱包
	 * @param Pojo
	 * @return
	 */
	public int updatePartnerWallet(PartnerWalletPojo Pojo);
	
	/**
	 * 根据合伙人id查询钱包明细
	 * @param Pojo
	 * @return
	 */
	public List<PartnerWalletPojo> queryPartnerWalletDetailed(String partnerId);
	
	/**
	 * 根据合伙人id插入合伙人钱包明细
	 * @param Pojo
	 * @return
	 */
	public int insertPartnerWalletDetailed(PartnerWalletPojo Pojo);
}
