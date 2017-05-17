package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

public interface SubmitOrderMapper {

	List<String> queryOrderByUser(Map<String, String> map);
	
	/**
	 * 根据小区跟天数查询合适的合伙人
	 * @param map
	 * @return
	 */
	List<String> queryParByComm(Map<String, String> map);
	
	void insertParterOrder();
	
	int queryParterOrder();
	
	int updateParterOrder();
}
