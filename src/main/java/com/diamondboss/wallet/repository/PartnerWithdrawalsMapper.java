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

	public PartnerWalletPojo queryPartnerWallet(PartnerWalletPojo Pojo);
	
	public int updatePartnerWallet(PartnerWalletPojo Pojo);
	
	public List<PartnerWalletPojo> queryPartnerWalletDetailed(PartnerWalletPojo Pojo);
	
//	public int updatePartnerWalletDetailed(PartnerWalletPojo Pojo);
	
	public int insertPartnerWalletDetailed(PartnerWalletPojo Pojo);
}
