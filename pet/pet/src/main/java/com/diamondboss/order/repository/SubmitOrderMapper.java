package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.util.pojo.OrderUserPojo;

public interface SubmitOrderMapper {

	/**
	 * 根据用户id查询已经预定的订单
	 * @param map
	 * @return
	 */
	List<String> queryOrderByUser(Map<String, String> map);
	
	/**
	 * 根据小区跟天数查询合适的合伙人
	 * @param map
	 * @return
	 */
	List<String> queryParByComm(Map<String, String> map);

	/**
	 * 查询指定合伙人在指定日期的有效订单数
	 * @param map
	 * @return
	 */
	int queryCountsByPartnerAndDate(Map<String, Object> map);
	
	/**
	 * 插入合伙人订单
	 */
	void insertPartnerOrder(Map<String, Object> map);

	/**
	 * 插入用户订单
	 */
	void insertUserOrder(Map<String, Object> map);
	
	/**
	 * 查询订单
	 * @returnn
	 */
	int queryPartnerOrder();
	
	/**
	 * 修改合伙人订单
	 * @return
	 */
	int updatePartnerOrder(Map<String, Object> map);

	/**
	 * 修改用户订单
	 * @return
	 */
	int updateUserOrder(Map<String, Object> map);
	
	/********************************************************/
	/********************************************************/
	/********************************************************/
	/********************************************************/
	/********************************************************/
	
	// TODO
	
//	/**
//	 * 检查登录表是否有未接单的订单
//	 * @param userId 用户id
//	 * @return int 记录数量
//	 */
//	public int cheakUserLogin(String userId);
	
	/**
	 * 插入订单（用户）表
	 */
	public int insertOrderUser(OrderUserPojo pojo);
	
	/**
	 * 根据小区id查询合伙人表获取小区宠物饲养上限
	 */
	public int queryTotalByCommunityId(String community);
	
	/**
	 * 更新用户登录表(下单时间、下单数量、下单数量+1)
	 * @param userId
	 */
	public void updateUserLogin(Map<String, Object> map);
	
	/**
	 * 更新订单（用户）表
	 */
	public int updateOrderUser(OrderUserPojo pojo);
	
}
