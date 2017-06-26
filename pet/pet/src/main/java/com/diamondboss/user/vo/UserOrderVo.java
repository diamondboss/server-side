package com.diamondboss.user.vo;

public class UserOrderVo {
	private String userId;
	
	private String orderDate;
	
	public UserOrderVo(){
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
}
