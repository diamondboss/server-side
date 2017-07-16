package com.diamondboss.order.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.pojo.OrderUserPojo;
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
	 * 支付宝支付-用户下单-指定合伙人
	 * 
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/appoint", method = RequestMethod.POST)
	public APPResponseBody appoint(OrderUserVo vo) {
		
		APPResponseBody app = new APPResponseBody();
		int currentHour = LocalDateTime.now().getHour();
//		if (21 < currentHour || currentHour < 4){
//			app.setData("夜间21点至次日凌晨5点不可下单");
//			app.setRetnCode(1);
//			return app;
//		}
		OrderUserPojo pojo = vo.voToPojo(vo);
		
		boolean is = placeOrderService.appointPartner(pojo);
		
		if(is){
			
			// 签名生成订单信息
			AlipayOrderSubmitVo orderInfo = placeOrderService.combinationOrderInfo(pojo);
			
			app.setData(orderInfo);
			app.setRetnCode(0);
		}else{
			AlipayOrderSubmitVo orderIsNull = new AlipayOrderSubmitVo();
			app.setData(orderIsNull);
			app.setRetnDesc("该合伙人订单已满");
			app.setRetnCode(1);
		}
		
		return app;
	
	}
	
	/**
	 * 支付宝支付-用户下单-不指定合伙人
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/random", method = RequestMethod.POST)
	public APPResponseBody random(OrderUserVo vo) {
		
		APPResponseBody app = new APPResponseBody();
		int currentHour = LocalDateTime.now().getHour();
//		if (21 < currentHour || currentHour < 4){
//			app.setData("夜间21点至次日凌晨5点不可下单");
//			app.setRetnCode(1);
//			return app;
//		}	
		
		OrderUserPojo pojo = vo.voToPojo(vo);
		boolean is = placeOrderService.randomPartner(pojo);
		
		if(is){
			
			// 签名生成订单信息
			AlipayOrderSubmitVo orderInfo = placeOrderService.combinationOrderInfo(pojo);
			
			app.setData(orderInfo);
			app.setRetnCode(0);
		}else{
			AlipayOrderSubmitVo orderIsNull = new AlipayOrderSubmitVo();
			app.setData(orderIsNull);
			app.setRetnDesc("该合伙人订单已满");
			app.setRetnCode(1);
		}
		
		return app;
	
	}
	
	
	/**
	 * 微信支付-用户下单-指定合伙人
	 * 
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/appointWXPay", method = RequestMethod.POST)
	public APPResponseBody appointWXPay(OrderUserVo vo) {
		
		APPResponseBody app = new APPResponseBody();
		int currentHour = LocalDateTime.now().getHour();
//		if (21 < currentHour || currentHour < 4){
//			app.setData("夜间21点至次日凌晨5点不可下单");
//			app.setRetnCode(1);
//			return app;
//		}
		OrderUserPojo pojo = vo.voToPojo(vo);
		
		boolean is = placeOrderService.appointPartner(pojo);
		
		if(is){
			
			// 签名生成订单信息
			Map<String, Object> resultMap = placeOrderService.combinationOrderInfoWXPay(pojo);
			
			app.setData(resultMap);
			app.setRetnCode(0);
		}else{
			AlipayOrderSubmitVo orderIsNull = new AlipayOrderSubmitVo();
			app.setData(orderIsNull);
			app.setRetnDesc("该合伙人订单已满");
			app.setRetnCode(1);
		}
		
		return app;
	
	}
	
	/**
	 * 微信支付-用户下单-不指定合伙人
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/randomWXPay", method = RequestMethod.POST)
	public APPResponseBody randomWXPay(OrderUserVo vo) {
		
		APPResponseBody app = new APPResponseBody();
		int currentHour = LocalDateTime.now().getHour();
//		if (21 < currentHour || currentHour < 4){
//			app.setData("夜间21点至次日凌晨5点不可下单");
//			app.setRetnCode(1);
//			return app;
//		}	
		
		OrderUserPojo pojo = vo.voToPojo(vo);
		boolean is = placeOrderService.randomPartner(pojo);
		
		if(is){
			
			// 签名生成订单信息
			Map<String, Object> resultMap = placeOrderService.combinationOrderInfoWXPay(pojo);
			
			app.setData(resultMap);
			app.setRetnCode(0);
		}else{
			AlipayOrderSubmitVo orderIsNull = new AlipayOrderSubmitVo();
			app.setData(orderIsNull);
			app.setRetnDesc("该合伙人订单已满");
			app.setRetnCode(1);
		}
		
		return app;
	
	}
	
}



