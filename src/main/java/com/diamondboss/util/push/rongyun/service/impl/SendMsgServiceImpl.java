package com.diamondboss.util.push.rongyun.service.impl;

import javax.annotation.Resource;

import com.diamondboss.util.pojo.RYGetTokenReturnInfo;
import com.diamondboss.util.pojo.SmsReturnInfo;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.tools.PropertyUtil;
import com.diamondboss.util.tools.SHA1Utils;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.diamondboss.util.tools.jedis.CacheFunc;
import com.diamondboss.util.push.rongyun.constcla.StatusCode;
import com.diamondboss.util.push.rongyun.util.SmsSenderUtils;
import com.diamondboss.util.push.rongyun.util.StringTools;

@Service(value="msgSendServiceImpl")
public class SendMsgServiceImpl implements ISendMsgService {
	
	// 日志记录器
	private final static Logger logger = Logger.getLogger(SendMsgServiceImpl.class);

	//融云的appKey
	private static final String appKey = PropertyUtil.getProperty("rongyun.appkey");
	private static final String sendVerifyUrl = PropertyUtil.getProperty("rongyun.sendVerifyUrl");
	private static final String verifyUrl = PropertyUtil.getProperty("rongyun.verifyUrl");
	private static final String sendNotifyUrl = PropertyUtil.getProperty("rongyun.sendNotifyUrl");
	private static final String verifyTemplateId = PropertyUtil.getProperty("rongyun.verifyTemplateId");
	private static final String notifyTemplateId = PropertyUtil.getProperty("rongyun.notifyTemplateId");
	private static final String RYGetTokenUri = PropertyUtil.getProperty("rongyun.RYGetToken");
	
	private static final String RYAppSecret = PropertyUtil.getProperty("rongyun.RYAppSecret");

	@Resource(name = "cacheFunc")
	private CacheFunc cacheFunc = null;

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
		//int hash = (RYAppSecret + nonce + timestamp).hashCode();
		String strHash = SHA1Utils.hex_sha1(RYAppSecret + nonce + timestamp);
		//String strHash = String.valueOf(hash);

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
		//int hash = (RYAppSecret + nonce + timestamp).hashCode();
		String strHash = SHA1Utils.hex_sha1(RYAppSecret + nonce + timestamp);
		//String strHash = String.valueOf(hash);

		SmsReturnInfo smsReturnInfo  = SmsSenderUtils.verifySmsCode(appKey, nonce, timestamp, strHash, verifyUrl ,sessionId, code);
		if(smsReturnInfo != null && smsReturnInfo.getCode() == StatusCode.SUCCESS_CODE.intValue()){
			logger.info("短信验证码校验成功");
		}
		return smsReturnInfo;
	}

	@Override
	public SmsReturnInfo sendNotifyMsg(String phone) {
		//随机数（不限长度）
		String nonce = StringTools.getRandomForSix(); // 获取随机数
		//时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数。
		String timestamp = String.valueOf(System.currentTimeMillis());
		//数据签名
		//int hash = (RYAppSecret + nonce + timestamp).hashCode();
		String strHash = SHA1Utils.hex_sha1(RYAppSecret + nonce + timestamp);
		//String strHash = String.valueOf(hash);

		SmsReturnInfo smsReturnInfo  = SmsSenderUtils.send(appKey, nonce, timestamp, strHash, phone, notifyTemplateId, sendNotifyUrl);
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
		//int hash = (RYAppSecret + nonce + timestamp).hashCode();
		String strHash = SHA1Utils.hex_sha1(RYAppSecret + nonce + timestamp);
		 //= String.valueOf(hash);
		
		RYGetTokenReturnInfo ryGetTokenReturnInfo = SmsSenderUtils.RYGetToken(appKey, nonce, timestamp, strHash , RYGetTokenUri, userId, name, portraitUri);
	
		return ryGetTokenReturnInfo;
	}
}
