package com.diamondboss.user.service.impl;

import com.diamondboss.user.pojo.UserInfoPojo;
import com.diamondboss.user.repository.UserInfoMapper;
import com.diamondboss.user.service.UserInfoService;
import com.diamondboss.user.vo.UserInfoVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfoPojo queryUserInfo(UserInfoVo vo) {
		UserInfoPojo UserInfoPojo = userInfoMapper.queryInfoByUserId(vo.getUserId());
		return UserInfoPojo;
	}

	@Override
	public int inputUserInfo(UserInfoPojo pojo) {
		return userInfoMapper.insertInfoByUserId(pojo);
	}

	@Override
	public int updateUserInfo(UserInfoPojo pojo) {
		return userInfoMapper.updateInfoByUserId(pojo);
	}

	@Override
	public UserInfoPojo queryUserInfoOfUpdate(UserInfoPojo pojo) {
		UserInfoPojo UserInfoPojo = userInfoMapper.queryInfoByUserId(pojo.getUserId());
		return UserInfoPojo;
	}

}
