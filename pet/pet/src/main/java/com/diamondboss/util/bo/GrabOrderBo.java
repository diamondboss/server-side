package com.diamondboss.util.bo;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.util.tools.TableUtils;

/**
 * 合伙人抢单业务对象类
 * 
 * @author john
 * @since 2017.06.17
 * @version 1.0
 * 
 */
public class GrabOrderBo {

	public GrabOrderBo(){
		
	}
	
	/**
	 * 生成合伙人抢单业务对象
	 * 
	 * @param date 日期
	 * @param partnerId 合伙人id
	 * @param userId 用户id
	 * @param orderUserId 用户订单表-id
	 */
	public GrabOrderBo(String date, String partnerId, 
			String userId, String orderUserId){
		this.date = date;
		this.partnerId = partnerId;
		this.userId = userId;
		this.orderUserId = orderUserId;
		this.partnerTableId = TableUtils.getOrderTableName(
				Long.valueOf(partnerId), PetConstants.ORDER_PARTNER_TABLE_PREFIX);
		this.userTableId = TableUtils.getOrderTableName(
				Long.valueOf(userId), PetConstants.ORDER_USER_TABLE_PREFIX);
	}
	
	
	/**
	 * 日期
	 */
	private String date = "";
	
	/**
	 * 合伙人id
	 */
	private String partnerId = "";
	
	/**
	 * 用户id
	 */
	private String userId = "";
	
	/**
	 * 用户订单表-id
	 */
	private String orderUserId = "";// 表中获取
	
	/**
	 * 对应用户订单表表id
	 */
	private String userTableId = "";// UUID获取
	
	/**
	 * 对应合伙人表表id
	 */
	private String partnerTableId = "";// UUID获取

	/**
	 * 日期
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 日期
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * 用户订单表-id
	 * @return
	 */
	public String getOrderUserId() {
		return orderUserId;
	}

	/**
	 * 用户订单表-id
	 * @param orderUserId
	 */
	public void setOrderUserId(String orderUserId) {
		this.orderUserId = orderUserId;
	}

	/**
	 * 对应用户订单表表id
	 * @return
	 */
	public String getUserTableId() {
		return userTableId;
	}

	/**
	 * 对应用户订单表表id
	 * @param userTableId
	 */
	public void setUserTableId(String userTableId) {
		this.userTableId = userTableId;
	}

	/**
	 * 对应合伙人表表id
	 * @return
	 */
	public String getPartnerTableId() {
		return partnerTableId;
	}

	/**
	 * 对应合伙人表表id
	 * @param partnerTableId
	 */
	public void setPartnerTableId(String partnerTableId) {
		this.partnerTableId = partnerTableId;
	}
	
}
