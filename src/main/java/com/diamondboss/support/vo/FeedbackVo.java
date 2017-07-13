package com.diamondboss.support.vo;

/**
 * 意见反馈
 * 
 * @author John
 * @since 2017-07-13
 *  
 */
public class FeedbackVo {

	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 反馈
	 */
	private String feedback;

	/**
	 * 电话
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 电话
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 反馈
	 * @return
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * 反馈
	 * @param feedback
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
