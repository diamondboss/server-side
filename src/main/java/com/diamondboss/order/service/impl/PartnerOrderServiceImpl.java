package com.diamondboss.order.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.repository.PartnerOrderMapper;
import com.diamondboss.order.service.IPartnerOrderService;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.util.vo.PartnerOrderVo;

@Service("partnerOrderService")
public class PartnerOrderServiceImpl implements IPartnerOrderService{

	@Autowired
	public PartnerOrderMapper partnerOrder;
	
	@Override
	public List<PartnerOrderVo> queryTodayOrder(String partnerId){
		
		// 根据合伙人id查询合伙人订单表
		String today = LocalDate.now().toString();
		
		String orderPartner = TableUtils.getOrderTableName(Long.valueOf(partnerId),
				PetConstants.ORDER_PARTNER_TABLE_PREFIX);
		
		Map<String, Object> param = new HashMap<>();
		param.put("orderDate", today);
		param.put("partnerId", partnerId);
		param.put("orderPartner", orderPartner);
		
		List<PartnerOrderVo> list = partnerOrder.queryTodayOrder(param);
		
		return list;
	}
	
	/**
	 * 查询合伙人历史订单明细
	 * @param partnerId
	 * @return
	 */
	@Override
	public List<PartnerOrderVo> queryTodayOrderDetail(String partnerId){
		
		String orderPartner = TableUtils.getOrderTableName(Long.valueOf(partnerId),
				PetConstants.ORDER_PARTNER_TABLE_PREFIX);
		Map<String, Object> param = new HashMap<>();
		param.put("partnerId", partnerId);
		param.put("orderPartner", orderPartner);
		
		List<PartnerOrderVo> list = partnerOrder.queryTodayOrderDetail(param);
		
		return list;
	}
}
