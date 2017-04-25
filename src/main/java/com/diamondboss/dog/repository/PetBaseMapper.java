package com.diamondboss.dog.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.util.pojo.PetBasePojo;

public interface PetBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PetBasePojo record);

    int insertSelective(PetBasePojo record);

    PetBasePojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetBasePojo record);

    int updateByPrimaryKey(PetBasePojo record);
    
    /**
     * 查询宠物基本信息
     * @param param
     * @return
     */
    List<PetBasePojo> selectByLimit(Map<String, Object> param);
    
}