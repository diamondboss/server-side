package com.diamondboss.order.service;

import java.util.Map;

/**
 * 合伙人钱包
 * @author 
 *
 */
public interface IPartnerWalletService {

	/**
	 * 合伙人首页-查询合伙人钱包
	 */
	public Map<String, Object> queryPartnerWalletAmount(String partnerId);
}
