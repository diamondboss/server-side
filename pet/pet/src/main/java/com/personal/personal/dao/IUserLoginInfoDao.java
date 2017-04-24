package com.personal.personal.dao;

import com.personal.util.pojo.UserLoginInfoPojo;

public interface IUserLoginInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserLoginInfoPojo record);

    int insertSelective(UserLoginInfoPojo record);

    UserLoginInfoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLoginInfoPojo record);

    int updateByPrimaryKey(UserLoginInfoPojo record);
}