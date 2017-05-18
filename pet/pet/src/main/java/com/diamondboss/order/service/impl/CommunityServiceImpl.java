package com.diamondboss.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.diamondboss.order.repository.CommunityMapper;
import com.diamondboss.order.service.ICommunityService;
import com.diamondboss.util.pojo.CommunityPojo;

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
	public List<CommunityPojo> queryCommunitys() {
		Map<String, String> map = new HashMap<>();
		map.put("effective", "1");
		
		List<CommunityPojo>  communityList = communityMapper.queryCommunitys(map);
		return communityList;
	}

}
