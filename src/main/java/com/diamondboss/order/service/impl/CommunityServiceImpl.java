package com.diamondboss.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.diamondboss.order.repository.CommunityMapper;
import com.diamondboss.order.service.ICommunityService;
import com.diamondboss.util.pojo.CommunityPojo;
import com.diamondboss.util.vo.CommunityVo;

/**
 * 小区接口实现类
 * @author xzf
 *
 */
@Service(value = "communityService")
public class CommunityServiceImpl implements ICommunityService {

	@Resource
	private CommunityMapper communityMapper;

	@Override
	public List<CommunityVo> queryCommunitys() {
		Map<String, String> map = new HashMap<>();
		map.put("effective", "1");
		
		List<CommunityPojo>  communityList = communityMapper.queryCommunitys(map);
		
		List<CommunityVo> communitys = new ArrayList<>();
		for (CommunityPojo communityPojo : communityList) {
			CommunityVo community = new CommunityVo();
			community.setId(communityPojo.getId());
			community.setCommunityName(communityPojo.getCommunityName());
			community.setImagesUrl(communityPojo.getImagesUrl());
			
			communitys.add(community);
		}
		return communitys;
	}

}
