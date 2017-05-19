package com.diamondboss.personal.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.diamondboss.util.pojo.UserInfoPojo;
import com.diamondboss.util.pojo.UserLoginInfoPojo;

import org.apache.commons.lang3.StringUtils;
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
		
		//调用第三方校验验证码
		
		Map map = userService.queryUserLoginIn(phoneNumber);
		
		APPResponseBody app = new APPResponseBody();
		if((boolean)map.get("resultState") && (UserLoginInfoPojo)map.get("UserLoginInfo") == null){
			app.setData("");
			app.setRetnCode(0);
		}else if((UserLoginInfoPojo)map.get("UserLoginInfo") != null){
			app.setData((UserLoginInfoPojo)map.get("UserLoginInfo"));
			app.setRetnCode(0);
		}else{
			app.setData(phoneNumber);
			app.setRetnCode(1);
		}
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
    public APPResponseBody toIndex(HttpServletRequest request){
		String userId = request.getParameter("userId");
		APPResponseBody app = new APPResponseBody();
		if (StringUtils.isNotBlank(userId)){
			UserInfoPojo user = this.userService.getUserById(Long.valueOf(userId));
			app.setData(user == null ? "用户不存在！" : user.getName());
		} else {
			app.setData("用户id不能为空！");
		}
        app.setRetnCode(0);
        return app;
    }  
	
}
