package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.pojo.RaiseNumberPojo;

/**
 * 用户下单
 * 
 * @author John
 * @since 2017-06-24
 *  
 */
public interface PlaceOrderMapper {

	/**
	 * 查询该合伙人是否满足条件
	 * 
	 * @param PartnerId 合伙人id
	 */
	public int queryPartnerCondition(String PartnerId);
	
	/**
	 * 查询指定合伙人在指定日期的有效订单数
	 * @param map
	 * @return
	 */
	public int queryCountsByPartnerAndDate(Map<String, Object> map);
	
	/**
	 * 插入用户订单
	 */
	public int insertUserOrder(OrderUserPojo pojo);
	
	/**
	 * 查询小区合伙人允许下单合伙人信息
	 * @param communityId
	 */
	public List<RaiseNumberPojo> queryTotalByCommunityId(String communityId);
	
	/**
	 * 查询合伙人接单数量
	 * @param communityId
	 */
	public int querNumByPartnerId(Map<String, Object> map);
	
	
	
}
