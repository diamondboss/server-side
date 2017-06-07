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
import com.diamondboss.util.vo.APPResponseBody;
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
	 * 查询用户的实时名单
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userOrder", method = RequestMethod.POST)
	public APPResponseBody userOrder(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		String orderDate = request.getParameter("orderDate");
		
		Map<String, Object> responseMap = orderService.queryUserOrderService(userId, orderDate);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(responseMap);
		app.setRetnCode(0);
		
		return app;
	}
}
