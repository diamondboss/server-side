package com.diamondboss.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.service.ISubmitOrderService;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 订单流程
 * @author Boowen
 *
 */
@Controller
@RequestMapping("/submitOrder")
public class SubmitOrderController {

	@Autowired
	private ISubmitOrderService submitOrderService;
	
	@ResponseBody
	@RequestMapping(value = "/queryOrder" ,method = RequestMethod.POST)
	private APPResponseBody queryOrderByUser(HttpServletRequest request){
		
		String petId = request.getParameter("petId");
		List<String> list= submitOrderService.queryOrderByUser(petId);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(list);
		app.setRetnCode(0);
		return app;
		
	}
}
