package com.diamondboss.wallet.service.impl;

import com.diamondboss.wallet.repository.CustomerServiceWithdrawalsMapper;
import com.diamondboss.wallet.service.CustomerServiceWithdrawalsService;

/**
 * 客服提现确认
 * 
 * @author John
 * @since 2017-06-28
 *  
 */
public class CustomerServiceWithdrawalsServiceImpl implements CustomerServiceWithdrawalsService{

	private CustomerServiceWithdrawalsMapper customerServiceWithdrawalsMapper;
	
	@Override
	public void confirm() {
		
		customerServiceWithdrawalsMapper.updatePartnerWalletDetail(null);
	}

}
