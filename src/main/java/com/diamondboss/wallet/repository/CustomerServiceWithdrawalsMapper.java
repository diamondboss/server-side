package com.diamondboss.wallet.repository;

import com.diamondboss.wallet.pojo.PartnerWalletPojo;

/**
 * 客服提现确认
 * 
 * @author John
 * @since 2017-06-29
 *  
 */
public interface CustomerServiceWithdrawalsMapper {

	/**
	 * 客服提现确认
	 * @return
	 */
	public int updatePartnerWalletDetail(PartnerWalletPojo Pojo);
	
}
