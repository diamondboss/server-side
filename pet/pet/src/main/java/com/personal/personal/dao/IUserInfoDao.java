package com.personal.personal.dao;

import com.personal.util.pojo.UserInfoPojo;

public interface IUserInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfoPojo record);

    int insertSelective(UserInfoPojo record);

    UserInfoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfoPojo record);

    int updateByPrimaryKey(UserInfoPojo record);
}