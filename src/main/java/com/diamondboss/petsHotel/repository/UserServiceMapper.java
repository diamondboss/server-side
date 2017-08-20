package com.diamondboss.petsHotel.repository;

import java.util.List;

import com.diamondboss.petsHotel.vo.HotelListResponseVo;

/**
 * 用户服务类
 * 
 * @author John
 * @since 2017-08-16
 *  
 */
public interface UserServiceMapper {
	
	/**
	 * 查询酒店列表
	 * @return
	 */
	public List<HotelListResponseVo> queryHotelList();
}
