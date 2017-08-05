package com.diamondboss.order.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.constants.PetInfoConstants;
import com.diamondboss.order.service.IGrabOrderService;
import com.diamondboss.order.vo.GrabOrderVo;
import com.diamondboss.util.vo.APPResponseBody;

@Controller
@RequestMapping("/grabOrder")
public class GrabOrderController {

	private static final Logger log = Logger.getLogger(GrabOrderController.class);
	
	@Autowired
	private IGrabOrderService grabOrderService;
	
	@ResponseBody
	@RequestMapping(value = "/grabOrder", method = RequestMethod.POST)
	public APPResponseBody grabOrder(GrabOrderVo vo){
		
		log.info("test git commit");
		log.info(PetInfoConstants.PARTNER_GRAB_ORDER);
		
		Integer i = grabOrderService.grabOrder(vo);
		APPResponseBody app = new APPResponseBody();
		app.setData("");
		if(i==0){
			app.setRetnDesc("抢单成功");
			app.setRetnCode(0);
		}else if(i==3){
			app.setRetnDesc("接单失败,当日接单数量已满");
			app.setRetnCode(1);
		}else if(i==2){
			app.setRetnDesc("接单失败,该订单已被抢");
			app.setRetnCode(1);
		}
		
		return app;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryOrder", method = RequestMethod.POST)
	public APPResponseBody queryOrder(GrabOrderVo vo){
		
//		log.info(PetInfoConstants.PARTNER_GRAB_ORDER);
		
		List<GrabOrderVo> list = grabOrderService.queryOrder(vo);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(list);
		app.setRetnCode(0);
		
		return app;
		
	}
	
}
