package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.pojo.RaiseNumberPojo;
import com.diamondboss.order.vo.PartnerClientVo;

/**
 * 
 * @author John
 * @since 2017-06-24
 *  
 */
public interface PlaceOrderMapper {

	/**
	 * 查询该合伙人是否满足条件
	 * 
	 * @param PartnerId 合伙人id
	 */
	public int queryPartnerCondition(String PartnerId);
	
	/**
	 * 查询指定合伙人在指定日期的有效订单数
	 * @param map
	 * @return
	 */
	public int queryCountsByPartnerAndDate(Map<String, Object> map);
	
	/**
	 * 插入用户订单
	 */
	public int insertUserOrder(OrderUserPojo pojo);
	
	/**
	 * 查询小区合伙人允许下单合伙人信息
	 * @param communityId
	 */
	public List<RaiseNumberPojo> queryTotalByCommunityId(String communityId);
	
	/**
	 * 根据用户Id和订单日期查出订单记录的主键Id
	 * @param map
	 * @return
	 */
	public String queryOrderPartnerId(Map<String, Object> map);
	
	/**
	 * 根据宠物信息查询托管金额
	 * @param param
	 * @return
	 */
	public String getAmtByPet(Map<String, Object> param);
	
	/**
	 * 查出某小区所有合伙人的clientId
	 * @param communityId
	 * @return
	 */
	public List<PartnerClientVo> queryPartnerClient(String communityId);
	
}
