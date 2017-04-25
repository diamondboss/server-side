package com.diamondboss.dog.dao;

import java.util.List;

import com.diamondboss.util.pojo.PetBasePhotoPojo;

public interface IPetBasePhotoDao {
	
    int deleteByPrimaryKey(Long id);

    int insert(PetBasePhotoPojo record);

    int insertSelective(PetBasePhotoPojo record);

    PetBasePhotoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetBasePhotoPojo record);

    int updateByPrimaryKey(PetBasePhotoPojo record);
    
    /**
     * 查询宠物图片信息
     * @param param
     * @return
     */
    List<String> queryURLById(int id);
}