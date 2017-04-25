package com.diamondboss.dog.repository;

import com.diamondboss.util.pojo.PetDogBasePojo;

public interface IPetDogBaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(PetDogBasePojo record);

    int insertSelective(PetDogBasePojo record);

    PetDogBasePojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetDogBasePojo record);

    int updateByPrimaryKey(PetDogBasePojo record);
}