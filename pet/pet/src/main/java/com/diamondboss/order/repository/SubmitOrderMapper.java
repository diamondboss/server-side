package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

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
}
