package com.diamondboss.order.repository;

import java.util.Map;

import com.diamondboss.order.pojo.OrderUserPojo;

public interface DistributeOrderMapper {

	
	/**
	 * 插入合伙人订单信息
	 * 
	 * @param pojo
	 * @return
	 */
	public int insertOrderPartner(OrderUserPojo pojo);
	
	/**
	 * 更新用户订单信息
	 * 
	 * @param pojo
	 * @return
	 */
	public int updateOrderUser(OrderUserPojo pojo);
	
	/**
	 * 插入合伙人抢单信息
	 * @param pojo
	 * @return
	 */
	public int insertGrabOrderInfo(Map<String, Object> param);
}
