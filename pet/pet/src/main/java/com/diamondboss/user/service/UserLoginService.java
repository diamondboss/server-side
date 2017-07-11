package com.diamondboss.user.service;

import java.util.Map;

import com.diamondboss.user.pojo.UserLoginPojo;
import com.diamondboss.user.vo.LoginVo;

public interface UserLoginService {

	/**
	 * 登录
	 * @param vo
	 * @return
	 */
	public UserLoginPojo login(LoginVo vo);
	
	/**
	 * 插入用户
	 * @param vo
	 * @return
	 */
	public Map<String, Integer> insertUser(LoginVo vo);
	
	/**
	 * 插入用户的clientId到userClientId表
	 * @param vo
	 * @return
	 */
	public int insertUserClientId(String userId, String clientId);
	
	/**
	 * 查询用户的clientId到userClientId表是否已经存在
	 * @param vo
	 * @return
	 */
	public int selectUserClientId(String userId, String clientId);
	
}
