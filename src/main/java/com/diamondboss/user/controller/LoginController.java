package com.diamondboss.user.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.diamondboss.user.pojo.PartnerLoginPojo;
import com.diamondboss.user.pojo.UserLoginPojo;
import com.diamondboss.user.service.PartnerLoginService;
import com.diamondboss.user.service.UserLoginService;
import com.diamondboss.user.vo.LoginVo;
import com.diamondboss.util.pojo.SmsReturnInfo;
import com.diamondboss.util.push.rongyun.constcla.StatusCode;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.UserOrderServiceVo;

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
		APPResponseBody app = new APPResponseBody();
		
		// 短信验证码
		SmsReturnInfo info = sendMsgService.verifyCode(
				vo.getSessionId(), vo.getCode());
		
		if (false == info.getSuccess()) {
			app.setData(vo);
			app.setRetnCode(1);
			app.setRetnDesc("验证码校验错误");
			log.info("短信验证码校验错误，手机号：" + vo.getPhone());
			return app;
		}

		PartnerLoginPojo partnerLogin = partnerLoginService.login(vo);
		if (partnerLogin == null) {
			UserLoginPojo userLogin = userLoginService.login(vo);
			if (userLogin == null) {
				if (userLoginService.insertUser(vo) < 1) {
					app.setRetnCode(1);
					return app;
				}
			}
			userLogin = userLoginService.login(vo);

			String today = LocalDate.now().toString();
			UserOrderServiceVo userOrder = partnerLoginService.queryUserOrderService(userLogin.getId(), today);

			Map<String, Object> resultDate = new HashMap<>();
			resultDate.put("userLogin", userLogin);
			resultDate.put("userOrder", userOrder);

			app.setRetnCode(0);
			app.setData(resultDate);
			return app;
		}
		app.setRetnCode(0);
		app.setData(partnerLogin);
		return app;
	}
	
	/**
	 * 发送短信验证码
	 */
	@ResponseBody
	@RequestMapping(value = "/sendVerify", method = RequestMethod.POST)
	public APPResponseBody sendMsg(@RequestBody LoginVo vo, 
			HttpServletRequest request){
		APPResponseBody app = new APPResponseBody();
		
		SmsReturnInfo smsReturnInfo = sendMsgService.sendVerifyMsg(vo.getPhone());
		if(smsReturnInfo != null && smsReturnInfo.getCode() == StatusCode.SUCCESS_CODE.intValue()){
			app.setRetnCode(0);
			log.info("短信验证码发送成功");
		}
		app.setData(smsReturnInfo);
		return app;	
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
