package com.diamondboss.order.pojo;

/**
 * 合伙人接单pojo
 * 
 * @author John
 * @since 2017-06-24
 *  
 */
public class RaiseNumberPojo {

	/**
	 * 合伙人id
	 */
	private String id; 
	
	/**
	 * 合伙人可接单数
	 */
	private String raiseNumber;

	/**
	 * 合伙人id
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 合伙人id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 合伙人可接单数
	 * @return
	 */
	public String getRaiseNumber() {
		return raiseNumber;
	}

	/**
	 * 合伙人可接单数
	 * @param raiseNumber
	 */
	public void setRaiseNumber(String raiseNumber) {
		this.raiseNumber = raiseNumber;
	}
	
}
