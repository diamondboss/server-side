package com.diamondboss.order.repository;

import com.diamondboss.util.pojo.OrderPartnerPojo;
import com.diamondboss.util.pojo.OrderUserPojo;

public interface GrabOrderMapper {

	/**
	 * 更新用户登录表
	 * @return
	 */
	public int updateUserLogin(String userId);
	
	/**
	 * 更新用户订单表
	 * @return
	 */
	public int updateOrderUser(OrderUserPojo pojo);
	
	/**
	 * 更新合伙人订单表
	 * @return
	 */
	public int insertOrderPartner(OrderPartnerPojo pojo);
	
	/**
	 * 查询合伙人能容纳数量
	 * @return
	 */
	public int querySelfOrdertotal(String partnerId);
	
	/**
	 * 查询合伙人接单数量
	 * @param partnerId
	 * @param date
	 * @return
	 */
	public int querySelfOrderNum(String partnerId, String date);
}
