package com.diamondboss.order.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.service.ICommunityService;
import com.diamondboss.util.pojo.CommunityPojo;
import com.diamondboss.util.pojo.ParterOrderPojo;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.CommunityVo;
import com.diamondboss.util.vo.UserDetailVo;

/**
 * 小区服务控制类
 * @author xzf
 *
 */
@Controller
@RequestMapping("/community")
public class CommunityController {
	
	@Resource
	private ICommunityService communityService;

	/**
	 * 首页所有的小区列表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryCommunity", method = RequestMethod.POST)
	public APPResponseBody queryCommunityo(HttpServletRequest request) {
		
		List<CommunityPojo> communitys = communityService.queryCommunitys();
		
		List<CommunityVo> communityList = new ArrayList<>();
		for (CommunityPojo communityPojo : communitys) {
			CommunityVo community = new CommunityVo();
			community.setId(communityPojo.getId());
			community.setCommunityName(communityPojo.getCommunityName());
			community.setImagesUrl(communityPojo.getImagesUrl());
			
			communityList.add(community);
		}
		
		System.out.println(communityList);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(communityList);
		app.setRetnCode(0);
		return app;
	}
}
