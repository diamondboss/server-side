package com.personal.dog.dao;

import com.personal.util.pojo.PetBasePhotoPojo;

public interface IPetBasePhotoDao {
	
    int deleteByPrimaryKey(Long id);

    int insert(PetBasePhotoPojo record);

    int insertSelective(PetBasePhotoPojo record);

    PetBasePhotoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetBasePhotoPojo record);

    int updateByPrimaryKey(PetBasePhotoPojo record);
}