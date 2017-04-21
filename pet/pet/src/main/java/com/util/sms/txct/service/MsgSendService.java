package com.util.sms.txct.service;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.util.sms.aliyun.MsmInterface.ISmsSendMsg;
import com.util.sms.aliyun.bean.Constants;
import com.util.sms.aliyun.bean.StatusCode;
import com.util.sms.aliyun.core.StringTools;
import com.util.sms.txct.bean.SendMsgParmResult;
import com.util.sms.txct.core.SmsSenderUtils;

@Service(value="txctSMSSendService")
public class MsgSendService implements ISmsSendMsg{
	
	// 日志记录器
	private final static Logger log = Logger.getLogger(MsgSendService.class);
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private ISmsSendMsg aliyunSMSSendService;
	
	
	/**
	 * 
	 * @Title: sendMsg   
	 * @Description: TODO(短信发送方法，phone为短信接收人手机号，content为短信内容)   
	 * @param:      
	 * @return: boolean      
	 * @throws
	 */
	public int sendMsg(String phone){
		String yzm = StringTools.getRandomForSix();
		String content = "您的验证码是："+yzm+",请在30分钟内使用，如果不是你本人操作，请忽略本信息。【"+ Constants.SMS_SIGN +"】";
		SendMsgParmResult sendResult = SmsSenderUtils.send(phone, content);
		if(sendResult != null && sendResult.getMessage().equals("ok")){
			redisTemplate.opsForValue().set(Constants.REDIS_PHONENO_YZM+phone,yzm,1800,TimeUnit.SECONDS);
			log.info("天下畅通，从Redis中获取验证码:"+redisTemplate.opsForValue().get(Constants.REDIS_PHONENO_YZM+phone));
			return StatusCode.SUCCESS_CODE;
		}else{
			// 通过阿里云发送，备选方案
			return aliyunSMSSendService.sendMsg(phone, yzm);
		}
	}


	@Override
	public int sendMsg(String phone, String yzm) {
		// TODO Auto-generated method stub
		return 0;
	}


	
}
