package com.diamondboss.user.vo;

public class SmsQueryNewVo {
	
	/**
	 * 未读消息的数量
	 */
	private String smsNum;
	
	/**
	 * 是否有未读消息标志
	 */
	private String flag;

	public String getSmsNum() {
		return smsNum;
	}

	public void setSmsNum(String smsNum) {
		this.smsNum = smsNum;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
