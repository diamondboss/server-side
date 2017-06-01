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
	 * 插入订单
	 */
	void insertParterOrder();
	
	/**
	 * 查询订单
	 * @return
	 */
	int queryParterOrder();
	
	/**
	 * 查询订单
	 * @return
	 */
	int updateParterOrder();
}
