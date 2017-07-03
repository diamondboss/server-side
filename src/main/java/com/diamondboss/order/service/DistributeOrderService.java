package com.diamondboss.order.service;

import com.diamondboss.order.pojo.OrderUserPojo;

/**
 * 订单分配
 * 
 * @author John
 * @since 2017-06-26
 *  
 */
public interface DistributeOrderService {

	public void DistributeOrder(OrderUserPojo pojo);
	
	
}
