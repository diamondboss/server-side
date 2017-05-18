package com.diamondboss.order.service;

import java.util.List;

import com.diamondboss.util.pojo.CommunityPojo;
import com.diamondboss.util.pojo.ParterInfoPojo;
import com.diamondboss.util.pojo.ParterOrderPojo;

/**
 * 小区订单
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
	public List<ParterInfoPojo> countParter(ParterInfoPojo parterInfoPojo);
	
	/**
	 * 根据小区ID，查询该小区有多少狗已预约
	 * @param parterOrderPojo
	 * @return
	 */
	public int countParterOrder(ParterOrderPojo parterOrderPojo);
	
}
