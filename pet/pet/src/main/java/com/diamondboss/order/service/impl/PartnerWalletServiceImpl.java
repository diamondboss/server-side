package com.diamondboss.order.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.order.repository.PartnerWalletDetailMapper;
import com.diamondboss.order.repository.PartnerWalletMapper;
import com.diamondboss.order.service.IPartnerWalletService;
import com.diamondboss.util.pojo.PartnerWalletPojo;
import com.diamondboss.util.vo.PartnerWalletDetailVo;
import com.diamondboss.util.vo.PartnerWalletVo;

/**
 * 合伙人钱包
 * @author 
 *
 */
@Service("partnerWalletService")
public class PartnerWalletServiceImpl implements IPartnerWalletService{

	@Autowired
	public PartnerWalletMapper partnerWallet;
	
	@Autowired
	public PartnerWalletDetailMapper partnerWalletDetail;
	
	/**
	 * 合伙人首页-查询合伙人钱包
	 */
	@Override
	public PartnerWalletPojo queryPartnerWalletAmount(String partnerId){
		
		Map<String, Object> map = new HashMap<>();
		map.put("partnerId", partnerId);
		map.put("effective", "1");
		
		// 查询合伙人钱包
		PartnerWalletVo partnerWalletVo = partnerWallet.queryPartnerWalletAmount(map);
		
		LocalDate today = LocalDate.now();
		
		// 根据合伙人id算出合伙人钱包明细表表明
		int temp = (Integer.valueOf(partnerId)/100) + 1;
		String  tableName = "partner_wallet_detail_" + temp;
		//根据合伙人id查询合伙人钱包明细表
		Map<String, Object> parmMap = new HashMap<>();
		parmMap.put("partnerId", partnerId);
		parmMap.put("effective", "1");
		parmMap.put("tableName", tableName);
		parmMap.put("orderDate", today);
		
		List<PartnerWalletDetailVo> partnerAmountDetails = partnerWalletDetail.queryPartnerAmountDetails(parmMap);
		
		System.out.println(partnerWalletVo);
		System.out.println(partnerAmountDetails);
		
		
		return null;
		
	}
	
}
