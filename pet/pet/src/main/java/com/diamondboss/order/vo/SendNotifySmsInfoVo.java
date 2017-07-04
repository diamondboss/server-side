package com.diamondboss.order.vo;

/**
 * 发送短信通知Vo
 * @author xzf
 *
 */
public class SendNotifySmsInfoVo {

	/**
	 * 发送的手机号
	 */
	private String phone;
	
	/**
	 * 用户名字
	 */
	private String userName;
	
	/**
	 * 合伙人名字
	 */
	private String partnerName;
	
	/**
	 * 订单日期
	 */
	private String orderDate;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
