package com.diamondboss.user.repository;

public interface UserLoginMapper {
	
	/**
	 * 登录查询
	 */
	public void queryUserLoginByPhone(String phoneNumber);
	
	/**
	 * 登录插入
	 */
	public void insertUserLoginByPhone(String phoneNumber);
	
	
	
	
}
