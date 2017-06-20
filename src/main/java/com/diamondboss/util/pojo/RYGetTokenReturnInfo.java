package com.diamondboss.util.pojo;

/**
 * 
 * @author xzf
 *
 */
public class RYGetTokenReturnInfo {
	private int code;
	private String token;
	private Boolean userId;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getUserId() {
		return userId;
	}

	public void setUserId(Boolean userId) {
		this.userId = userId;
	}
}
