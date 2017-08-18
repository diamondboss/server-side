package com.diamondboss.util.push.rongyun.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.vo.SendNotifySmsInfoVo;
import com.diamondboss.util.pojo.RYGetTokenReturnInfo;
import com.diamondboss.util.pojo.SmsReturnInfo;
import com.diamondboss.util.push.rongyun.constcla.Constants;
import com.diamondboss.util.push.rongyun.constcla.StatusCode;
import com.diamondboss.util.push.rongyun.core.HttpUtils;
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
	private static Logger logger = Logger.getLogger(SmsSenderUtils.class);

	/**
	 *
	 * @param appKey
	 * @param nonce
	 * @param timestamp
	 * @param hash
	 * @param mobile
	 * @return
	 */
	public static SmsReturnInfo send(String appKey, String nonce, String timestamp, String hash, String mobile,
			String templateId, String requestUrl) {
		SmsReturnInfo smsReturnInfo = new SmsReturnInfo();
		try {
			// 将发送参数appKey + nonce + Timestamp + hash 放入到request请求头中
			Map params = new HashMap();
			params.put("appKey", appKey);
			params.put("nonce", nonce);
			params.put("timestamp", timestamp);
			params.put("hash", hash);

			params.put("region", "86");
			params.put("mobile", mobile);
			params.put("templateId", templateId);

			// 发送
			String result = HttpUtils.sendPost(params, requestUrl, 0);

			JsonObject obj = new JsonParser().parse(result).getAsJsonObject();
			String code = obj.get("code").getAsString();
			if (StringUtils.equals(code, Constants.USE_TOO_MORE)) {
				smsReturnInfo.setCode(Integer.valueOf(Constants.USE_TOO_MORE));
				smsReturnInfo.setSuccess(false);
				return smsReturnInfo; // 发送失败，调用频率过快
			}
			String sessionId = obj.get("sessionId").getAsString();
			if (code != null && StringUtils.equals(Constants.SUCCES, code)) {
				// 接收返回结果，并处理
				logger.info("短信发送返回状态码：" + code);
				logger.info("短信发送返回验证ID：" + sessionId);
				smsReturnInfo.setCode(StatusCode.SUCCESS_CODE);
				smsReturnInfo.setSessionId(sessionId);
				smsReturnInfo.setSuccess(true);
			} else {
				// 接收返回结果，并处理
				logger.info("短信发送返回状态码：" + code);
				smsReturnInfo.setCode(StatusCode.ERROR_CODE);
				smsReturnInfo.setSuccess(false);
			}
			return smsReturnInfo; // 发送成功
		} catch (Exception e) {
			smsReturnInfo.setCode(StatusCode.ERROR_CODE);
			smsReturnInfo.setSuccess(false);
			logger.error("短信平台发送异常！发送手机号：" + mobile);
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
			String result = HttpUtils.sendPost(params, requestUrl, 1);

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
				logger.info("短信校验返回码："+ returnCode);
				smsReturnInfo.setCode(Integer.valueOf(returnCode));
				smsReturnInfo.setSuccess(success);
			}
			return smsReturnInfo;  // 发送成功
		} catch (Exception e) {
			smsReturnInfo.setCode(StatusCode.ERROR_CODE);
			logger.error("短信校验返回异常，会话ID:" + sessionId );
			logger.error(e.getMessage());

		}
		return smsReturnInfo;
	}
	
	@SuppressWarnings("unchecked")
	public static RYGetTokenReturnInfo RYGetToken(String appKey, String nonce, String timestamp, String hash, String requestUrl,
			String userId, String name, String portraitUri){
		RYGetTokenReturnInfo ryGetTokenReturnInfo = new RYGetTokenReturnInfo();
		try{
			//将发送参数appKey + nonce + Timestamp + hash 放入到request请求头中
			Map params = new HashMap();
			params.put("appKey", appKey);
			params.put("nonce", nonce);
			params.put("timestamp", timestamp);
			params.put("hash", hash);

			params.put("userId", userId);
			params.put("name", name);
			params.put("portraitUri", portraitUri);

			//发送
			String result = HttpUtils.sendPost(params, requestUrl, 2);
			
			JsonObject obj = new JsonParser().parse(result).getAsJsonObject();
			String returnCode = obj.get("code").getAsString();
			String token = obj.get("token").getAsString();
			String usrId = obj.get("userId").getAsString();
			
			if(returnCode != null && StringUtils.equals(Constants.SUCCES, returnCode)){
				//接收返回结果，并处理
				logger.info("获取Token成功："+ returnCode);
				logger.info("获取Token用户ID："+ usrId);
				logger.info("Token："+ token);
				ryGetTokenReturnInfo.setCode(StatusCode.SUCCESS_CODE);
				ryGetTokenReturnInfo.setUserId(usrId);
				ryGetTokenReturnInfo.setToken(token);
			}else{
				//接收返回结果，并处理
				logger.info("获取Token返回错误码："+ returnCode);
				ryGetTokenReturnInfo.setCode(StatusCode.ERROR_CODE);
			}
			return ryGetTokenReturnInfo;  // 发送成功	
		}catch (Exception e) {
			ryGetTokenReturnInfo.setCode(StatusCode.ERROR_CODE);
			logger.error("获取token失败，用户ID:" + userId );
			logger.error(e.getMessage());

		}
		
		
		return ryGetTokenReturnInfo;
	}
	
	
	/**
	 *
	 * @param appKey
	 * @param nonce
	 * @param timestamp
	 * @param hash
	 * @param mobile
	 * @return
	 */
	public static SmsReturnInfo sendNotifyMsg(String appKey, String nonce, String timestamp, String hash, 
			SendNotifySmsInfoVo sendSmsInfo, String templateId, String requestUrl,int smsType){
		SmsReturnInfo smsReturnInfo = new SmsReturnInfo();
		try {
			//将发送参数appKey + nonce + Timestamp + hash 放入到request请求头中
			Map<String, Object> params = new HashMap<>();
			params.put("appKey", appKey);
			params.put("nonce", nonce);
			params.put("timestamp", timestamp);
			params.put("hash", hash);

			params.put("region", "86");
			params.put("mobile", sendSmsInfo.getPhone());
			params.put("templateId", templateId);
			
			String result = "";
			
			if(smsType == 0){ //用户成功
				params.put("p1", sendSmsInfo.getUserName());
				params.put("p3", sendSmsInfo.getPartnerName());
				//发送
				HttpUtils.sendPost(params, requestUrl, 3);
			}else if(smsType == 1){ //用户失败
				params.put("p1", sendSmsInfo.getUserName());
				//发送
				HttpUtils.sendPost(params, requestUrl, 4);
			}else if(smsType == 2){ //合伙人成功
				params.put("p1", sendSmsInfo.getOrderDate());
				//发送
				HttpUtils.sendPost(params, requestUrl, 5);
			}else if(smsType == 3){ //取消预约
				params.put("p1", sendSmsInfo.getUserPhone());
				//发送
				HttpUtils.sendPost(params, requestUrl, 6);
			}
			
			JsonObject obj = new JsonParser().parse(result).getAsJsonObject();
			String code = obj.get("code").getAsString();
			String sessionId = obj.get("sessionId").getAsString();
			if(code != null && StringUtils.equals(Constants.SUCCES, code)){
				//接收返回结果，并处理
				logger.info("短信发送返回状态码："+ code);
				logger.info("短信发送sessionId："+ sessionId);
				smsReturnInfo.setCode(StatusCode.SUCCESS_CODE);
				smsReturnInfo.setSessionId(sessionId);
				smsReturnInfo.setSuccess(true);
			}else{
				//接收返回结果，并处理
				logger.info("短信发送返回状态码："+ code);
				smsReturnInfo.setCode(StatusCode.ERROR_CODE);
				smsReturnInfo.setSuccess(false);
			}
			return smsReturnInfo;  // 发送成功
		} catch (Exception e) {
			smsReturnInfo.setCode(StatusCode.ERROR_CODE);
			smsReturnInfo.setSuccess(false);
			logger.error("短信平台发送异常！发送手机号：" + sendSmsInfo.getPhone() );
			logger.error(e.getMessage());

		}
		return smsReturnInfo;
	}
}
