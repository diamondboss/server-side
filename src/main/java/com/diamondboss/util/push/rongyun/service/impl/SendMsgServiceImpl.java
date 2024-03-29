package com.diamondboss.util.push.rongyun.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.diamondboss.order.vo.SendNotifySmsInfoVo;
import com.diamondboss.util.pojo.RYGetTokenReturnInfo;
import com.diamondboss.util.pojo.SmsReturnInfo;
import com.diamondboss.util.push.rongyun.constcla.StatusCode;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.push.rongyun.util.SmsSenderUtils;
import com.diamondboss.util.push.rongyun.util.StringTools;
import com.diamondboss.util.tools.PropsUtil;
import com.diamondboss.util.tools.SHA1Utils;

@Service(value="msgSendServiceImpl")
public class SendMsgServiceImpl implements ISendMsgService {
	
	// 日志记录器	
	private static Logger logger = Logger.getLogger(SendMsgServiceImpl.class);

	//融云的appKey
	private static final String appKey = PropsUtil.getProperty("rongyun.appkey");
	private static final String sendVerifyUrl = PropsUtil.getProperty("rongyun.sendVerifyUrl");
	private static final String verifyUrl = PropsUtil.getProperty("rongyun.verifyUrl");
	private static final String sendNotifyUrl = PropsUtil.getProperty("rongyun.sendNotifyUrl");
	private static final String verifyTemplateId = PropsUtil.getProperty("rongyun.verifyTemplateId");
	private static final String notifyTemplateIdForUserSuccess = PropsUtil.getProperty("rongyun.notifyTemplateId.user.success");
	private static final String notifyTemplateIdForUserFail = PropsUtil.getProperty("rongyun.notifyTemplateId.user.fail");
	private static final String notifyTemplateIdForPartnerSuccess = PropsUtil.getProperty("rongyun.notifyTemplateId.partner.success");
	private static final String notifyTemplateIdcancel = PropsUtil.getProperty("rongyun.notifyTemplateId.cancel");
	private static final String RYGetTokenUri = PropsUtil.getProperty("rongyun.RYGetToken");
	
	private static final String RYAppSecret = PropsUtil.getProperty("rongyun.RYAppSecret");

//	@Autowired
//	private CacheFunc cacheFunc = null;

	/**
	 * 发送短信验证码
	 */
	@Override
	public SmsReturnInfo sendVerifyMsg(String phone) {

		//随机数（不限长度）
		String nonce = StringTools.getRandomForSix(); // 获取随机数
		//时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数。
		String timestamp = String.valueOf(System.currentTimeMillis());
		//数据签名
		String strHash = SHA1Utils.hex_sha1(RYAppSecret + nonce + timestamp);

		SmsReturnInfo smsReturnInfo  = SmsSenderUtils.send(appKey, nonce, timestamp, strHash, phone, verifyTemplateId,sendVerifyUrl);
		if(smsReturnInfo != null && smsReturnInfo.getCode() == StatusCode.SUCCESS_CODE.intValue()){
			logger.info("短信验证码发送成功");
		}
		return smsReturnInfo;
	}

	@Override
	public SmsReturnInfo verifyCode(String sessionId, String code) {
		//随机数（不限长度）
		String nonce = StringTools.getRandomForSix(); // 获取随机数
		//时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数。
		String timestamp = String.valueOf(System.currentTimeMillis());
		//数据签名
		String strHash = SHA1Utils.hex_sha1(RYAppSecret + nonce + timestamp);

		SmsReturnInfo smsReturnInfo  = SmsSenderUtils.verifySmsCode(appKey, nonce, timestamp, strHash, verifyUrl ,sessionId, code);
		if(smsReturnInfo != null && smsReturnInfo.getCode() == StatusCode.SUCCESS_CODE.intValue()){
			logger.info("短信验证码校验成功");
		}else if(smsReturnInfo != null && smsReturnInfo.getCode() == StatusCode.VERIFIED.intValue()){
			logger.info("验证码已验证过");
		}else if(smsReturnInfo != null && smsReturnInfo.getCode() == StatusCode.VERIFIEY_TIME_OUT.intValue()){
			logger.info("验证超时");
		}
		return smsReturnInfo;
	}

	@Override
	public SmsReturnInfo sendNotifyMsg(SendNotifySmsInfoVo sendSmsInfo, int flag) {
		//随机数（不限长度）
		String nonce = StringTools.getRandomForSix(); // 获取随机数
		//时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数。
		String timestamp = String.valueOf(System.currentTimeMillis());
		//数据签名
		String strHash = SHA1Utils.hex_sha1(RYAppSecret + nonce + timestamp);
		
		SmsReturnInfo smsReturnInfo = new SmsReturnInfo();
		
		if(flag == 0){ //用户成功
			smsReturnInfo  = SmsSenderUtils.sendNotifyMsg(appKey, nonce, timestamp, strHash, sendSmsInfo, notifyTemplateIdForUserSuccess, sendNotifyUrl, 01);
		}else if(flag == 1){ //用户失败
			smsReturnInfo  = SmsSenderUtils.sendNotifyMsg(appKey, nonce, timestamp, strHash, sendSmsInfo, notifyTemplateIdForUserFail, sendNotifyUrl, 1);
		}else if(flag == 2){ //合伙人成功
			smsReturnInfo  = SmsSenderUtils.sendNotifyMsg(appKey, nonce, timestamp, strHash, sendSmsInfo, notifyTemplateIdForPartnerSuccess, sendNotifyUrl, 2);
		}else if(flag == 3){ //取消预约
			smsReturnInfo  = SmsSenderUtils.sendNotifyMsg(appKey, nonce, timestamp, strHash, sendSmsInfo, notifyTemplateIdcancel, sendNotifyUrl, 3);
		}
		
		if(smsReturnInfo != null && smsReturnInfo.getCode() == StatusCode.SUCCESS_CODE.intValue()){
			logger.info("通知类短信发送成功");
		}
		return smsReturnInfo;
	}

	@Override
	public RYGetTokenReturnInfo getToken(String userId, String name, String portraitUri) {
		// 随机数（不限长度）
		String nonce = StringTools.getRandomForSix(); // 获取随机数
		// 时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数。
		String timestamp = String.valueOf(System.currentTimeMillis());
		// 数据签名
		String strHash = SHA1Utils.hex_sha1(RYAppSecret + nonce + timestamp);
		
		RYGetTokenReturnInfo ryGetTokenReturnInfo = SmsSenderUtils.RYGetToken(appKey, nonce, timestamp, strHash , RYGetTokenUri, userId, name, portraitUri);
	
		return ryGetTokenReturnInfo;
	}
}
