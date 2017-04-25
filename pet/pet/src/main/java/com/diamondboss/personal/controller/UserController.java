package com.diamondboss.personal.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.diamondboss.util.pojo.UserInfoPojo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.personal.service.IUserService;
import com.diamondboss.util.vo.APPResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

	 @Resource  
	 private IUserService userService;
	/**
	 * 用户登录模块
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login" ,method = RequestMethod.POST)
	public APPResponseBody userLogin(HttpServletRequest request) {
		
		String phoneNumber = request.getParameter("phoneNumber");
		String sessionId = request.getParameter("sessionId");
		String code = request.getParameter("code");
	
		// 1.根据sessionId和code验证手机验证码
		System.out.println(phoneNumber);
		System.out.println(sessionId);
		System.out.println(code);
		
		// 2.记录手机号入库（埋点）
		boolean loginResult = userService.login(phoneNumber);
		APPResponseBody app = new APPResponseBody();
		app.setData(loginResult);
		app.setRetnCode(0);
		return app;
	}
	
	/**
	 * 用户信息录入
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/infoInput" ,method = RequestMethod.POST)
	public APPResponseBody userInfoInput(HttpServletRequest request) {
		
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String IDnumber = request.getParameter("IDnumber");
		String positiveURL = request.getParameter("positiveURL");
		String oppositeURL = request.getParameter("oppositeURL");
		
		
		// 1.插入
		UserInfoPojo userInfo = new UserInfoPojo(userName, userPhone, IDnumber, positiveURL,oppositeURL);
		userService.inputUserInfo(userInfo);

		// 2.登录

		APPResponseBody app = new APPResponseBody();
		app.setData("");
		app.setRetnCode(0);
		return app;
	}
	
 	@ResponseBody
    @RequestMapping(value="showUser",method=RequestMethod.POST)
    public String toIndex(HttpServletRequest request){  
        long userId = 1;
        UserInfoPojo user = this.userService.getUserById(userId);
        APPResponseBody app = new APPResponseBody();
        app.setData(user.getName());
        app.setRetnCode(0);
        return "showUser";  
    }  
	
}
