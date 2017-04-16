package com.personal.dog.dao;

import java.util.List;

import com.personal.util.dto.PetBaseInfoDto;
import com.personal.util.pojo.PetBaseInfoPojo;

public interface IPetBaseInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(PetBaseInfoPojo record);

    int insertSelective(PetBaseInfoPojo record);

    PetBaseInfoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetBaseInfoPojo record);

    int updateByPrimaryKey(PetBaseInfoPojo record);
    
    /**
     * 查询宠物详细信息
     * @param param
     * @return
     */
    List<PetBaseInfoDto> queryInfoById(int id);
}