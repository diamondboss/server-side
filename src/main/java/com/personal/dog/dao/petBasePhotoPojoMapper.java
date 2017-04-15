package com.personal.dog.dao;

import com.personal.util.pojo.petBasePhotoPojo;

public interface petBasePhotoPojoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(petBasePhotoPojo record);

    int insertSelective(petBasePhotoPojo record);

    petBasePhotoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(petBasePhotoPojo record);

    int updateByPrimaryKey(petBasePhotoPojo record);
}