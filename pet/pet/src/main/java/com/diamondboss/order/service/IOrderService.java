package com.diamondboss.order.service;

import com.diamondboss.util.pojo.CommunityPojo;
import com.diamondboss.util.pojo.ParterInfoPojo;

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
	
	/**
	 * 根据小区ID，查询该小区有多少位合伙人
	 * @return
	 */
	public int countParter(ParterInfoPojo parterInfoPojo);
	
}
