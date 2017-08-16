package com.diamondboss.user.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.user.pojo.PartnerLoginPojo;
import com.diamondboss.user.pojo.UserInfoPojo;
import com.diamondboss.user.pojo.UserLoginPojo;
import com.diamondboss.user.service.PartnerLoginService;
import com.diamondboss.user.service.UserInfoService;
import com.diamondboss.user.service.UserLoginService;
import com.diamondboss.user.vo.IndexOrderOfUserVo;
import com.diamondboss.user.vo.LoginVo;
import com.diamondboss.util.pojo.SmsReturnInfo;
import com.diamondboss.util.push.rongyun.constcla.StatusCode;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.tools.UUIDUtil;
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
	private static final Logger log =  Logger.getLogger(LoginController.class);
	
	@Autowired
	private PartnerLoginService partnerLoginService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private ISendMsgService sendMsgService; 
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 用户/合伙人登录
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public APPResponseBody login(LoginVo vo,
			HttpServletRequest request){
		APPResponseBody app = new APPResponseBody();
		
		log.info("登录");
		
		if(StringUtils.equals(vo.getPhone(), "18238954989")){
			//系统白名单用户，直接跳过验证码！
		}else{
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
		}
		

		UserLoginPojo userLogin =  new UserLoginPojo();
		
		PartnerLoginPojo partnerLogin = partnerLoginService.login(vo);
		if (partnerLogin == null) {
			userLogin = userLoginService.login(vo);
			if (userLogin == null) {
				Map<String, Integer> responseMap = userLoginService.insertUser(vo);
				log.info("id = "  + responseMap.get("id"));
				if (responseMap.get("result") < 1) {
					userLogin.setUserType("0");
					log.info("登录失败");
					app.setRetnCode(1);
					return app;
				}else{
					UserInfoPojo UserInfoPojo = new UserInfoPojo();
					UserInfoPojo.setUserId(String.valueOf(responseMap.get("id")));
					UserInfoPojo.setName(UUIDUtil.getOrderIdByUUID());
					UserInfoPojo.setPhoneNumber(vo.getPhone());
					UserInfoPojo.setAge("");
					UserInfoPojo.setSex("");
					UserInfoPojo.setAddress("");
					UserInfoPojo.setIndustry("");
					UserInfoPojo.setRemark("");
					
					if(userInfoService.inputUserInfo(UserInfoPojo) < 1){
						log.info("生成默认用户信息保存失败,UserId：" + String.valueOf(responseMap.get("id")));
						app.setRetnCode(1);
						return app;
					}
					
				}
			}
			userLogin = userLoginService.login(vo);
			
			if(userLoginService.selectUserClientId(userLogin.getId(), vo.getClientId()) < 1){
				log.info("User clientId不存在，开始写入");
				if(userLoginService.insertUserClientId(userLogin.getId(), vo.getClientId()) < 1){
					userLogin.setUserType("0");
					log.info("User clientId写入失败");
					app.setRetnCode(1);
					return app;
				}
				log.info("User clientId写入成功! ^_^");
			}
			
			userLogin.setUserType("0");
			log.info("登录成功");
			app.setRetnCode(0);
			app.setData(userLogin);
			return app;
		}else{
			if(partnerLoginService.insertPartnerClientId(partnerLogin.getId() , vo.getClientId()) < 1){
				userLogin.setUserType("0");
				log.info("Partner clientId获取失败");
				app.setRetnCode(1);
				return app;
			}
		}
		log.info("登录成功");
		partnerLogin.setUserType("1");
		app.setRetnCode(0);
		app.setData(partnerLogin);
		return app;
	}
	
	
	/**
	 * 发送短信验证码
	 */
	
	@RequestMapping(value = "/sendVerify", method = RequestMethod.POST)
	public @ResponseBody APPResponseBody sendMsg(LoginVo vo, HttpServletRequest request){
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
	 * 用户首页订单
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userLoginInit", method = RequestMethod.POST)
	public APPResponseBody userLoginInit(IndexOrderOfUserVo vo, HttpServletRequest request){
		APPResponseBody app = new APPResponseBody();
		
		String today = LocalDate.now().toString();
		List<UserOrderServiceVo> userOrder =  new ArrayList<>();
		userOrder = partnerLoginService.queryUserOrderService(vo.getUserId(), today);
		
		if(userOrder == null || userOrder.size() == 0){
			app.setData(userOrder);
			app.setRetnCode(0);
			app.setRetnDesc("未找到数据哦~");
		} else{
			app.setData(userOrder);
			app.setRetnCode(0);
		}
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
	public void userLogin(LoginVo vo){
		
	}
	
	/**
	 * 合伙人登录(用户切换)
	 * 
	 * @param vo
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/partnerLogin", method = RequestMethod.POST)
	public void partnerLogin(LoginVo vo){
		
	}
	
}
