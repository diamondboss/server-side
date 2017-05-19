package com.diamondboss.order.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.service.IOrderService;
import com.diamondboss.util.pojo.CommunityPojo;
import com.diamondboss.util.pojo.ParterInfoPojo;
import com.diamondboss.util.pojo.ParterOrderPojo;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.CommOrderInfoVo;
import com.diamondboss.util.vo.UserDetailVo;

@Controller
@RequestMapping("/queryOrder")
public class QueryInfoController {

	 @Resource  
	 private IOrderService orderService;
	
	/**
	 * 首页查询获得该小区社区合伙人数量跟已预订宠物量
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryInfo" ,method = RequestMethod.POST)
	public APPResponseBody queryOrderInfo(HttpServletRequest request) {
		
		String communityId = request.getParameter("communityId");
		
		APPResponseBody app = new APPResponseBody();
		if(communityId == null || "".equals(communityId)){
			app.setData("参数不合法");
			app.setRetnCode(1);
			return app;
		}
		
		ParterInfoPojo parterInfoPojo = new ParterInfoPojo();
		parterInfoPojo.setCommunityId(communityId);
		Map map = orderService.countParter(parterInfoPojo);
		
		ParterOrderPojo parterOrderPojo = new ParterOrderPojo();
		parterOrderPojo.setCommunityId(communityId);
		parterOrderPojo.setEffective(1);
		int countParterOrder = orderService.countParterOrder(parterOrderPojo);
		
		CommOrderInfoVo vo =  new CommOrderInfoVo();
		vo.setCommParNum(String.valueOf(map.get("ParterSize")));
		vo.setCommPetNum(String.valueOf(countParterOrder) + "/" + map.get("ParterNum"));
		
		app.setData(vo);
		app.setRetnCode(0);
		return app;
	}
	
	/**
	 * 首页查询获得该小区图片
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryCommMap" ,method = RequestMethod.POST)
	public APPResponseBody queryCommMap(HttpServletRequest request) {
		//获取前台传过来的小区ID
		String id = request.getParameter("communityId");
		
		APPResponseBody app = new APPResponseBody();
		if(id == null || "".equals(id)){
			app.setData("参数不合法");
			app.setRetnCode(1);
			return app;
		}
		
		Long communityId = Long.valueOf(id);
		
		CommunityPojo communityPojo = orderService.queryCommunity(communityId);
		
		communityPojo.getImagesUrl();
		// 查询图片
		// queryMap
		//String result = "\\\\HUMANS\\Users\\Boowen\\Downloads\\1234.jpg";
		String result = communityPojo.getImagesUrl();
		
		app.setData(result);
		app.setRetnCode(0);
		return app;
	}
	
	
	/**
	 * 查询用户的预算明细记录
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryUserDetail" ,method = RequestMethod.POST)
	public APPResponseBody queryUserDetail(HttpServletRequest request) {
		//获取前台传过来的用户ID
		String parter_id = request.getParameter("parterId");
		
		APPResponseBody app = new APPResponseBody();
		if(parter_id == null || "".equals(parter_id)){
			app.setData("参数不合法");
			app.setRetnCode(1);
			return app;
		}
		
		List<UserDetailVo> parterOrders = orderService.queryUserDetail(parter_id);
		
		app.setData(parterOrders);
		app.setRetnCode(0);
		return app;
	}
	
}
