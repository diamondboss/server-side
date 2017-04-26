package com.diamondboss.util.sms.rongyun.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.diamondboss.util.tools.jedis.CacheFunc;
import com.diamondboss.util.sms.rongyun.constcla.StatusCode;
import com.diamondboss.util.sms.rongyun.service.ISmsSendMsg;
import com.diamondboss.util.sms.rongyun.util.SmsSenderUtils;
import com.diamondboss.util.sms.rongyun.util.StringTools;

@Service(value="sMSSendService")
public class MsgSendService implements ISmsSendMsg {
	
	// 日志记录器
	private final static Logger logger = Logger.getLogger(MsgSendService.class);
	
	@Resource(name = "cacheFunc")
	private CacheFunc cacheFunc = null;

	/**
	 * 发送短信验证码
	 */
	public int sendMsg(String phone) {
		//平台App Key
		String appKey = "12156465341564";
		//随机数（不限长度）
		String nonce = StringTools.getRandomForSix(); // 获取随机数
		//时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数。
		String Timestamp = String.valueOf(System.currentTimeMillis());
		//数据签名
		int hash = (appKey + nonce + Timestamp).hashCode();
		
		String strHash = String.valueOf(hash);
		
		SmsSenderUtils smsSenderUtils = new SmsSenderUtils();
		int retFlag = smsSenderUtils.send(appKey, nonce, Timestamp, strHash, phone);
		if(retFlag == (int)StatusCode.SUCCESS_CODE){
			logger.info("短信验证码发送成功");
		}
		return retFlag;
	}
}
