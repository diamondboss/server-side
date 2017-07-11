package com.diamondboss.order.service;

import java.util.Map;
import java.util.Objects;
import com.diamondboss.order.vo.UserOrderListVo;
import com.diamondboss.util.vo.PartnerOrderServiceVo;

/**
 * 小区订单
 * @author xzf
 *
 */
public interface IOrderService {
	
	/**
	 * 查询用户的实时名单
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, Object> queryUserOrderService(String userId, String orderDate);
	
	/**
	 * 查询合伙人的实时名单
	 * 
	 * @param request
	 * @return
	 */
	public PartnerOrderServiceVo queryPartnerOrderService(String partnerId);
	
	/**
	 * 根据合伙人Id查询合伙人的当日的订单数量
	 * @param partnerId
	 * @return
	 */
	public Map<String, String> NumByPartnerOrder(String partnerId);

	/**
	 *
	 * @param orderId
	 * @param params
	 */
	public void synchronizedPaymentResult(String orderId, Map<String, Objects> params);
	
	/**
	 * 查询用户订单列表
	 * 
	 * @param userId
	 * @return 用户订单列表
	 */
	public UserOrderListVo queryUserOrderList(String userId);
	
	/**
	 * 查询合伙人订单列表
	 * 
	 * @param userId
	 * @return 用户订单列表
	 */
	public UserOrderListVo queryPartnerOrderList(String partnerId);
	
}
