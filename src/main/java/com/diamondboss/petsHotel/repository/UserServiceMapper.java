package com.diamondboss.petsHotel.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.petsHotel.vo.HotelListResponseVo;
import com.diamondboss.petsHotel.vo.HotelServiceResponseVo;

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
	
	/**
	 * 查询酒店服务类目
	 * @param map
	 * @return
	 */
	public List<HotelServiceResponseVo> queryHotelService(Map<String, String> map);
}
