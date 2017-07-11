package com.diamondboss.order.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.diamondboss.order.repository.CommunityMapper;
import com.diamondboss.order.service.ICommunityService;
import com.diamondboss.order.vo.CommunityResponseVo;

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
	public CommunityResponseVo queryCommunityId(String communityName) {
		return communityMapper.queryCommunityId(communityName);
	}

}
