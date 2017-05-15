package com.diamondboss.order.service;

import com.diamondboss.util.pojo.CommunityPojo;

/**
 * 小区
 * @author xzf
 *
 */
public interface IOrderService {
	
	/**
	 * 根据小区ID，查询小区信息
	 * @param communityId
	 * @return
	 */
	public CommunityPojo queryCommunity(Long communityId);
}
