package com.diamondboss.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.user.pojo.UserLoginPojo;
import com.diamondboss.user.repository.UserLoginMapper;
import com.diamondboss.user.service.UserLoginService;
import com.diamondboss.user.vo.LoginVo;

@Service
public class UserLoginServiceImpl implements UserLoginService{

	private static Logger logger = Logger.getLogger(UserLoginServiceImpl.class);
	
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
	public Map<String, Integer> insertUser(LoginVo vo) {
		int result = userLoginMapper.insertUserLoginByPhone(vo);
		
		Map<String, Integer> responseMap = new HashMap<>();
		responseMap.put("result", result);
		responseMap.put("id", vo.getId());
		
		return responseMap;
	}

	@Override
	public int insertUserClientId(String userId, String clientId) {
		Map<String, String> param = new HashMap<>();
		param.put("userId", userId);
		param.put("clientId", clientId);
		
		return userLoginMapper.insertUserClientId(param);
	}

	@Override
	public int selectUserClientId(String userId, String clientId) {
		Map<String, String> param = new HashMap<>();
		param.put("userId", userId);
		param.put("clientId", clientId);
		
		return userLoginMapper.insertUserClientId(param);
	}

	@Override
	public String selectUserClientId(String userId) {
		Map<String, String> param = new HashMap<>();
		param.put("userId", userId);
		
		return userLoginMapper.selectUserCID(param);
	}
}
