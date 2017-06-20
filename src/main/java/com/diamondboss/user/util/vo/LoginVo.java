package com.diamondboss.user.util.vo;

public class LoginVo {

	/**
	 * 电话号码
	 */
	private String phone;
	
	/**
	 * 短信验证码
	 */
	private String code;
	
	/**
	 * 电话验证编号
	 */
	private String sessionId;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	
	
	
	
}
