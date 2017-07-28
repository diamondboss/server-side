package com.diamondboss.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.diamondboss.order.service.ICommunityService;
import com.diamondboss.order.vo.CommunityIdVo;
import com.diamondboss.order.vo.CommunityResponseVo;
import com.diamondboss.util.vo.APPResponseBody;

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
	 * 根据小区名字，获取小区Id
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryCommunityId", method = RequestMethod.POST)
	public APPResponseBody queryCommunityId(CommunityIdVo vo, HttpServletRequest request) {
		APPResponseBody app = new APPResponseBody();
		
		if(vo.getCommunityName() == null){
			app.setRetnDesc("请求参数非法");
			app.setRetnCode(0);
			return app;
		}
		
		CommunityResponseVo community = communityService.queryCommunityId(vo.getCommunityName());
		if(community != null){
			app.setData(community);
			app.setRetnCode(0);
			return app;
		}
		
		app.setData("");
		app.setRetnCode(1);
		app.setRetnDesc("当前小区未开放，敬请期待");
		return app;
	}
	
	
}
