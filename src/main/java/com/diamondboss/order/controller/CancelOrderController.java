package com.diamondboss.order.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.service.CancelOrderService;
import com.diamondboss.order.vo.CancelOrderVo;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 用户取消预约
 * 
 * @author John
 * @since 2017-08-10
 *  
 */
@Controller
@RequestMapping("/cancel")
public class CancelOrderController {

	private static final Logger log = Logger.getLogger(CancelOrderController.class);
	
	@Autowired
	private CancelOrderService cancelOrderService;
	
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public APPResponseBody cancel(CancelOrderVo vo){
		log.info("取消订单开始");
		return cancelOrderService.cancelOrder(vo);
	}
	
}