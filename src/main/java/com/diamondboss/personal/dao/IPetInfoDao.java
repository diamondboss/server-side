package com.diamondboss.personal.dao;

import com.diamondboss.util.pojo.PetInfoPojo;

public interface IPetInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(PetInfoPojo record);

    int insertSelective(PetInfoPojo record);

    PetInfoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetInfoPojo record);

    int updateByPrimaryKey(PetInfoPojo record);
}