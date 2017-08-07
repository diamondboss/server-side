package com.diamondboss.util.push.rongyun.service;


import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.vo.SendNotifySmsInfoVo;
import com.diamondboss.util.pojo.RYGetTokenReturnInfo;
import com.diamondboss.util.pojo.SmsReturnInfo;

/**
 * 
 * @ClassName:  ISendMsgService
 * @Description: 发送短信通道接口
 * @author: focus.liu
 * @date:   2017-06-21 下午2:44:37
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
	SmsReturnInfo sendVerifyMsg(String phone);

	/**
	 *
	 * @Title: verifyCode
	 * @Description: 验证验证码方法
	 * @param: sessionId 回话id
	 * @param: code 验证码
	 * @throws
	 */
	SmsReturnInfo verifyCode(String sessionId, String code);

	/**
	 *
	 * @Title: sendNotifyMsg
	 * @Description: 通知类短信发送方法，phone为短信接收人手机号
	 * @param: sendSmsInfo:发送信息需要的额信息
	 * @param: flag ：成功或失败的短信标识. 0为成功，1为失败 . 
	 * @throws
	 */
	SmsReturnInfo sendNotifyMsg(SendNotifySmsInfoVo sendSmsInfo, int flag);
	
	/**
	 * 
	 * @Title: getToken
	 * @param userId 用户ID
	 * @param name	用户名字
	 * @param portraitUri	用户头像路径
	 * @return
	 */
	RYGetTokenReturnInfo getToken(String userId, String name, String portraitUri);
	
}
