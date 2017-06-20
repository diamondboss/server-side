package com.diamondboss.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.util.vo.APPResponseBody;

public class PartnerInfoController {


	/**
	 * 查询进行中的订单
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryInfo" ,method = RequestMethod.POST)
	public APPResponseBody query(HttpServletRequest request) {
		
		APPResponseBody app = new APPResponseBody();
		app.setData("");
		app.setRetnCode(0);
		return app;
		
	}
	
}
