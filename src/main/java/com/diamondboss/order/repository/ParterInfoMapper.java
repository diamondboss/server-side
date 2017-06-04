package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

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
     * @param map
     * @return
     */
    List<ParterInfoPojo> countParter(Map<String, String> map);

    /**
     * 根据小区id，查询小区人的合伙人信息
     * @return
     */
    List<ParterInfoPojo> queryPartnerByCommunityId(String communityId);
}