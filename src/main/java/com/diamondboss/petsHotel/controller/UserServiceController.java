package com.diamondboss.petsHotel.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.controller.CancelOrderController;
import com.diamondboss.order.vo.CancelOrderVo;
import com.diamondboss.petsHotel.service.UserService;
import com.diamondboss.petsHotel.vo.HotelListRequestVo;
import com.diamondboss.petsHotel.vo.HotelListResponseVo;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 用户服务类
 * 
 * @author John
 * @since 2017-08-16
 *  
 */
@Controller
@RequestMapping("/userServiceHotel")
public class UserServiceController {

	private static final Logger log = Logger.getLogger(UserServiceController.class);
	
	private UserService userService;
	
	
	/**
	 * 酒店列表查询
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryHotelList", method = RequestMethod.POST)
	public APPResponseBody queryHotelList(HotelListRequestVo vo){
		log.info("查询酒店列表");
		APPResponseBody app = new APPResponseBody();
		
		if(vo.getLongitude() == null || vo.getLatitude() == null){
			app.setRetnCode(1);
			app.setRetnDesc("参数非法");
			return app;
		}
		List<HotelListResponseVo> hotelList = userService.queryHotelList();
		
		List<HotelListResponseVo> hotel =  new ArrayList<>();
		for (HotelListResponseVo hotelListVo : hotelList) {
			if(Distance(Double.valueOf(vo.getLongitude()),Double.valueOf(hotelListVo.getLongitude()),
					Double.valueOf(vo.getLatitude()),Double.valueOf(hotelListVo.getLatitude())) < 5000){
				hotel.add(hotelListVo);
			}
		}
		app.setRetnCode(0);
		app.setData(hotel);
		return app;
	}
	
	/**
	 * 酒店服务类目列表。--日托--周托--月托--寄养
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryServiceList", method = RequestMethod.POST)
	public APPResponseBody queryServiceList(CancelOrderVo vo){
		
		userService.queryPrice("");
		return null;
	}
	
	/** 
	 * 计算地球上任意两点(经纬度)距离 
	 *  
	 * @param long1 
	 *            第一点经度 
	 * @param lat1 
	 *            第一点纬度 
	 * @param long2 
	 *            第二点经度 
	 * @param lat2 
	 *            第二点纬度 
	 * @return 返回距离 单位：米 
	 */  
	public static double Distance(double long1, double lat1, double long2,  
	        double lat2) {  
	    double a, b, R;  
	    R = 6378137; // 地球半径  
	    lat1 = lat1 * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = lat1 - lat2;  
	    b = (long1 - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);  
	    d = 2  
	            * R  
	            * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
	                    * Math.cos(lat2) * sb2 * sb2));  
	    return d;  
	}  
}
