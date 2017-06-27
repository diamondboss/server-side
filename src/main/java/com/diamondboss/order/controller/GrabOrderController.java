package com.diamondboss.order.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diamondboss.constants.PetInfoConstants;
import com.diamondboss.order.service.IGrabOrderService;
import com.diamondboss.util.bo.GrabOrderBo;

@Controller
@RequestMapping("/grabOrder")
public class GrabOrderController {

	private static final Logger log = LogManager.getLogger(GrabOrderController.class);
	
	@Autowired
	private IGrabOrderService grabOrderService;
	
	public void grabOrder(){
		
		log.info(PetInfoConstants.PARTNER_GRAB_ORDER);
		
//		GrabOrderBo bo = new GrabOrderBo("","","","");
		GrabOrderBo bo = new GrabOrderBo();
		
		grabOrderService.grabOrder(bo);
		
	}
	
	
}
