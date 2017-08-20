package com.diamondboss.petsHotel.controller;

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
public class UserServiceController {

	private UserService userService;
	
	
	/**
	 * 酒店列表查询
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public APPResponseBody queryHotelList(CancelOrderVo vo){
		userService.queryHotelList();
		return null;
	}
	
	/**
	 * 酒店价格显示
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public APPResponseBody queryPrice(CancelOrderVo vo){
		
		userService.queryPrice("");
		return null;
	}
}
