package com.diamondboss.wallet.service;

import com.diamondboss.order.pojo.OrderUserPojo;

/**
 * 合伙人返佣
 * 
 * @author John
 * @since 2017-06-28
 *  
 */
public interface PartnerRebateService {
	
	/**
	 * 返佣
	 */
	public void rebate(OrderUserPojo pojo);
	
}
