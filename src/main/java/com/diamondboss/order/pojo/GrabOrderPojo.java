package com.diamondboss.order.pojo;

/**
 * 抢单pojo
 * 
 * @author John
 * @since 2017-07-04
 *  
 */
public class GrabOrderPojo {

	public GrabOrderPojo(){
		
	}
	
	/**
	 * 用户表对应的主键
	 */
	private String userKeyId;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 用户表id
	 */
	private String userTableId;
	
	/**
	 * 合伙人id
	 */
	private String partnerId;
	
	/**
	 * 合伙人表id
	 */
	private String partnerTableId;
	
	/**
	 * 合伙人名称
	 */
	private String partnerName;
	
	/**
	 * 订单日期
	 */
	private String orderDate;
	
	/**
	 * 小区Id
	 */
	private String communityId;


	/**
	 * 用户id
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 用户表id
	 * @return
	 */
	public String getUserTableId() {
		return userTableId;
	}

	/**
	 * 用户表id
	 * @param userTableId
	 */
	public void setUserTableId(String userTableId) {
		this.userTableId = userTableId;
	}

	/**
	 * 合伙人id
	 * @return
	 */
	public String getPartnerId() {
		return partnerId;
	}

	/**
	 * 合伙人id
	 * @param partnerId
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * 订单日期
	 * @return
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * 订单日期
	 * @param orderDate
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 合伙人表id
	 * @return
	 */
	public String getPartnerTableId() {
		return partnerTableId;
	}

	/**
	 * 合伙人表id
	 * @param partnerTableId
	 */
	public void setPartnerTableId(String partnerTableId) {
		this.partnerTableId = partnerTableId;
	}

	public String getUserKeyId() {
		return userKeyId;
	}

	public void setUserKeyId(String userKeyId) {
		this.userKeyId = userKeyId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	
	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	
}
