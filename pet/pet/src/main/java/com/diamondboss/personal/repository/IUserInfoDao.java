package com.diamondboss.personal.repository;

import com.diamondboss.util.pojo.UserInfoPojo;

public interface IUserInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfoPojo record);

    int insertSelective(UserInfoPojo record);

    UserInfoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfoPojo record);

    int updateByPrimaryKey(UserInfoPojo record);
}