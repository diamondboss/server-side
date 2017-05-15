package com.diamondboss.order.repository;

import com.diamondboss.util.pojo.ParterOrderPojo;

public interface ParterOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ParterOrderPojo record);

    int insertSelective(ParterOrderPojo record);

    ParterOrderPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ParterOrderPojo record);

    int updateByPrimaryKey(ParterOrderPojo record);
}