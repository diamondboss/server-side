package com.diamondboss.order.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.diamondboss.order.repository.ParterOrderMapper;
import com.diamondboss.order.service.IPartnerOrderService;
import com.diamondboss.util.vo.PartnerOrderVo;
import org.springframework.stereotype.Service;

@Service
public class PartnerOrderServiceImpl implements IPartnerOrderService{

	public ParterOrderMapper parterOrder;
	
	public List<PartnerOrderVo> queryTodayOrder(String partnerId){
		
		// 根据合伙人id查询合伙人订单表
		LocalDate today = LocalDate.now();
		
		Map<String, Object> param = new HashMap<>();
		param.put("orderDate", today);
		param.put("partnerId", partnerId);
		
		List<PartnerOrderVo> list = parterOrder.queryTodayOrder(param);
		
		return list;
	}
}
