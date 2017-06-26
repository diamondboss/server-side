package com.diamondboss.user.vo;

public class RequestTokenVo {
	/**
	 * 用户ID，（手机号）
	 */
	private String userId;
	
	public RequestTokenVo(){
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
