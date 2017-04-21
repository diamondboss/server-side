package com.util.sms.aliyun.core;

import org.apache.log4j.Logger;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.util.sms.aliyun.bean.Constants;
import com.util.sms.aliyun.bean.StatusCode;

/**
 * 
 * All rights Reserved, Designed By alsfox.com   
 * @ClassName:  SmsSenderUtils   
 * @Description:TODO(短信通道，短信发送)   
 * @author: Lynn  
 * @date:   2017-3-9 下午1:56:31   
 *
 */
public class SmsSenderUtils {
	
	// 日志记录器
	private static final Logger logger = Logger.getLogger(SmsSenderUtils.class);
	
	public static int send(String phone,String yzm){
		try {
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", Constants.ALIYUN_ACCESS_KEY, Constants.ALIYUN_ACCESS_SECRET);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms",  "sms.aliyuncs.com");
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			// 控制台创建的签名名称
			request.setSignName(Constants.ALIYUN_SMS_SIGN);
			// 控制台创建的模板CODE
			request.setTemplateCode(Constants.ALIYUN_SMS_TEMPLATE_CODE);
			// 短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
			request.setParamString("{\"yzm\":\""+yzm+"\"}");
			// 接收号码
			request.setRecNum(phone);
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			logger.info("短信发送返回RequestId："+ httpResponse.getRequestId());
			logger.info("短信发送返回Model："+ httpResponse.getModel());
			return StatusCode.SUCCESS_CODE;  // 发送成功
		} catch (ServerException e) {
			logger.error("阿里云短信平台发送异常！发送手机号：" + phone + "，返回ErrCode：" + e.getErrCode() +"，ErrMsg：" + e.getErrMsg());
			if(e.getErrCode().equals("InvalidSendSms") && e.getErrMsg().equals("Frequency limit reaches.")){
				// 验证码发送过于频繁
				return StatusCode.FREQUENCY_LIMIT_REACHES;
			}
		} catch (ClientException e) {
			logger.error("阿里云短信平台发送异常！发送手机号：" + phone + "，返回ErrCode：" + e.getErrCode() +"，ErrMsg：" + e.getErrMsg());
			if(e.getErrCode().equals("InvalidSendSms") && e.getErrMsg().equals("Frequency limit reaches.")){
				// 验证码发送过于频繁
				return StatusCode.FREQUENCY_LIMIT_REACHES;
			}
		} catch (Exception e) {
			logger.error("阿里云短信平台发送异常！发送手机号：" + phone );
		}
		return StatusCode.ERROR_CODE;
	}
}
