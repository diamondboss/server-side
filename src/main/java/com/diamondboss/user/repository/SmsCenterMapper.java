package com.diamondboss.user.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.user.pojo.SmsCenterPojo;
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
	public int insertSmsCenter(SmsCenterPojo pojo);
	
	/**
	 * 查询是否有新消息
	 * @param userId
	 * @return
	 */
	public int queryNewSms(Map<String, String> map);
	
	/**
	 * 查询用户的消息列表
	 * @param map
	 * @return
	 */
	public List<SmsQueryListVo> querySmsList(Map<String, String> map);
	
	/**
	 * 更新用户消息状态为已读
	 * @param map
	 * @return
	 */
	public int updateSmsStatusForUser(Map<String, String> map);
}
