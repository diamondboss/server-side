package com.diamondboss.order.service;

import java.util.List;

import com.diamondboss.order.vo.GrabOrderVo;

public interface IGrabOrderService {

	/**
	 * 合伙人抢单
	 */
	public int grabOrder(GrabOrderVo vo);

	public List<GrabOrderVo> queryOrder(GrabOrderVo vo);
	
}
