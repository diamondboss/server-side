package com.diamondboss.petsHotel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.diamondboss.petsHotel.service.UserService;
import com.diamondboss.petsHotel.vo.HotelListResponseVo;

/**
 * 用户服务类
 * 
 * @author John
 * @since 2017-08-16
 *  
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * 查询酒店列表
	 * 
	 * @return 酒店列表
	 */
	public List<HotelListResponseVo> queryHotelList(){
		
		
		return null;
	}
	
	/**
	 * 查询酒店价格
	 * 
	 * @param hotelId 酒店id
	 * @return 对应酒店价格列表
	 */
	public List<?> queryPrice(String hotelId){
		
		
		return null;
	}
	
}
