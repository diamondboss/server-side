package com.diamondboss.order.repository;

import com.diamondboss.util.pojo.CommunityPojo;

public interface CommunityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommunityPojo record);

    int insertSelective(CommunityPojo record);

    CommunityPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommunityPojo record);

    int updateByPrimaryKey(CommunityPojo record);
}