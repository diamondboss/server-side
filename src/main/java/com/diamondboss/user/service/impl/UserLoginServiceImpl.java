package com.diamondboss.user.service.impl;

import com.diamondboss.user.pojo.UserLoginPojo;
import com.diamondboss.user.repository.UserLoginMapper;
import com.diamondboss.user.service.UserLoginService;
import com.diamondboss.user.vo.LoginVo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService{

	private static Logger logger = LogManager.getLogger(UserLoginServiceImpl.class);
	
	@Autowired
	private UserLoginMapper userLoginMapper;
	
	public UserLoginPojo login(LoginVo vo){
		UserLoginPojo userLogin = userLoginMapper.queryUserLoginByPhone(vo.getPhone());
		if(userLogin == null){
			logger.info("查询用户不存在");
			return userLogin;
		}
		
		return userLogin;
	}

	@Override
	public int insertUser(LoginVo vo) {
		int result = userLoginMapper.insertUserLoginByPhone(vo.getPhone());
		return result;
	}
}
