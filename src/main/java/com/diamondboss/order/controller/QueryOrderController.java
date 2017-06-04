package com.diamondboss.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.service.IPartnerOrderService;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.PartnerOrderVo;

@Controller
@RequestMapping("/queryOrder")
public class QueryOrderController {

	@Autowired
	private IPartnerOrderService partnerOrderService;
	
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
		
		return null;
	}
	
}
