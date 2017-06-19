package com.diamondboss.order.service;

import com.diamondboss.util.bo.GrabOrderBo;

public interface IGrabOrderService {

	/**
	 * 合伙人抢单
	 */
	public void grabOrder(GrabOrderBo grabOrder);
	
}
