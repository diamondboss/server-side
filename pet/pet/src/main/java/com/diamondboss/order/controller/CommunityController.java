package com.diamondboss.order.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.service.ICommunityService;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.CommunityVo;

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
		
		List<CommunityVo> communitys = communityService.queryCommunitys();
		
		System.out.println(communitys);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(communitys);
		app.setRetnCode(0);
		return app;
	}
}
