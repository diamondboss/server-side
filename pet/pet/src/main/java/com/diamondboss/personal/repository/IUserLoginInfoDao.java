package com.diamondboss.personal.repository;

import com.diamondboss.util.pojo.UserLoginInfoPojo;

public interface IUserLoginInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserLoginInfoPojo record);

    int insertSelective(UserLoginInfoPojo record);

    UserLoginInfoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLoginInfoPojo record);

    int updateByPrimaryKey(UserLoginInfoPojo record);
}