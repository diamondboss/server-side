package com.personal.personal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.util.vo.APPResponseBody;

@Controller
@RequestMapping("/petInfo")
public class PetInfoController {

	
	/**
	 * 宠物信息录入
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/infoInput" ,method = RequestMethod.POST)
	public APPResponseBody infoInput(HttpServletRequest request) {
		
		String phoneNumber = request.getParameter("phoneNumber");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String age = request.getParameter("age");
		String weight = request.getParameter("weight");
		String type = request.getParameter("type");
		String varieties = request.getParameter("varieties");
		String habit = request.getParameter("habit");
		
		
		
		// 1.根据sessionId和code验证手机验证码
		
		
		
		
		// 2.登录
		
		APPResponseBody app = new APPResponseBody();
		app.setData("");
		app.setRetnCode(0);
		return app;
	}
	
}
