package com.personal.dog.dao;

import com.personal.util.pojo.petBaseInfoPojo;

public interface petBaseInfoPojoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(petBaseInfoPojo record);

    int insertSelective(petBaseInfoPojo record);

    petBaseInfoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(petBaseInfoPojo record);

    int updateByPrimaryKey(petBaseInfoPojo record);
}