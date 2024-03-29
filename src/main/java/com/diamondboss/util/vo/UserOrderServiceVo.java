package com.diamondboss.util.vo;

public class UserOrderServiceVo {

	private Long userId;
	
	private String partnerId;

	private String userName;

	private String phoneNum;
	
	private String receiveTime;
	
	private String returnTime;

	private String orderCount;
	
	private String dogFood;
	
	public String getDogFood() {
		return dogFood;
	}


	public void setDogFood(String dogFood) {
		this.dogFood = dogFood;
	}


	public UserOrderServiceVo(){
		
	}
	
	
	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
}
