package com.diamondboss.petsHotel.service;

import java.util.List;

/**
 * 用户服务类
 * 
 * @author John
 * @since 2017-08-16
 *  
 */
public interface UserService {

	/**
	 * 查询酒店列表
	 * 
	 * @return 酒店列表
	 */
	public List<?> queryHotelList();
	
	/**
	 * 查询酒店价格
	 * 
	 * @param hotelId 酒店id
	 * @return 对应酒店价格列表
	 */
	public List<?> queryPrice(String hotelId);
}
