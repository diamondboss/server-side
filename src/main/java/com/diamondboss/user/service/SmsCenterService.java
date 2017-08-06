package com.diamondboss.user.service;

import java.util.List;

import com.diamondboss.user.pojo.SmsCenterPojo;
import com.diamondboss.user.vo.SmsQueryListVo;

/**
 * 消息中心接口
 * @author xzf
 *
 */
public interface SmsCenterService {
	
	/**
	 * 向用户消息表中，插入用户消息
	 * @return
	 */
	public int insertSmsForUser(SmsCenterPojo pojo);
	
	/**
	 * 查询是否有新消息
	 * @param userId
	 * @return
	 */
	public int queryNewSms(String userId);
	
	/**
	 * 查询用户的消息列表
	 * @param userId
	 * @return
	 */
	public List<SmsQueryListVo> querySmsListForUser(String userId);
	
	/**
	 * 更新用户消息状态为已读
	 * @param userId
	 * @return
	 */
	public int updateSmsStatusForUser(String userId);
}
