package com.diamondboss.order.repository;

import com.diamondboss.util.pojo.ParterOrderPojo;

public interface ParterOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ParterOrderPojo record);

    int insertSelective(ParterOrderPojo record);

    ParterOrderPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ParterOrderPojo record);

    int updateByPrimaryKey(ParterOrderPojo record);
    
    /**
     * 计算某个小区的已预约宠物
     * @param parterInfoPojo
     * @return
     */
    int countParterOrder(ParterOrderPojo parterOrderPojo);
}