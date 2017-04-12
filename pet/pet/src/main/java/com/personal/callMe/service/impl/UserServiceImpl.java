package com.personal.callMe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personal.callMe.dao.IUserDao;
import com.personal.callMe.service.IUserService;
import com.personal.util.User;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;

	@Override
	public User getUserById(int userId) {

		return this.userDao.selectByPrimaryKey(userId);
	}
}
