package com.diamondboss.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.user.service.LoginInitService;
import com.diamondboss.util.tools.TableUtils;

public class LoginInitServiceImpl implements LoginInitService{

	@Override
	public List<String> queryPartnerInCommunity(Object o) {
		
		// 根据小区信息查询该小区下面的合伙人
		
		
		return null;
	}

	@Override
	public void queryPartnerDetail(String partnerId){
		
		String tableId = TableUtils.getOrderTableName(Long.valueOf(partnerId), 
				PetConstants.ORDER_PARTNER_TABLE_PREFIX);
		
		Map<String, Object> map = new HashMap<>();
		map.put("partnerId", partnerId);
		map.put("tableId", tableId);
		
		
	}

	@Override
	public void queryOrderInHand(String userId) {
		
		String tableId = TableUtils.getOrderTableName(Long.valueOf(userId), 
				PetConstants.ORDER_USER_TABLE_PREFIX);
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("tableId", tableId);
		
		
		
		
	}
	
}
