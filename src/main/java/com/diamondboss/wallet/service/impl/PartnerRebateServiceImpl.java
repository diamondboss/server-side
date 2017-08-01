package com.diamondboss.wallet.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.wallet.pojo.PartnerWalletPojo;
import com.diamondboss.wallet.repository.PartnerRebateMapper;
import com.diamondboss.wallet.service.PartnerRebateService;

/**
 * 合伙人返佣
 * 
 * @author John
 * @since 2017-06-28
 *  
 */
@Service
public class PartnerRebateServiceImpl implements PartnerRebateService{

	@Autowired
	private PartnerRebateMapper partnerRebateMapper;
	
	@Override
	public void rebate(OrderUserPojo pojo, boolean flag) {
		
		PartnerWalletPojo wallet = new PartnerWalletPojo();
		wallet.setPartnerId(pojo.getPartnerId());
		if(flag){
			wallet.setAmt(pojo.getAmt().multiply(new BigDecimal("0.8")));
		}else{
			wallet.setAmt(pojo.getAmt());
		}
		
		wallet.setKind("1");
		wallet.setOrderDate(pojo.getOrderDate());
		wallet.setPartnerWalletDetail(TableUtils.getOrderTableName(
				Long.valueOf(pojo.getPartnerId()), PetConstants.PARTNER_WALLET_DETAIL));
		
		partnerRebateMapper.insertPartnerWalletDetail(wallet);
		
		int i = partnerRebateMapper.updatePartnerWallet(wallet);
		if(i==0){
			partnerRebateMapper.insertPartnerWallet(wallet);
		}
		
	}

}
