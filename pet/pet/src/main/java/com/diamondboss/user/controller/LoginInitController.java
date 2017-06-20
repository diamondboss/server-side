package com.diamondboss.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.user.service.LoginInitService;
import com.diamondboss.util.vo.APPResponseBody;

public class LoginInitController {

	private static final Logger log = Logger.getLogger(LoginInitController.class);
	
	private LoginInitService loginInitService;
	
	/**
	 * 首页查询获得该小区社区合伙人数量跟已预订宠物量
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
}
