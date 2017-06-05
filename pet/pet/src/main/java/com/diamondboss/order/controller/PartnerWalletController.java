package com.diamondboss.order.controller;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.diamondboss.order.service.IPartnerWalletService;
import com.diamondboss.util.vo.APPResponseBody;

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
		
		Map<String, Object> responseMap = partnerWallet.queryPartnerWalletAmount(partnerId);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(responseMap);
		app.setRetnCode(0);
		return app;
	}

}
