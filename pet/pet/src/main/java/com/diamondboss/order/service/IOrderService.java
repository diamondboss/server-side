package com.diamondboss.order.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.diamondboss.util.pojo.CommunityPojo;
import com.diamondboss.util.vo.UserDetailVo;
import com.diamondboss.util.vo.UserOrderServiceVo;

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
	public Map countParter(String subtitle);
	
	/**
	 * 根据小区ID，查询该小区有多少狗已预约
	 * @param parterOrderPojo
	 * @return
	 */
	public int countParterOrder(String subtitle);
	
	/**
	 * 查询用户的预约明细
	 * @param communityId
	 * @return
	 */
	public List<UserDetailVo> queryUserDetail(String parterId);	
	
	/**
	 * 查询用户的实时名单
	 * 
	 * @param request
	 * @return
	 */
	public UserOrderServiceVo queryUserOrderService(String userId, String orderDate);

	/**
	 *
	 * @param orderId
	 * @param params
	 */
	public void synchronizedPaymentResult(String orderId, Map<String, Objects> params);
}
