package com.diamondboss.util.vo;

public class PartnerOrderServiceVo {

	private Long partnerId;

	private String partnerName;

	private String phoneNum;

	private String orderCount;
	
	private String numByPartnerOrder;

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
}
