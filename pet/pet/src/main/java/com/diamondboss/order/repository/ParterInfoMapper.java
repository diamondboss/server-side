package com.diamondboss.order.repository;

import com.diamondboss.util.pojo.ParterInfoPojo;

public interface ParterInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ParterInfoPojo record);

    int insertSelective(ParterInfoPojo record);

    ParterInfoPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ParterInfoPojo record);

    int updateByPrimaryKey(ParterInfoPojo record);
}