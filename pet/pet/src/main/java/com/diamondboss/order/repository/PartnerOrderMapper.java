package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.util.vo.PartnerOrderVo;

public interface PartnerOrderMapper {

	/**
	 * 查询合伙人首页订单信息
	 * @param param
	 * @return
	 */
	public List<PartnerOrderVo> queryTodayOrder(Map<String, Object> param);
	
	/**
	 * 查询合伙人历史订单明细
	 * @param param
	 * @return
	 */
	public List<PartnerOrderVo> queryTodayOrderDetail(Map<String, Object> param);
	
	
	
}
