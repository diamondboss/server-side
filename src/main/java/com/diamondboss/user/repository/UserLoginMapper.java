package com.diamondboss.user.repository;

import java.util.Map;

import com.diamondboss.user.pojo.UserLoginPojo;
import com.diamondboss.user.vo.LoginVo;

public interface UserLoginMapper {
	
	/**
	 * 登录查询
	 */
	public UserLoginPojo queryUserLoginByPhone(String phoneNumber);
	
	/**
	 * 登录插入
	 */
	public int insertUserLoginByPhone(LoginVo vo);
	
	/**
	 * 插入用户的clientId到userClientId表
	 * @param map
	 * @return
	 */
	public int insertUserClientId(Map<String, String> map);
	
	/**
	 * 查询用户的clientId到userClientId表是否已经存在
	 * @param map
	 * @return
	 */
	public int selectUserClientId(Map<String, String> map);
	
	/**
	 * 通过userId查出用户的CID
	 * @param map
	 * @return
	 */
	public String selectUserCID(Map<String, String> map);
}
