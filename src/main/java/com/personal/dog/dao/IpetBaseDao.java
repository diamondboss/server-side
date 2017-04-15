package com.personal.dog.dao;

import java.util.Map;

import com.personal.util.pojo.PetBasePojo;

public interface IpetBaseDao {
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
    PetBasePojo selectByLimti(Map<String, Object> param);
    
}