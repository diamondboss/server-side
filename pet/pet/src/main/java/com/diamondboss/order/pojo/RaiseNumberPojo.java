package com.diamondboss.order.pojo;

/**
 * 合伙人接单pojo
 * 
 * @author John
 * @since 2017-06-24
 *  
 */
public class RaiseNumberPojo {

	public RaiseNumberPojo(){
		
	}
	
	/**
	 * 合伙人id
	 */
	private String partnerId; 

	/**
	 * 合伙人可接单数
	 */
	private String orderNum;

	/**
	 * 合伙人Id
	 * @return
	 */
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}	

	/**
	 * 合伙人可接单数
	 * @return
	 */
	public String getorderNum() {
		return orderNum;
	}

	/**
	 * 合伙人可接单数
	 * @param orderNum
	 */
	public void setorderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
}
