package com.diamondboss.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.service.IPartnerWithDraw;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.PartnerDetailVo;

/**
 * 合伙人提现控制器
 * @author xzf
 *
 */
@Controller
@RequestMapping("/withDraw")
public class PartnerWithDrawController {
	
	@Resource  
	 private IPartnerWithDraw partnerWithDraw;
	/**
	 * 查询用户的预算明细记录
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPartnerDetail" ,method = RequestMethod.POST)
	public APPResponseBody queryUserDetail(HttpServletRequest request) {
		//获取前台传过来的用户ID
		String partner_id = request.getParameter("partnerId");
		
		APPResponseBody app = new APPResponseBody();
		if(StringUtils.isBlank(partner_id)){
			app.setData("参数不合法");
			app.setRetnCode(1);
			return app;
		}
		
		List<PartnerDetailVo> partnerOrders = partnerWithDraw.queryPartnerDetail(partner_id);

		app.setData(partnerOrders);
		app.setRetnCode(0);
		return app;
	}
}
