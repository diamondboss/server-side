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
	 * 向合伙人消息表中，插入合伙人消息
	 * @return
	 */
	public int insertSmsForPartner(SmsCenterPojo pojo);
	
	/**
	 * 查询用户是否有新消息
	 * @param userId
	 * @return
	 */
	public int queryNewSmsForUser(String userId);
	
	/**
	 * 查询合伙人是否有新消息
	 * @param userId
	 * @return
	 */
	public int queryNewSmsForPartner(String partnerId);
	
	/**
	 * 查询用户的消息列表
	 * @param userId
	 * @return
	 */
	public List<SmsQueryListVo> querySmsListForUser(String userId);
	
	/**
	 * 查询合伙人的消息列表
	 * @param userId
	 * @return
	 */
	public List<SmsQueryListVo> querySmsListForPartner(String partnerId);
	
	/**
	 * 更新用户消息状态为已读
	 * @param userId
	 * @return
	 */
	public int updateSmsStatusForUser(String userId);
	
	/**
	 * 更新合伙人消息状态为已读
	 * @param userId
	 * @return
	 */
	public int updateSmsStatusForPartner(String partnerId);
}
