package com.diamondboss.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.user.pojo.UserInfoPojo;
import com.diamondboss.user.service.UserInfoService;
import com.diamondboss.user.vo.UserInfoVo;
import com.diamondboss.util.tools.UUIDUtil;
import com.diamondboss.util.vo.APPResponseBody;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	
	private static final Logger log = Logger.getLogger(UserInfoController.class);
	
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * 更新用户信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateInfo" ,method = RequestMethod.POST)
	public APPResponseBody updateUserInfo(UserInfoPojo pojo,
			 HttpServletRequest request) {
		APPResponseBody app = new APPResponseBody();
		
		if(userInfoService.updateUserInfo(pojo) < 1){
			log.info("更新信息失败,UserId：" + pojo.getUserId());
			app.setRetnCode(1);
			return app;
		}
		
		UserInfoPojo userInfo = userInfoService.queryUserInfoOfUpdate(pojo);
		
		log.info("处理成功");
		app.setRetnCode(0);
		app.setData(userInfo);
		return app;	
	}
	
	/**
	 * 查询用户信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryInfo" ,method = RequestMethod.POST)
	public APPResponseBody queryUserInfo(UserInfoVo vo,
			HttpServletRequest request) {
		APPResponseBody app = new APPResponseBody();
		
		UserInfoPojo userInfo = userInfoService.queryUserInfo(vo);
		UserInfoPojo queryUserInfo = new UserInfoPojo();
		
		if(userInfo == null){
			log.info("查询用户信息不存在，生成默认用户信息并保存，UserId：" + vo.getUserId());
			
			UserInfoPojo UserInfoPojo = new UserInfoPojo();
			UserInfoPojo.setUserId(vo.getUserId());
			UserInfoPojo.setName(UUIDUtil.getOrderIdByUUID());
			UserInfoPojo.setPhoneNumber("");
			UserInfoPojo.setAge("");
			UserInfoPojo.setSex("");
			UserInfoPojo.setAddress("");
			UserInfoPojo.setIndustry("");
			UserInfoPojo.setRemark("");
			
			if(userInfoService.inputUserInfo(UserInfoPojo) < 1){
				log.info("生成默认用户信息保存失败,UserId：" + vo.getUserId());
				app.setRetnCode(1);
				return app;
			}
			
			log.info("生成默认信息后，处理成功");
			app.setData(UserInfoPojo);
			app.setRetnCode(0);
			return app;
		}
		
		log.info("处理成功");
		app.setData(queryUserInfo);
		app.setRetnCode(0);
		return app;
		
	}
	
}
