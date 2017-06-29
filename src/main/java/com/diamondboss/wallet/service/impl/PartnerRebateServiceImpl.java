package com.diamondboss.wallet.service.impl;

import com.diamondboss.wallet.repository.PartnerRebateMapper;
import com.diamondboss.wallet.service.PartnerRebateService;

/**
 * 合伙人返佣
 * 
 * @author John
 * @since 2017-06-28
 *  
 */
public class PartnerRebateServiceImpl implements PartnerRebateService{

	
	private PartnerRebateMapper partnerRebateMapper;
	
	@Override
	public void rebate() {
		
		partnerRebateMapper.insertPartnerWalletDetail(null);
		
		partnerRebateMapper.updatePartnerWallet(null);
		
	}

}
