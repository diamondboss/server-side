package com.diamondboss.timer.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.order.pojo.UserOrderListPojo;

public interface AutoAllocationOrderMapper {

	/**
	 * 扫描是否有没处理的用户订单
	 */
	public List<String> scanUserOrder();
	
	/**
	 * 根据用户id查询未完成的订单
	 * @return
	 */
	public List<UserOrderListPojo> queryOrderByUserId(Map<String, Object> param);
	
	/**
	 * 插入合伙人订单表
	 * @param param
	 * @return
	 */
	public int insertPartnerOrder(Map<String, Object> param);
}
