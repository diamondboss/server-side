package com.diamondboss.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.user.service.PartnerLoginService;
import com.diamondboss.user.service.UserLoginService;
import com.diamondboss.user.util.vo.LoginVo;
import com.diamondboss.util.pojo.SmsReturnInfo;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 用户登录
 * 
 * @author 
 * @since 2017-06-19
 *  
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private PartnerLoginService partnerLoginService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private ISendMsgService sendMsgService; 
	
	/**
	 * 用户/合伙人登录
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public APPResponseBody login(@RequestBody LoginVo vo, 
			HttpServletRequest request){
		
		// 短信验证码
		SmsReturnInfo info = sendMsgService.verifyCode(
				vo.getSessionId(), vo.getCode());
		
		if(false == info.getSuccess()){
			
		}
		
		vo = userLoginService.login(vo);// 查询
		
		if(vo == null){
			
			vo = partnerLoginService.login(vo);
			
		}
		
		APPResponseBody app = new APPResponseBody();
		app.setData(vo);
		app.setRetnCode(0);
		return app;
		
	}
	
	/**
	 * 发送短信验证码
	 */
	@ResponseBody
	@RequestMapping(value = "/sendVerify", method = RequestMethod.POST)
	public void sendMsg(){
		
		sendMsgService.sendVerifyMsg("18621705751");
		
	}
	
	/**
	 * 用户登录(合伙人切换)
	 * 
	 * @param vo
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public void userLogin(@RequestBody LoginVo vo, 
			HttpServletRequest request){
	
		
	}
	
	/**
	 * 合伙人登录(用户切换)
	 * 
	 * @param vo
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/partnerLogin", method = RequestMethod.POST)
	public void partnerLogin(@RequestBody LoginVo vo, 
			HttpServletRequest request){
	
		
	}
	
}
