package com.diamondboss.user.vo;

/**
 * 用户反馈信息请求Vo
 * @author xzf
 *
 */
public class UserFeedBackVo {
	private String userId;
	
	private String phoneNum;
	
	private String context;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
}
