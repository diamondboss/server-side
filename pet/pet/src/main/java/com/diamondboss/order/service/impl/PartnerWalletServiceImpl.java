package com.diamondboss.order.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.repository.PartnerWalletDetailMapper;
import com.diamondboss.order.repository.PartnerWalletMapper;
import com.diamondboss.order.service.IPartnerWalletService;
import com.diamondboss.util.pojo.PartnerWalletPojo;
import com.diamondboss.util.tools.TableUtils;
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
	public Map<String, Object> queryPartnerWalletAmount(String partnerId){
		
		Map<String, Object> map = new HashMap<>();
		map.put("partnerId", partnerId);
		map.put("effective", "1");
		
		// 查询合伙人钱包
		PartnerWalletVo partnerWalletVo = partnerWallet.queryPartnerWalletAmount(map);
		
		String today = LocalDate.now().toString();
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(partnerId),
				PetConstants.PARTNER_WALLET_DETAIL);
	
		//根据合伙人id查询合伙人钱包明细表
		Map<String, Object> parmMap = new HashMap<>();
		parmMap.put("partnerId", partnerId);
		parmMap.put("effective", "1");
		parmMap.put("tableName", tableName);
		parmMap.put("orderDate", today);
		
		List<PartnerWalletDetailVo> partnerAmountDetails = partnerWalletDetail.queryPartnerAmountDetails(parmMap);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("WalletAmount", partnerWalletVo);
		responseMap.put("partnerAmountDetails", partnerAmountDetails);
		
		return responseMap;
		
	}
	
}
