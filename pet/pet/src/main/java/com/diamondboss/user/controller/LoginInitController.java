package com.diamondboss.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.user.service.LoginInitService;
import com.diamondboss.util.vo.APPResponseBody;

public class LoginInitController {

	private static final Logger log = LogManager.getLogger(LoginInitController.class);
	
	private LoginInitService loginInitService;
	
	/**
	 * 查询进行中的订单
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryInfo" ,method = RequestMethod.POST)
	public APPResponseBody queryOrderInHand(HttpServletRequest request) {

		String userId = ""; // 合伙人id
		
		// 查询用户正在进行中的订单
		loginInitService.queryOrderInHand(userId);
		
		APPResponseBody app = new APPResponseBody();
		app.setData("");
		app.setRetnCode(0);
		return app;
	}
	
	/**
	 * 首页查询获得该小区社区合伙人
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryInfo" ,method = RequestMethod.POST)
	public APPResponseBody queryPartnerInCommunity(HttpServletRequest request) {

		Object o = ""; // 小区信息
		
		// 获取指定地址小区的合伙人
		List<String> list = loginInitService.queryPartnerInCommunity(o);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(list);
		app.setRetnCode(0);
		return app;
	}
	
	/**
	 * 获取指定合伙人的明细信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryInfo" ,method = RequestMethod.POST)
	public APPResponseBody queryPartnerDetail(HttpServletRequest request) {

		String partnerId = ""; // 合伙人id
		
		// 获取指定合伙人的明细信息
		loginInitService.queryPartnerDetail(partnerId);
		
		APPResponseBody app = new APPResponseBody();
		app.setData("");
		app.setRetnCode(0);
		return app;
	}
	
	
}
