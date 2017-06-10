package com.diamondboss.util.push.rongyun.constcla;

/**
 * 
 * 融云短信服务返回码
 * @author zfxue
 *
 */
public class Constants {
	//发送短信验证码
	
	//成功
	public static final String SUCCES = "200";
	
	//没有开启图验功能
	public static final String NO_IMAGE_VALIDATA = "1009";
	//未使用已开启的图验功能
	public static final String NO_USE_IMAGE_VALIDATA = "1010";
	//内部逻辑错误
	public static final String INSIDE_ERROR = "1000";
	//参数错误
	public static final String PARAMS_ERROR = "1003";
	//参数长度超出限制
	public static final String PARAMS_TOO_LONG = "1005";
	//调用超过频率上限
	public static final String USE_TOO_MORE = "1008";
	//缺少参数
	public static final String LACK_PARAMS = "1002";
	//验证签名错误
	public static final String VALIDATA_ERROR = "1004";
	//内部服务响应超时
	public static final String SERVICE_TIMEOUT = "1050";
	//图片验证码不正确
	public static final String IMAGE_VALIDATA_ERROR = "1012";
	//剩余条数不足，需要充值
	public static final String MONEY_POOL = "1011";
	//短信通道不可用
	public static final String SMS_DISABLE = "1013";
	//短信验证码已验证过，再次验证失效
	public static final String AGAIN_VALIDATA = "1014";
	//短信验证码过期无效
	public static final String VALIDATA_TIMEOUT = "1015";
	
	
	
	public static final String SESSIONID = "sessionId";
	
}
