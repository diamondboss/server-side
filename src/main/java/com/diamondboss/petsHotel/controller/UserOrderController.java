package com.diamondboss.petsHotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.vo.CancelOrderVo;
import com.diamondboss.petsHotel.service.UserOrderService;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 用户下单类
 * 
 * @author John
 * @since 2017-08-16
 *  
 */
@Controller
@RequestMapping("/userOrderHotel")
public class UserOrderController {
	
	private UserOrderService userOrder;

	/**
	 * 预约登记--支付宝
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/placeOrderAli", method = RequestMethod.POST)
	public APPResponseBody placeOrderAli(CancelOrderVo vo){
		userOrder.placeOrderAli();
		
		return null;
	}

	/**
	 * 预约登记--微信
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/placeOrderWx", method = RequestMethod.POST)
	public APPResponseBody placeOrderWx(CancelOrderVo vo){
		userOrder.placeOrderAli();
		
		return null;
	}
	
	/**
	 * 酒店用户查询订单
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryOrderForHotel", method = RequestMethod.POST)
	public APPResponseBody queryOrderForHotel(CancelOrderVo vo){
		userOrder.placeOrderAli();
		
		return null;
	}
}
