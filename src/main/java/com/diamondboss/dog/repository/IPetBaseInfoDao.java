package com.diamondboss.dog.repository;

import java.util.List;

import com.diamondboss.util.dto.PetBaseInfoDto;
import com.diamondboss.util.pojo.PetBaseInfoPojo;

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