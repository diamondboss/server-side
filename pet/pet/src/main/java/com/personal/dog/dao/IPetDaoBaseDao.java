package com.personal.dog.dao;

import com.personal.util.pojo.PetDaoBasePojo;

public interface IPetDaoBaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(PetDaoBasePojo record);

    int insertSelective(PetDaoBasePojo record);

    PetDaoBasePojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetDaoBasePojo record);

    int updateByPrimaryKey(PetDaoBasePojo record);
}