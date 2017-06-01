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
import com.diamondboss.util.tools.SpinOffAddress;
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
		
		String subtitle = request.getParameter("subtitle");
		String name = request.getParameter("name");
		//String address = request.getParameter("address");
				
		APPResponseBody app = new APPResponseBody();
		if(subtitle == null || "".equals(subtitle)){
			app.setData("参数不合法");
			app.setRetnCode(1);
			return app;
		}
		
		@SuppressWarnings("rawtypes")
		Map map = orderService.countParter(subtitle);
		
		ParterOrderPojo parterOrderPojo = new ParterOrderPojo();
		parterOrderPojo.setCommunityId(subtitle);
		parterOrderPojo.setEffective(1);
		int countParterOrder = orderService.countParterOrder(subtitle);
		
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
		
		app.setData(communityPojo);
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
