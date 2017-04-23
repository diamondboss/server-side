package com.personal.personal.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.callMe.service.IUserService;
import com.personal.util.User;
import com.personal.util.vo.APPResponseBody;

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
		
		// 2.登录
		
		APPResponseBody app = new APPResponseBody();
		app.setData("");
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
		System.out.println(userName);
		System.out.println(userPhone);
		System.out.println(IDnumber);
		System.out.println(positiveURL);
		System.out.println(oppositeURL);
		
		// 2.登录
		
		APPResponseBody app = new APPResponseBody();
		app.setData("");
		app.setRetnCode(0);
		return app;
	}
	
 	@ResponseBody
    @RequestMapping(value="showUser",method=RequestMethod.POST)
    public String toIndex(HttpServletRequest request){  
        int userId = 1;  
        User user = this.userService.getUserById(userId);  
        APPResponseBody app = new APPResponseBody();
        app.setData(user.getAge());
        app.setRetnCode(0);
        return "showUser";  
    }  
	
}
