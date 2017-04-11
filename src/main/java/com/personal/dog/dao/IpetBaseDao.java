package com.personal.dog.dao;

import com.personal.util.pojo.petBasePojo;

public interface IpetBaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(petBasePojo record);

    int insertSelective(petBasePojo record);

    petBasePojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(petBasePojo record);

    int updateByPrimaryKey(petBasePojo record);
}