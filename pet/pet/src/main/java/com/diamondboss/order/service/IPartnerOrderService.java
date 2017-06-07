package com.diamondboss.order.service;

import java.util.List;

import com.diamondboss.util.vo.PartnerOrderVo;

/**
 * 合伙人订单
 * @author Boowen
 *
 */
public interface IPartnerOrderService {

	public List<PartnerOrderVo> queryTodayOrder(String partnerId);
	
	public List<PartnerOrderVo> queryTodayOrderDetail(String partnerId);
	
}
