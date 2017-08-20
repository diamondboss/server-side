package com.diamondboss.petsHotel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.vo.CancelOrderVo;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 酒店服务类
 * 
 * @author John
 * @since 2017-08-16
 *  
 */
public class HotelServiceController {

	/**
	 * 订单查询
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public APPResponseBody queryOrderList(CancelOrderVo vo){

		return null;
	}
}
