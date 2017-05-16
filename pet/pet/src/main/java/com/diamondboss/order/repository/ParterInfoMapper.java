package com.diamondboss.order.repository;

import java.util.List;

import com.diamondboss.util.pojo.ParterInfoPojo;

public interface ParterInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ParterInfoPojo record);

    int insertSelective(ParterInfoPojo record);

    ParterInfoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ParterInfoPojo record);

    int updateByPrimaryKey(ParterInfoPojo record);
    
    /**
     * 计算某个小区中的合伙人数量
     * @param parterInfoPojo
     * @return
     */
    List<ParterInfoPojo> countParter(ParterInfoPojo parterInfoPojo);
}