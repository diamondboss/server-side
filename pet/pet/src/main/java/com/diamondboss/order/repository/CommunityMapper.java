package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.order.vo.CommunityResponseVo;
import com.diamondboss.util.pojo.CommunityPojo;

public interface CommunityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommunityPojo record);

    int insertSelective(CommunityPojo record);

    CommunityPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommunityPojo record);

    int updateByPrimaryKey(CommunityPojo record);
    
    List<CommunityPojo> queryCommunitys(Map<String, String> map);
    
    CommunityResponseVo queryCommunityId(String communityName);
}