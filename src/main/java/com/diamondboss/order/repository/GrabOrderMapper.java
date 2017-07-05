package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.order.pojo.GrabOrderPojo;
import com.diamondboss.order.vo.GrabOrderVo;

public interface GrabOrderMapper {

	/**
	 * 根据合伙人id查询合伙人抢单信息
	 */
	public List<GrabOrderVo> queryGrabOrderByPartnerId(Map<String, Object> param);
	
	/**
	 * 根据id查询用户抢单信息
	 */
	public GrabOrderPojo queryGrabOrderUserId(Map<String, Object> param);
	
	/**
	 * 根据id更新用户抢单信息
	 */
	public void updateGrabOrderUserId(Map<String, Object> param);
	
	/**
	 * 更新用户登录表
	 * @return
	 */
	public int updateUserLogin(String userId);
	
	/**
	 * 更新用户订单表
	 * @return
	 */
	public int updateOrderUser(GrabOrderPojo pojo);
	
	/**
	 * 插入合伙人订单表
	 * @return
	 */
	public int insertOrderPartner(GrabOrderPojo pojo);
	
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
	public int querySelfOrderNum(Map<String, String> map);
}
