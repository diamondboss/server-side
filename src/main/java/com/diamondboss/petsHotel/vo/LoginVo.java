package com.diamondboss.petsHotel.vo;

/**
 * 酒店端登录vo
 * 
 * @author John
 * @since 2017-08-20
 *  
 */
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
	 * 电话号码
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 电话号码
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 短信验证码
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 短信验证码
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 电话验证编号
	 * @return
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * 电话验证编号
	 * @param sessionId
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
