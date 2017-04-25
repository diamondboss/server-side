package com.diamondboss.callMe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.diamondboss.callMe.dao.IUserDao;
import com.diamondboss.callMe.service.IUserService;
import com.diamondboss.util.User;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;

	@Override
	public User getUserById(int userId) {

		return this.userDao.selectByPrimaryKey(userId);
	}
}
