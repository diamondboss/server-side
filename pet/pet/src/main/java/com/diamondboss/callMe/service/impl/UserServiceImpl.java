package com.diamondboss.callMe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.diamondboss.callMe.repository.UserMapper;
import com.diamondboss.callMe.service.IUserService;
import com.diamondboss.util.User;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userDao;

	@Override
	public User getUserById(int userId) {

		return this.userDao.selectByPrimaryKey(userId);
	}
}
