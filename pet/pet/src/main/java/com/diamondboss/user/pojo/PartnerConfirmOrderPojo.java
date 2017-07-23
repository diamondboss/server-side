package com.diamondboss.user.pojo;

public class PartnerConfirmOrderPojo {

	public PartnerConfirmOrderPojo(){
		
	}
	
	/**
	 * 合伙人表
	 */
	private String partnerTable;
	
	/**
	 * 系统订单
	 */
	private String outTradeNo;
	
	/**
	 * 时间
	 */
	private String time;
	
	private String userTable;

	public String getPartnerTable() {
		return partnerTable;
	}

	public void setPartnerTable(String partnerTable) {
		this.partnerTable = partnerTable;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserTable() {
		return userTable;
	}

	public void setUserTable(String userTable) {
		this.userTable = userTable;
	}
	
}
