package com.diamondboss.user.pojo;

/**
 * 合伙人登录pojo对象
 * 
 * @author John
 * @since 2017-06-19
 *  
 */
public class PartnerLoginPojo {

	/**
	 * 合伙人id
	 */
	private String id;
	
	/**
	 * 电话号码
	 */
	private String phoneNumber;
	
	/**
	 * 用户类型	1：合伙人
	 */
	private String userType;

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
	 * 电话号码
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 电话号码
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
}
