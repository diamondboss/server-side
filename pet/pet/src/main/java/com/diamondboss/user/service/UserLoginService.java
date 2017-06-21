package com.diamondboss.user.service;

import com.diamondboss.user.pojo.UserLoginPojo;
import com.diamondboss.user.util.vo.LoginVo;

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
	public int insertUser(LoginVo vo);
	
}
