package com.diamondboss.user.vo;

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
	
	/**
	 * 客户端ID
	 */
	private String clientId;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LoginVo(){
		
	}

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
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}	
}
