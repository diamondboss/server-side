package com.diamondboss.user.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.user.service.PartnerInfoService;
import com.diamondboss.user.vo.RequestPartnerOfCommunityVo;
import com.diamondboss.user.vo.ResponsePartnerOfCommunityVo;
import com.diamondboss.util.vo.APPResponseBody;

@Controller
@RequestMapping("/partner")
public class PartnerInfoController {
	
	private static final Logger logger = Logger.getLogger(PartnerInfoController.class);
	
	@Autowired
	private PartnerInfoService partnerInfoService;


	/**
	 * 查询进行中的订单
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryInfo" ,method = RequestMethod.POST)
	public APPResponseBody query(HttpServletRequest request) {
		
		APPResponseBody app = new APPResponseBody();
		app.setData("");
		app.setRetnCode(0);
		return app;
	}
	
	/**
	 * 查询某个小区中所有的合伙人
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPartnerOfCommunityList" ,method = RequestMethod.POST)
	public APPResponseBody queryPartnerOfCommunityList(RequestPartnerOfCommunityVo vo, HttpServletRequest request) {
		APPResponseBody app = new APPResponseBody();
		
		if(vo.getCommunityName() == null){
			app.setData("参数不合法");
			app.setRetnCode(1);
			return app;
		}
		
		List<ResponsePartnerOfCommunityVo> partnerList = partnerInfoService.queryPartnerOfCommunityList(vo.getCommunityName());
		
		if(partnerList != null){
			app.setData(partnerList);
			app.setRetnCode(0);
			return app;
		}
		
		app.setData("");
		app.setRetnCode(0);
		return app;	
	}
	
}
