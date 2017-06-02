package com.diamondboss.personal.service.impl;

import com.diamondboss.personal.service.IPartnerWalletService;

/**
 * 合伙人钱包
 * @author 
 *
 */
public class PartnerWalletServiceImpl implements IPartnerWalletService{

	/**
	 * 合伙人首页-查询合伙人钱包
	 */
	@Override
	public void queryPartnerWallet(){
		
		// 根据获取合伙人信息查询合伙人id
		
		// 查询合伙人钱包
		
		// 根据合伙人id算出合伙人钱包明细表表明
		int id = 1000;// 假设的合伙人id
		int temp = (id/100) + 1;
		String  tableName = "partner_wallet_detail_" + temp;
		
		// 根据合伙人id查询合伙人钱包明细表
		
	}
	
}
