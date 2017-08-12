package com.diamondboss.user.repository;

import java.util.List;
import java.util.Map;
import com.diamondboss.user.vo.SmsQueryListVo;

/**
 * 消息中心数据访问
 * @author xzf
 *
 */
public interface SmsCenterMapper {
	/**
	 * 插入消息
	 * @param pojo
	 * @return
	 */
	public int insertSmsCenter(Map<String, String> map);
	
	/**
	 * 插入合伙人消息
	 * @param pojo
	 * @return
	 */
	public int insertSmsCenterForPartner(Map<String, String> map);
	
	/**
	 * 查询用户是否有新消息
	 * @param userId
	 * @return
	 */
	public int queryNewSmsForUser(Map<String, String> map);
	
	/**
	 * 查询合伙人是否有新消息
	 * @param userId
	 * @return
	 */
	public int queryNewSmsForPartner(Map<String, String> map);
	
	/**
	 * 查询用户的消息列表
	 * @param map
	 * @return
	 */
	public List<SmsQueryListVo> querySmsListForUser(Map<String, String> map);
	
	/**
	 * 查询合伙人的消息列表
	 * @param map
	 * @return
	 */
	public List<SmsQueryListVo> querySmsListForPartner(Map<String, String> map);
	
	/**
	 * 更新用户消息状态为已读
	 * @param map
	 * @return
	 */
	public int updateSmsStatusForUser(Map<String, String> map);
	
	/**
	 * 更新合伙人消息状态为已读
	 * @param map
	 * @return
	 */
	public int updateSmsStatusForPartner(Map<String, String> map);
}
