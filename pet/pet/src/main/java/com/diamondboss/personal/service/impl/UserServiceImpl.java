package com.diamondboss.personal.service.impl;

import com.diamondboss.personal.repository.UserInfoMapper;
import com.diamondboss.personal.service.IUserService;
import com.diamondboss.util.pojo.UserInfoPojo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by focus.liu on 2017/4/23.
 */
@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public boolean login(String phoneNumber) {
        UserInfoPojo userInfoPojo = new UserInfoPojo("",phoneNumber,"","","");
        userInfoPojo.setEffective(true);
        return insertUser(userInfoPojo);
    }

    @Override
    public boolean inputUserInfo(UserInfoPojo userInfo) {
        userInfo.setEffective(true);
        return insertUser(userInfo);
    }

    @Override
    public UserInfoPojo getUserById(long userId) {
        return  userInfoMapper.selectByPrimaryKey(userId);
    }

    /**
     * 入库操作
     * @param userInfo
     * @return
     */
    private boolean insertUser(UserInfoPojo userInfo){
        int result =  userInfoMapper.insert(userInfo);
        if (result >0 ){
            return true;
        } else {
            return false;
        }
    }
}
