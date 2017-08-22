package com.diamondboss.petsHotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.controller.CancelOrderController;
import com.diamondboss.order.vo.CancelOrderVo;
import com.diamondboss.petsHotel.service.UserService;
import com.diamondboss.petsHotel.vo.HotelListRequestVo;
import com.diamondboss.petsHotel.vo.HotelListResponseVo;
import com.diamondboss.petsHotel.vo.HotelServiceRequestVo;
import com.diamondboss.petsHotel.vo.HotelServiceResponseVo;
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
	
	//地球半径
	private static double EARTH_RADIUS = 6371.393;
	
	@Autowired
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
		
		if(StringUtils.isBlank(vo.getLongitude())|| StringUtils.isBlank(vo.getLatitude())){
			app.setRetnCode(1);
			app.setRetnDesc("参数非法");
			return app;
		}
		List<HotelListResponseVo> hotelList = userService.queryHotelList();
		
		List<HotelListResponseVo> hotel =  new ArrayList<>();
		for (HotelListResponseVo hotelListVo : hotelList) {
			double distance = GetDistance(Double.valueOf(vo.getLatitude()),Double.valueOf(vo.getLongitude()),
					Double.valueOf(hotelListVo.getLatitude()),Double.valueOf(hotelListVo.getLongitude()));
			if(distance < 5000){
				hotelListVo.setDistance(String.valueOf(distance));
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
	public APPResponseBody queryServiceList(HotelServiceRequestVo vo){
		log.info("查询酒店类目详情");
		APPResponseBody app = new APPResponseBody();
		if(StringUtils.isBlank(vo.getHotelId())){
			app.setRetnCode(1);
			app.setRetnDesc("参数非法");
			return app;
		}
		
		Map<String, Object> responsemap = userService.queryHotelService(vo.getHotelId());
		
		
		
		app.setRetnCode(0);
		app.setData(responsemap);
		return app;
	}
	
	
	
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 计算两个经纬度之间的距离
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 1000);
		return s;
	}
}
