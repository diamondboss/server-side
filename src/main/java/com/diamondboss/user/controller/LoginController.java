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
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 
 * @author 
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
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public APPResponseBody login(@RequestBody LoginVo vo, 
			HttpServletRequest request){
		
		vo = userLoginService.login();
		
		if(vo == null){
			
			vo = partnerLoginService.login();
			
		}
		
		APPResponseBody app = new APPResponseBody();
		app.setData(vo);
		app.setRetnCode(0);
		return app;
		
	}
	
}
