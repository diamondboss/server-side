package com.diamondboss.order.service;

import com.diamondboss.util.pojo.PartnerWalletPojo;

/**
 * 合伙人钱包
 * @author 
 *
 */
public interface IPartnerWalletService {

	/**
	 * 合伙人首页-查询合伙人钱包
	 */
	public PartnerWalletPojo queryPartnerWalletAmount(String partnerId);
}
