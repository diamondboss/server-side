package com.diamondboss.petsHotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.petsHotel.repository.UserServiceMapper;
import com.diamondboss.petsHotel.service.UserService;
import com.diamondboss.petsHotel.vo.HotelListResponseVo;
import com.diamondboss.petsHotel.vo.HotelServiceResponseVo;

/**
 * 用户服务类
 * 
 * @author John
 * @since 2017-08-16
 *  
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserServiceMapper userService;

	/**
	 * 查询酒店列表
	 * 
	 * @return 酒店列表
	 */
	public List<HotelListResponseVo> queryHotelList(){
		return userService.queryHotelList();
	}
	
	/**
	 * 查询酒店价格
	 * 
	 * @param hotelId 酒店id
	 * @return 对应酒店价格列表
	 */
	public Map<String, Object> queryHotelService(String hotelId){
		Map<String, String> map = new HashMap<>();
		map.put("hotelId", hotelId);
		List<HotelServiceResponseVo> list = userService.queryHotelService(map);
		
		//对数据库中查询到的信息根据serviceId进行升序的排序(冒泡)
		for(int i = 0; i < list.size() - 1; i++){
			for(int j = 0; j < list.size() - i -1; j++){
				if(Integer.valueOf(list.get(j).getServiceId()) > Integer.valueOf(list.get(j + 1).getServiceId())){
					HotelServiceResponseVo vo = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, vo);
				}
			}
		}
		
		System.out.println(list);
		
		Map<String, Object> maps =  new HashMap<>();
		//第一条数据的服务Id
		String serviceId = list.get(0).getServiceId();
		maps.put(serviceId, "");
		
		for (HotelServiceResponseVo hotelServiceResponseVo : list) {
			if(StringUtils.equals(serviceId, hotelServiceResponseVo.getServiceId())){
				continue;
			}else{
				maps.put(hotelServiceResponseVo.getServiceId(), "");
			}
		}
		
		//第一条数据的服务Id
		String serviceId2 = list.get(0).getServiceId();
		List<HotelServiceResponseVo> hotelList = new ArrayList<>();
		for (HotelServiceResponseVo hotelServiceResponseVo : list) {
			if(maps.containsKey(serviceId2)){
				if(StringUtils.equals(serviceId2, hotelServiceResponseVo.getServiceId())){
					hotelList.add(hotelServiceResponseVo);
					continue;
				}else{
					maps.put(serviceId2, hotelList);
					hotelList = new ArrayList<>();
					hotelList.add(hotelServiceResponseVo);
					serviceId2 =  String.valueOf(Integer.valueOf(serviceId2) + 1);
				}
			}
		}
		maps.put(serviceId2, hotelList);
		
		System.out.println(maps);
		return maps;
	}
	
}
