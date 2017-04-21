package com.util.sms.txct.core;

import java.util.HashMap;
import java.util.Map;

import com.util.sms.aliyun.bean.Constants;
import com.util.sms.aliyun.core.HttpClientUtils;
import com.util.sms.txct.bean.SendMsgParmResult;
import com.thoughtworks.xstream.XStream;

/**
 * 
 * All rights Reserved, Designed By alsfox.com   
 * @ClassName:  SmsSenderUtils   
 * @Description:TODO(短信通道，短信发送)   
 * @author: Lynn  
 * @date:   2016-12-27 上午11:10:28   
 *
 */
public class SmsSenderUtils {
	
	public static SendMsgParmResult send(String phone,String content){
		Map<String, String> params = new HashMap<String,String>();
		params.put("action", Constants.SMS_ACTION);
		params.put("userid", Constants.SMS_USERID);
		params.put("account", Constants.SMS_ACCOUNT);
		params.put("password", Constants.SMS_PASSWORD);
		params.put("mobile", phone);
		params.put("content", content);
		String resultStr = "";
		try {
			resultStr = HttpClientUtils.getInstance().smsSendMesage(Constants.SMS_SEND_PATH, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("".equals(resultStr)){
			return null;
		}
		XStream xstream = new XStream();
		xstream.alias("returnsms", SendMsgParmResult.class); 
		return (SendMsgParmResult)xstream.fromXML(resultStr);
	}
	
	
}
