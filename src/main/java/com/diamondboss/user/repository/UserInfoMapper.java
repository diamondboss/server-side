package com.diamondboss.user.repository;

import com.diamondboss.user.pojo.UserInfoPojo;

/**
 * 用户信息数据访问
 * 
 * @author John
 * @since 2017-06-20
 *  
 */
public interface UserInfoMapper {

	/**
	 * 根据用户id查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfoPojo queryInfoByUserId(String userId);
	
	/**
	 * 插入用户信息
	 * 
	 * @param pojo
	 * @return
	 */
	public int insertInfoByUserId(UserInfoPojo pojo);
	
	/**
	 * 更新用户信息
	 * 
	 * @param pojo
	 * @return
	 */
	public int updateInfoByUserId(UserInfoPojo pojo);
	
}
