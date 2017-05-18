package com.diamondboss.personal.repository;

import java.util.Map;

import com.diamondboss.util.pojo.UserLoginInfoPojo;

public interface UserLoginInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLoginInfoPojo record);

    int insertSelective(UserLoginInfoPojo record);

    UserLoginInfoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLoginInfoPojo record);

    int updateByPrimaryKey(UserLoginInfoPojo record);
    
    int updateLoginIn(Map<String, String> map);
    
    UserLoginInfoPojo queryUserLoginIn(Map<String, String> map);
}