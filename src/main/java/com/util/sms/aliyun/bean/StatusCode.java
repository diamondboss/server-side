package com.util.sms.aliyun.bean;

public class StatusCode {
	public static Integer SUCCESS_CODE = 200;					//请求操作成功
	public static Integer ERROR_CODE = 300;						//请求操作失败
	public static Integer SIGN_ERROR = 313;						//签名错误
	public static Integer TOKEN_ERROR = 314;					//请求Token已经失效
	
	public static Integer INFO_CODE = 201;						//数据异常
	public static Integer NOREGUSER = 202;						//未注册的用户：第三方用户登录
	public static Integer BOUNDUSER = 203;						//已被绑定的手机号或邮箱
	public static Integer BINDNOTFOUND = 204;					//用户未绑定手机号和邮箱
	public static Integer NORESULT = 205;						//当查询成功但是数据为空时
	public static Integer CHALLENCE_SUCCESS = 206;				//挑战成功返回状态标识
	public static Integer RELATIONBLACK = 207;					//黑名单关系返回标识
	
	public static Integer NOTICE_ACTION_NOT_GO_ON = 208;		//不能继续操作消息标识
	public static Integer FREQUENCY_LIMIT_REACHES = 209;		//短信验证码发送过于频繁
	
	public static Integer SIGN_CHALLENGE_FALL = 210;			//签到时候，触发的挑战失败
	public static Integer PAY_PASSWORD_ERROR = 212;				//支付密码错误
	
}
