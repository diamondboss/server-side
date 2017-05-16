package com.diamondboss.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.diamondboss.order.repository.CommunityMapper;
import com.diamondboss.order.repository.ParterInfoMapper;
import com.diamondboss.order.repository.ParterOrderMapper;
import com.diamondboss.order.service.IOrderService;
import com.diamondboss.util.pojo.CommunityPojo;
import com.diamondboss.util.pojo.ParterInfoPojo;
import com.diamondboss.util.pojo.ParterOrderPojo;

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
	 
	 @Resource
	 ParterOrderMapper parterOrderMapper;
	
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
	public List<ParterInfoPojo> countParter(ParterInfoPojo parterInfoPojo) {
		return parterInfoMapper.countParter(parterInfoPojo);
	}

	/**
	 * /**
	 * 根据小区ID，查询该小区有多少狗已预约
	 * @param parterOrderPojo
	 * @return
	 */
	@Override
	public int countParterOrder(ParterOrderPojo parterOrderPojo) {
		return parterOrderMapper.countParterOrder(parterOrderPojo);
	}
	
}
