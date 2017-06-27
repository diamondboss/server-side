package com.diamondboss.order.repository;

import com.diamondboss.order.pojo.OrderPartnerPojo;
import com.diamondboss.order.pojo.OrderUserPojo;

public interface DistributeOrderMapper {

	
	/**
	 * 插入合伙人订单信息
	 * 
	 * @param pojo
	 * @return
	 */
	public int insertOrderPartner(OrderPartnerPojo pojo);
	
	/**
	 * 更新用户订单信息
	 * 
	 * @param pojo
	 * @return
	 */
	public int updateOrderUser(OrderUserPojo pojo);
	
}
