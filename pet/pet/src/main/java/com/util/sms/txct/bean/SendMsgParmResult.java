package com.util.sms.txct.bean;

/**
 * 
 *    
 * 类名称：SendMsgParmResult   
 * 类描述：短信通道接口返回对象   
 * 创建人：Alsfox@lynn.com   
 * 创建时间：2015-7-6 下午05:23:04       
 *
 */
public class SendMsgParmResult {
	
	private String returnstatus;              // 返回状态值：成功返回Success 失败返回：Faild
	private String message;                   // 返回信息提示
	private Integer remainpoint;              // 剩余条数
	private long taskID;                      // 返回本次任务的序列ID
	private Integer successCounts;            // 成功条数
	
	
	/********************************************************
	  
	     【返回信息提示】	                                          【说明】
	    
	ok	                                                                提交成功
	用户名或密码不能为空	          	        提交的用户名或密码为空
	发送内容包含sql注入字符	 		        包含sql注入字符
	用户名或密码错误	                                        表示用户名或密码错误
	短信号码不能为空			                        提交的被叫号码为空
	短信内容不能为空			                        发送内容为空
	包含非法字符：				                        表示检查到不允许发送的非法字符
	对不起，您当前要发送的量大于您当前余额	当支付方式为预付费是，检查到账户余额不足
	其他错误					                        其他数据库操作方面的错误
	
	********************************************************/
	
	
	public String getReturnstatus() {
		return returnstatus;
	}
	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getRemainpoint() {
		return remainpoint;
	}
	public void setRemainpoint(Integer remainpoint) {
		this.remainpoint = remainpoint;
	}
	public long getTaskID() {
		return taskID;
	}
	public void setTaskID(long taskID) {
		this.taskID = taskID;
	}
	public Integer getSuccessCounts() {
		return successCounts;
	}
	public void setSuccessCounts(Integer successCounts) {
		this.successCounts = successCounts;
	}
	
	

}
