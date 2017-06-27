package com.diamondboss.order.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.service.PlaceOrderService;
import com.diamondboss.order.vo.AlipayOrderSubmitVo;
import com.diamondboss.order.vo.OrderUserVo;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 用户下单
 * 
 * @author John
 * @since 2017-06-24
 *  
 */
@Controller
@RequestMapping("/placeOrder")
public class PlaceOrderController {
	
	@Autowired
	private PlaceOrderService placeOrderService;
	
	/**
	 * 用户下单-指定合伙人
	 * 
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/appoint", method = RequestMethod.POST)
	public APPResponseBody appoint(OrderUserVo vo) {
		
		APPResponseBody app = new APPResponseBody();
		int currentHour = LocalDateTime.now().getHour();
		if (21 < currentHour || currentHour < 4){
			app.setData("夜间21点至次日凌晨5点不可下单");
			app.setRetnCode(1);
			return app;
		}
		
		boolean is = placeOrderService.appointPartner(vo);
		
		if(is){
			
			// 签名生成订单信息
			AlipayOrderSubmitVo orderInfo = placeOrderService.combinationOrderInfo(vo);
			
			app.setData(orderInfo);
			app.setRetnCode(0);
		}else{
			app.setData("该合伙人订单已满");
			app.setRetnCode(1);
		}
		
		return app;
	
	}
	
	/**
	 * 用户下单-不指定合伙人
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/random", method = RequestMethod.POST)
	public APPResponseBody random(OrderUserVo vo) {
		
		APPResponseBody app = new APPResponseBody();
		int currentHour = LocalDateTime.now().getHour();
		if (21 < currentHour || currentHour < 4){
			app.setData("夜间21点至次日凌晨5点不可下单");
			app.setRetnCode(1);
			return app;
		}	
		
		boolean is = placeOrderService.randomPartner(vo);
		
		if(is){
			
			// 签名生成订单信息
			AlipayOrderSubmitVo orderInfo = placeOrderService.combinationOrderInfo(vo);
			
			app.setData(orderInfo);
			app.setRetnCode(0);
		}else{
			app.setData("该合伙人订单已满");
			app.setRetnCode(1);
		}
		
		return app;
	
	}
}