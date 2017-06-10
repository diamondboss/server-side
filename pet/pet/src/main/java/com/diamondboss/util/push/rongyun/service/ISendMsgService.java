package com.diamondboss.util.push.rongyun.service;


/**
 * 
 * All rights Reserved, Designed By alsfox.com   
 * @ClassName:  ISendMsgService
 * @Description:TODO(发送短信通道接口)   
 * @author: Lynn  
 * @date:   2016-12-24 下午2:44:37   
 *
 */
public interface ISendMsgService {

	/**
	 * 
	 * @Title: sendMsg   
	 * @Description: TODO(短信发送方法，phone为短信接收人手机号)   
	 * @param:      
	 * @throws
	 */
	public int sendMsg(String phone);
	
}
