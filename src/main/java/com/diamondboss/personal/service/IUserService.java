package com.diamondboss.personal.service;

import java.util.Map;

import com.diamondboss.util.pojo.UserInfoPojo;
import com.diamondboss.util.pojo.UserLoginInfoPojo;

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
    
    /**
     * 更新用户的登录次数
     * @param userInfo
     * @return
     */
    public boolean updateUserLoginCount(String phoneNumber);
    
    /**
     * 根据手机号查询到用户登录信息
     * @param loginInfoPojo
     * @return
     */
    public Map queryUserLoginIn(String phoneNumber);
    
    /**
     * 插入用户登录信息
     * @param userLoginInfoPojo
     * @return
     */
    public boolean insertUserLoginIn(UserLoginInfoPojo userLoginInfoPojo);
}
