package com.diamondboss.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.wallet.pojo.PartnerWalletPojo;
import com.diamondboss.wallet.service.PartnerWithdrawalsService;
import com.diamondboss.wallet.vo.PartnerWalletVo;

/**
 * 合伙人提现
 * 
 * @author John
 * @since 2017-06-29
 *  
 */
@Controller
@RequestMapping("/partnerWithdrawals")
public class PartnerWithdrawalsController {

	@Autowired
	private PartnerWithdrawalsService withdrawalsService;
	
	/**
	 * 提现
	 */
	@ResponseBody
	@RequestMapping(value = "/withdrawals" ,method = RequestMethod.POST)
	public APPResponseBody withdrawals(PartnerWalletVo vo){
		
		withdrawalsService.withdrawals();
		
		APPResponseBody app = new APPResponseBody();
		app.setData("");
		app.setRetnCode(0);
		return app;
	}
	
	/**
	 * 查询钱包汇总
	 */
	@ResponseBody
	@RequestMapping(value = "/querySummaryInfo" ,method = RequestMethod.POST)
	public APPResponseBody querySummaryInfo(PartnerWalletVo vo){
		
		
		String value = withdrawalsService.querySummaryInfo(vo.getPartnerId());
		APPResponseBody app = new APPResponseBody();
		app.setData(value);
		app.setRetnCode(0);
		return app;
	}
	
	/**
	 * 查询钱包明细
	 */
	@ResponseBody
	@RequestMapping(value = "/queryDetailed" ,method = RequestMethod.POST)
	public APPResponseBody queryDetailed(String partnerId){
		
		List<PartnerWalletPojo> list = withdrawalsService.queryDetailed(partnerId);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(list);
		app.setRetnCode(0);
		return app;
	}
	
}
