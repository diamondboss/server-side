package com.diamondboss.util.vo;

public class PartnerOrderServiceVo {

	/**
	 * 合伙人Id
	 */
	private Long partnerId;

	/**
	 * 合伙人姓名
	 */
	private String partnerName;

	/**
	 * 手机号
	 */
	private String phoneNum;

	/**
	 * 历史订单数
	 */
	private String orderCount;
	
	/**
	 * 合伙人名下订单情况
	 */
	private String numByPartnerOrder;
	
	/**
	 * 是否可以预约   0：可以    1：不可以
	 */
	private String appointmentFlag;

	public PartnerOrderServiceVo(){
		
	}
	
	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}


	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	
	public String getNumByPartnerOrder() {
		return numByPartnerOrder;
	}

	public void setNumByPartnerOrder(String numByPartnerOrder) {
		this.numByPartnerOrder = numByPartnerOrder;
	}
	
	public String getAppointmentFlag() {
		return appointmentFlag;
	}

	public void setAppointmentFlag(String appointmentFlag) {
		this.appointmentFlag = appointmentFlag;
	}
}
