package com.util.sms.aliyun.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.diamondboss.util.tools.jedis.CacheFunc;
import com.util.sms.ISmsSendMsg;
import com.util.sms.aliyun.bean.Constants;
import com.util.sms.aliyun.bean.StatusCode;
import com.util.sms.aliyun.core.SmsSenderUtils;
import com.util.sms.aliyun.core.StringTools;

@Service(value="aliyunSMSSendService")
public class MsgSendService implements ISmsSendMsg {
	
	// 日志记录器
	private final static Logger logger = Logger.getLogger(MsgSendService.class);
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Resource(name = "cacheFunc")
	private CacheFunc cacheFunc = null;


	/**
	 * 直接通知Aliyun发送
	 */
	public int sendMsg(String phone) {
		String yzm = StringTools.getRandomForSix();
		int retFlag = SmsSenderUtils.send(phone, yzm);
		if(retFlag == (int)StatusCode.SUCCESS_CODE){
			cacheFunc.setStrEx(Constants.REDIS_PHONENO_YZM+phone, 1800, yzm);
			//redisTemplate.opsForValue().set(Constants.REDIS_PHONENO_YZM+phone,yzm,1800,TimeUnit.SECONDS);
			//logger.info("从Redis中获取验证码:"+redisTemplate.opsForValue().get(Constants.REDIS_PHONENO_YZM+phone));
			logger.info("从Redis中获取验证码:"+cacheFunc.getStr(Constants.REDIS_PHONENO_YZM+phone));
		}
		return retFlag;
	}
	
	
	/**
	 * 验证码在天下畅通通道生成，那边发送失败，然后采用Aliyun发送
	 */
	public int sendMsg(String phone,String yzm) {
		int retFlag = SmsSenderUtils.send(phone, yzm);
		if(retFlag == (int)StatusCode.SUCCESS_CODE){
			redisTemplate.opsForValue().set(Constants.REDIS_PHONENO_YZM+phone,yzm,1800,TimeUnit.SECONDS);
			logger.info("阿里云，从Redis中获取验证码:"+redisTemplate.opsForValue().get(Constants.REDIS_PHONENO_YZM+phone));
		}
		return retFlag;
	}

}
