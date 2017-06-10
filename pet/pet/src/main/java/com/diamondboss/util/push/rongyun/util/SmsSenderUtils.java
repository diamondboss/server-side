package com.diamondboss.util.push.rongyun.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

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
	
	@Resource(name = "cacheFunc")
	private CacheFunc cacheFunc = null;

	/**
	 *
	 * @param appKey
	 * @param nonce
	 * @param Timestamp
	 * @param hash
	 * @param mobile
	 * @return
	 */
	public int send(String appKey, String nonce, String Timestamp, String hash, String mobile){
		try {
			//url
			String requestUrl = "http://api.sms.ronghub.com/sendNotify.json";
			//手机号（参数已传）
			//模板ID
			//手机地区编码（86）
			
			//将发送参数appKey + nonce + Timestamp + hash 放入到request请求头中
			
			Map params = new HashMap();
			params.put("appKey", appKey);
			params.put("nonce", nonce);
			params.put("Timestamp", Timestamp);
			params.put("mobile", mobile);
			params.put("hash", hash);
			//拼接url参数
			//发送
			String result = HttpUtils.sendPost(params, requestUrl);
			//String result ="{\"code\": 200,\"sessionId\": \"xxxxxxx\"}";
			
			JsonObject obj = new JsonParser().parse(result).getAsJsonObject();
			String code = obj.get("code").getAsString();
			String sessionId = obj.get("sessionId").getAsString();
			
			if(code != null && StringUtils.equals(Constants.SUCCES, "200")){
				cacheFunc.setStrEx(Constants.SESSIONID + mobile, 60000, sessionId);
				//接收返回结果，并处理
				logger.info("短信发送返回状态码："+ code);
				logger.info("短信发送返回验证ID："+ cacheFunc.getStr(Constants.SESSIONID + mobile));
			}else{
				//接收返回结果，并处理
				logger.info("短信发送返回状态码："+ code);
			}
			return StatusCode.SUCCESS_CODE;  // 发送成功
		} catch (Exception e) {
			logger.error("短信平台发送异常！发送手机号：" + mobile );
			logger.error(e.getMessage());
		}
		return StatusCode.ERROR_CODE;
	}
}
