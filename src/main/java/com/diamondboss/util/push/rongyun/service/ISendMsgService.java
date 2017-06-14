package com.diamondboss.util.push.rongyun.service;


import com.diamondboss.util.pojo.SmsReturnInfo;

/**
 * 
 * All rights Reserved, Designed By alsfox.com   
 * @ClassName:  ISendMsgService
 * @Description:TODO(发送短信通道接口)   
 * @author: Lynn  
 * @date:   2016-12-24 下午2:44:37   
 *
 */
public interface ISendMsgService {

	/**
	 * 
	 * @Title: sendVerifyMsg
	 * @Description: 验证码短信发送方法，phone为短信接收人手机号
	 * @param:      
	 * @throws
	 */
	public SmsReturnInfo sendVerifyMsg(String phone);

	/**
	 *
	 * @Title: verifyCode
	 * @Description: 验证验证码方法
	 * @param: sessionId 回话id
	 * @param: code 验证码
	 * @throws
	 */
	public SmsReturnInfo verifyCode(String sessionId, String code);

	/**
	 *
	 * @Title: sendNotifyMsg
	 * @Description: 通知类短信发送方法，phone为短信接收人手机号
	 * @param:
	 * @throws
	 */
	public SmsReturnInfo sendNotifyMsg(String phone);
	
}
