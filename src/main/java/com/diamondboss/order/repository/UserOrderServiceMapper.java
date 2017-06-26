package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.util.vo.PartnerOrderServiceVo;
import com.diamondboss.util.vo.UserOrderServiceVo;
import com.diamondboss.util.vo.UserOrdersServiceVo;

public interface UserOrderServiceMapper {

	/**
	 * 查询用户当日订单
	 * @param map
	 * @return
	 */
	UserOrderServiceVo queryUserOrderService(Map<String, String> map);
	
	/**
	 * 查询合伙人当日订单
	 * @param map
	 * @return
	 */
	PartnerOrderServiceVo queryPartnerOrderService(Map<String, String> map);
	
	/**
	 * 查询用户的当日合伙人下的宠物订单
	 * @param map
	 * @return
	 */
	List<UserOrdersServiceVo> queryUserOrders(Map<String, Object> map);
}
