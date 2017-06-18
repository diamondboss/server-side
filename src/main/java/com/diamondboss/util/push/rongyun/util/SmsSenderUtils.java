package com.diamondboss.util.push.rongyun.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.diamondboss.util.pojo.SmsReturnInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.diamondboss.util.push.rongyun.constcla.Constants;
import com.diamondboss.util.push.rongyun.constcla.StatusCode;
import com.diamondboss.util.push.rongyun.core.HttpUtils;
import com.diamondboss.util.tools.jedis.CacheFunc;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * All rights Reserved, Designed By alsfox.com   
 * @ClassName:  SmsSenderUtils   
 * @Description:TODO(短信通道，短信发送)   
 * @author: zfxue  
 * @date:   2017-3-9 下午1:56:31   
 *
 */
public class SmsSenderUtils {
	
	// 日志记录器
	private static final Logger logger = Logger.getLogger(SmsSenderUtils.class);

	/**
	 *
	 * @param appKey
	 * @param nonce
	 * @param timestamp
	 * @param hash
	 * @param mobile
	 * @return
	 */
	public static SmsReturnInfo send(String appKey, String nonce, String timestamp, String hash, String mobile, String templateId, String requestUrl){
		SmsReturnInfo smsReturnInfo = new SmsReturnInfo();
		try {
			//将发送参数appKey + nonce + Timestamp + hash 放入到request请求头中
			Map params = new HashMap();
			params.put("appKey", appKey);
			params.put("nonce", nonce);
			params.put("timestamp", timestamp);
			params.put("hash", hash);

			params.put("region", "86");
			params.put("mobile", mobile);
			params.put("templateId", templateId);

			//发送
			String result = HttpUtils.sendPost(params, requestUrl,false);

			JsonObject obj = new JsonParser().parse(result).getAsJsonObject();
			String code = obj.get("code").getAsString();
			String sessionId = obj.get("sessionId").getAsString();
			if(code != null && StringUtils.equals(Constants.SUCCES, code)){
				//接收返回结果，并处理
				logger.info("短信发送返回状态码："+ code);
				logger.info("短信发送返回验证ID："+ sessionId);
				smsReturnInfo.setCode(StatusCode.SUCCESS_CODE);
				smsReturnInfo.setSessionId(sessionId);
			}else{
				//接收返回结果，并处理
				logger.info("短信发送返回状态码："+ code);
				smsReturnInfo.setCode(StatusCode.ERROR_CODE);
			}
			return smsReturnInfo;  // 发送成功
		} catch (Exception e) {
			smsReturnInfo.setCode(StatusCode.ERROR_CODE);
			logger.error("短信平台发送异常！发送手机号：" + mobile );
			logger.error(e.getMessage());

		}
		return smsReturnInfo;
	}

	public static SmsReturnInfo verifySmsCode(String appKey, String nonce, String timestamp, String hash, String requestUrl, String sessionId, String code){
		SmsReturnInfo smsReturnInfo = new SmsReturnInfo();
		try {
			//将发送参数appKey + nonce + Timestamp + hash 放入到request请求头中
			Map params = new HashMap();
			params.put("appKey", appKey);
			params.put("nonce", nonce);
			params.put("timestamp", timestamp);
			params.put("hash", hash);

			params.put("sessionId", sessionId);
			params.put("code", code);

			//发送
			String result = HttpUtils.sendPost(params, requestUrl, true);

			JsonObject obj = new JsonParser().parse(result).getAsJsonObject();
			String returnCode = obj.get("code").getAsString();
			Boolean success = obj.get("success").getAsBoolean();
			if(returnCode != null && StringUtils.equals(Constants.SUCCES, returnCode)){
				//接收返回结果，并处理
				logger.info("短信校验返回状态码："+ returnCode);
				logger.info("验证码验证结果："+ success);
				smsReturnInfo.setCode(StatusCode.SUCCESS_CODE);
				smsReturnInfo.setSuccess(success);
			}else{
				//接收返回结果，并处理
				logger.info("短信校验返回状态码："+ code);
				smsReturnInfo.setCode(StatusCode.ERROR_CODE);
			}
			return smsReturnInfo;  // 发送成功
		} catch (Exception e) {
			smsReturnInfo.setCode(StatusCode.ERROR_CODE);
			logger.error("短信校验返回异常，会话ID:" + sessionId );
			logger.error(e.getMessage());

		}
		return smsReturnInfo;
	}
}
