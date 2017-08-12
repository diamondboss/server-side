package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.order.pojo.OrderUserPojo;

/**
 * 用户取消预约
 * 
 * @author John
 * @since 2017-08-10
 *  
 */
public interface CancelOrderMapper {

	/**
	 * 根据订单编号跟用户id查询订单
	 * @param param
	 * @return
	 */
	public OrderUserPojo queryOrderById(Map<String, Object> param);
	
	/**
	 * 更新用户订单表
	 * @param param
	 * @return
	 */
	public int updateUserOrderById(Map<String, Object> param);
	
	/**
	 * 更新用户登录表 订单数-1
	 * @param param
	 * @return
	 */
	public int updateUserLoginByUserId(Map<String, Object> param);
	
	/**
	 * 根据用户订单小区id查询小区下合伙人
	 * @param param
	 * @return
	 */
	public List<String> queryPartnerId(Map<String, Object> param);
	
	/**
	 * 更新合伙人抢单表
	 * @param param
	 * @return
	 */
	public int updateGrabOrderByPartnerId(Map<String, Object> param);
	
	/**
	 * 更新合伙人订单表
	 * @param param
	 * @return
	 */
	public int updatePartnerOrder(Map<String, Object> param);
	
	
	
	
}
