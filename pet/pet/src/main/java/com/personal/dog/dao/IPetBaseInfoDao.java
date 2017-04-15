package com.personal.dog.dao;

import com.personal.util.pojo.PetBaseInfoPojo;

public interface IPetBaseInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(PetBaseInfoPojo record);

    int insertSelective(PetBaseInfoPojo record);

    PetBaseInfoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetBaseInfoPojo record);

    int updateByPrimaryKey(PetBaseInfoPojo record);
}