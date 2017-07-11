package com.diamondboss.order.repository;

import com.diamondboss.order.vo.CommunityResponseVo;

public interface CommunityMapper {
    int deleteByPrimaryKey(Long id);
    
    CommunityResponseVo queryCommunityId(String communityName);
}