package com.diamondboss.petsHotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.vo.CancelOrderVo;
import com.diamondboss.petsHotel.service.UserService;
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

	private UserService userService;
	
	
	/**
	 * 酒店列表查询
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryHotelList", method = RequestMethod.POST)
	public APPResponseBody queryHotelList(CancelOrderVo vo){
		userService.queryHotelList();
		
		return null;
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
}
