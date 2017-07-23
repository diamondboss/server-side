package com.diamondboss.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.user.service.PartnerConfirmOrderService;
import com.diamondboss.user.vo.PartnerConfirmOrderVo;
import com.diamondboss.util.vo.APPResponseBody;

@Controller
@RequestMapping("/confirmOrder")
public class PartnerConfirmOrderController {

	@Autowired
	private PartnerConfirmOrderService confirmOrderService;
	
	@ResponseBody
	@RequestMapping(value = "/receive", method = RequestMethod.POST)
	public APPResponseBody receive(PartnerConfirmOrderVo vo,
			HttpServletRequest request){
		
		confirmOrderService.receive(vo);
		
		APPResponseBody app = new APPResponseBody();
		app.setRetnCode(0);
		app.setData("");
		return app;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giveBack", method = RequestMethod.POST)
	public APPResponseBody giveBack(PartnerConfirmOrderVo vo,
			HttpServletRequest request){
		
		confirmOrderService.giveBack(vo);
		
		APPResponseBody app = new APPResponseBody();
		app.setRetnCode(0);
		app.setData("");
		return app;
	}
}
