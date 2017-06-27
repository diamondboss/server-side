package com.diamondboss.order.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.diamondboss.order.service.IOrderService;
import com.diamondboss.order.service.IPartnerOrderService;
import com.diamondboss.order.vo.UserOrderListVo;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.PartnerOrderRequestVo;
import com.diamondboss.util.vo.PartnerOrderServiceVo;
import com.diamondboss.util.vo.PartnerOrderVo;

@Controller
@RequestMapping("/queryOrder")
public class QueryOrderController {

	@Autowired
	private IPartnerOrderService partnerOrderService;
	
	@Autowired
	private IOrderService orderService;
	
	/**
	 * 查询合伙人首页订单
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/partnerHomePage", method = RequestMethod.POST)
	public APPResponseBody partnerHomePage(HttpServletRequest request) {
		
		String partnerId = request.getParameter("partnerId");
		
		List<PartnerOrderVo> list = partnerOrderService.queryTodayOrder(partnerId);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(list);
		app.setRetnCode(0);
		
		return app;
	}
	
	/**
	 * 查询合伙人首页订单
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/partnerOrderDetail", method = RequestMethod.POST)
	public APPResponseBody partnerOrderDetail(HttpServletRequest request) {
		
		String partnerId = request.getParameter("partnerId");
		
		List<PartnerOrderVo> list = partnerOrderService.queryTodayOrderDetail(partnerId);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(list);
		app.setRetnCode(0);
		
		return app;
	}
	
	/**
	 * 查询合伙人的实时订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/PartnerOrder", method = RequestMethod.POST)
	public @ResponseBody APPResponseBody PartnerOrder(PartnerOrderRequestVo vo, HttpServletRequest request) {
		
		APPResponseBody app = new APPResponseBody();
		
		String partnerId = request.getParameter("partnerId");
		
		PartnerOrderServiceVo partnerOrder = orderService.queryPartnerOrderService(partnerId);
		
		Map<String, String> map = orderService.NumByPartnerOrder(partnerId);
		partnerOrder.setNumByPartnerOrder(map.get("num") + "/" + map.get("total"));
		
		app.setData(partnerOrder);
		app.setRetnCode(0);
		
		return app;
	}
	
	/**
	 * 查询用户订单列表
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/userOrderList", method = RequestMethod.POST)
	public @ResponseBody APPResponseBody queryUserOrderList(String userId) {
		
		APPResponseBody app = new APPResponseBody();
		
		UserOrderListVo vo = orderService.queryUserOrderList(userId);
		
		app.setData(vo);
		app.setRetnCode(0);
		
		return app;
	}
	
}
