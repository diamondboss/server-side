package com.diamondboss.personal.service;

import com.diamondboss.util.pojo.UserInfoPojo;

/**
 * Created by focus.liu on 2017/4/23.
 */
public interface IUserService {
    /**
     * 使用电话号码登录
     * @param phoneNumber 电话
     * @return
     */
    public boolean login(String phoneNumber);

    /**
     * 录入用户信息
     * @param userInfo
     * @return
     */
    public boolean inputUserInfo(UserInfoPojo userInfo);

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    public UserInfoPojo getUserById(long userId);
}
