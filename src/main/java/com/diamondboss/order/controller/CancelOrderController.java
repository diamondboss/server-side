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
		APPResponseBody app = new APPResponseBody();
		
		if(cancelOrderService.cancelOrder(vo)){
			app.setData("取消成功");
			app.setRetnCode(0);
			log.info("取消成功");
		}else{
			app.setData("取消失败");
			app.setRetnCode(1);
			log.info("取消失败");
		}
		return app;
		
	}
	
}