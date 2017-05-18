package com.diamondboss.personal.service.impl;

import com.diamondboss.personal.repository.UserInfoMapper;
import com.diamondboss.personal.repository.UserLoginInfoMapper;
import com.diamondboss.personal.service.IUserService;
import com.diamondboss.util.pojo.UserInfoPojo;
import com.diamondboss.util.pojo.UserLoginInfoPojo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

/**
 * Created by focus.liu on 2017/4/23.
 */
@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    UserLoginInfoMapper loginInfoMapper;

    @Override
    public boolean login(String phoneNumber) {
        UserLoginInfoPojo loginInfoPojo = new UserLoginInfoPojo();
        loginInfoPojo.setPhoneNumber(phoneNumber);
        loginInfoPojo.setUserId(new Long(0));
        loginInfoPojo.setPetId(new Long(0));
        loginInfoPojo.setEffective(true);
        int result =  loginInfoMapper.insert(loginInfoPojo);
        if (result >0 ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean inputUserInfo(UserInfoPojo userInfo) {
        userInfo.setEffective(true);
        int result =  userInfoMapper.insert(userInfo);
        if (result >0 ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserInfoPojo getUserById(long userId) {
        return  userInfoMapper.selectByPrimaryKey(userId);
    }

	@Override
	public boolean updateUserLoginCount(String phoneNum) {
		Map<String, String> map = new HashMap<>();
		map.put("phoneNumber", phoneNum);
		
		int result = loginInfoMapper.updateLoginIn(map);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserLoginInfoPojo queryUserLoginIn(String phoneNumber) {
		Map<String, String> map = new HashMap<>();
		map.put("phoneNumber", phoneNumber);
		
		return loginInfoMapper.queryUserLoginIn(map);
	}

	@Override
	public boolean insertUserLoginIn(UserLoginInfoPojo userLoginInfoPojo) {
		int result = loginInfoMapper.insertSelective(userLoginInfoPojo);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

}
