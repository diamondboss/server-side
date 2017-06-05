package com.diamondboss.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.service.IPartnerWalletService;
import com.diamondboss.util.pojo.PartnerWalletPojo;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.CommOrderInfoVo;

/**
 * 合伙人钱包
 * @author 
 *
 */
@Controller
@RequestMapping("/partnerWallet")
public class PartnerWalletController {

	@Resource
	private IPartnerWalletService partnerWallet;

	@ResponseBody
	@RequestMapping(value = "/queryPartnerWallet", method = RequestMethod.POST)
	public APPResponseBody queryPaterWallet(HttpServletRequest request) {

		String partnerId = request.getParameter("partnerId");
		
		PartnerWalletPojo partnerWalletPojo = partnerWallet.queryPartnerWalletAmount(partnerId);
		
		System.out.println(partnerWalletPojo);
		
		APPResponseBody app = new APPResponseBody();
		CommOrderInfoVo vo = new CommOrderInfoVo();
		
		app.setData(vo);
		app.setRetnCode(0);
		return app;
	}

}
