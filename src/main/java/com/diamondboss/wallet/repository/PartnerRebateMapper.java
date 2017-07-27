package com.diamondboss.wallet.repository;

import com.diamondboss.wallet.pojo.PartnerWalletPojo;

/**
 * 合伙人返佣
 * 
 * @author John
 * @since 2017-06-29
 *  
 */
public interface PartnerRebateMapper {

	/**
	 * 返佣
	 * @return
	 */
	public int updatePartnerWallet(PartnerWalletPojo Pojo);
	
	/**
	 * 返佣
	 * @return
	 */
	public int insertPartnerWalletDetail(PartnerWalletPojo Pojo);
	
	/**
	 * 返佣
	 * @param Pojo
	 * @return
	 */
	public int insertPartnerWallet(PartnerWalletPojo Pojo);
	
}
