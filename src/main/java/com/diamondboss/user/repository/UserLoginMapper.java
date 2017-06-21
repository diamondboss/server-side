package com.diamondboss.user.repository;

import com.diamondboss.user.pojo.UserLoginPojo;

public interface UserLoginMapper {
	
	/**
	 * 登录查询
	 */
	public UserLoginPojo queryUserLoginByPhone(String phoneNumber);
	
	/**
	 * 登录插入
	 */
	public int insertUserLoginByPhone(String phoneNumber);
	
	
	
	
}
