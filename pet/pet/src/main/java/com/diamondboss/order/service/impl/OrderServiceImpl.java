package com.diamondboss.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.diamondboss.order.repository.CommunityMapper;
import com.diamondboss.order.repository.ParterInfoMapper;
import com.diamondboss.order.service.IOrderService;
import com.diamondboss.util.pojo.CommunityPojo;
import com.diamondboss.util.pojo.ParterInfoPojo;

/**
 * 小区服务实现类
 * @author xzf
 *
 */
@Service(value = "orderService")
public class OrderServiceImpl implements IOrderService {

	 @Resource
	 CommunityMapper communityMapper;
	 
	 @Resource
	 ParterInfoMapper parterInfoMapper;
	
	/**
	 * 根据小区ID，查询小区信息
	 */
	@Override
	public CommunityPojo queryCommunity(Long communityId) {
		return communityMapper.selectByPrimaryKey(communityId);
	}

	/**
	 * 根据小区ID，查询该小区有多少位合伙人
	 * @return
	 */
	@Override
	public int countParter(ParterInfoPojo parterInfoPojo) {
		return parterInfoMapper.countParter(parterInfoPojo);
	}
	
}
