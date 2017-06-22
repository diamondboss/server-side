package com.diamondboss.user.service;

import com.diamondboss.user.pojo.UserInfoPojo;
import com.diamondboss.user.vo.UserInfoVo;

public interface UserInfoService {

	/**
	 * 根据UserId查询到具体某个用户信息
	 * @return
	 */
	public UserInfoPojo queryUserInfo(UserInfoVo vo);
	
	/**
	 * 插入用户信息
	 * @param pojo
	 * @return
	 */
	public int inputUserInfo(UserInfoPojo pojo);
	
	/**
	 * 更新用户信息
	 * @param pojo
	 * @return
	 */
	public int updateUserInfo(UserInfoPojo pojo);
	
}
