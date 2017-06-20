package com.diamondboss.user.pojo;

/**
 * 用户登录pojo对象
 * 
 * @author John
 * @since 2017-06-19
 *  
 */
public class UserLoginPojo {

	/**
	 * 用户id
	 */
	private String id;
	
	/**
	 * 电话号码
	 */
	private String phoneNumber;

	/**
	 * 用户id
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 用户id
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
	
}
