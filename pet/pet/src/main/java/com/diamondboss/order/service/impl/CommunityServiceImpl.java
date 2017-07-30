package com.diamondboss.order.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private CommunityMapper communityMapper;

	@Override
	public CommunityResponseVo queryCommunityId(String communityName) {
		return communityMapper.queryCommunityId(communityName);
	}

}
