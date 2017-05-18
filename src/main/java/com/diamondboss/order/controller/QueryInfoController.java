package com.diamondboss.order.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.diamondboss.util.pojo.ParterWithDrawPojo;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.CommOrderInfoVo;
import com.diamondboss.util.vo.ParterDetailVo;
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
		
		ParterInfoPojo parterInfoPojo = new ParterInfoPojo();
		parterInfoPojo.setCommunityId(communityId);
		List<ParterInfoPojo> listParter = orderService.countParter(parterInfoPojo);
		int ParterNum = 0;
		for (ParterInfoPojo parterInfo : listParter) {
			ParterNum = ParterNum + Integer.valueOf(parterInfo.getRaisenumber());
		}
		System.out.println(ParterNum);
		
		ParterOrderPojo parterOrderPojo = new ParterOrderPojo();
		parterOrderPojo.setCommunityId(communityId);
		parterOrderPojo.setEffective(1);
		int countParterOrder = orderService.countParterOrder(parterOrderPojo);
		
		CommOrderInfoVo vo =  new CommOrderInfoVo();
		
		vo.setCommParNum(String.valueOf(listParter.size()));
		vo.setCommPetNum(String.valueOf(countParterOrder) + "/" + ParterNum);
		
		APPResponseBody app = new APPResponseBody();
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
		Long communityId = Long.valueOf(id);
		
		CommunityPojo communityPojo = orderService.queryCommunity(communityId);
		
		communityPojo.getImagesUrl();
		// 查询图片
		// queryMap
		//String result = "\\\\HUMANS\\Users\\Boowen\\Downloads\\1234.jpg";
		String result = communityPojo.getImagesUrl();
		
		APPResponseBody app = new APPResponseBody();
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
		
		List<ParterOrderPojo> parterOrders = orderService.queryUserDetail(parter_id);
		
		List<UserDetailVo> userDetails = new ArrayList<>();
		for (ParterOrderPojo parterOrder : parterOrders) {
			UserDetailVo userDetail = new UserDetailVo();
			userDetail.setOrderTime(parterOrder.getOrderTime());
			userDetail.setCreateTime(parterOrder.getCreateTime());
			userDetails.add(userDetail);
		}
		
		APPResponseBody app = new APPResponseBody();
		app.setData(userDetails);
		app.setRetnCode(0);
		return app;
	}
	
}
