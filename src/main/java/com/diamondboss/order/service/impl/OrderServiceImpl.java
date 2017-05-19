package com.diamondboss.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.diamondboss.order.repository.CommunityMapper;
import com.diamondboss.order.repository.ParterInfoMapper;
import com.diamondboss.order.repository.ParterOrderMapper;
import com.diamondboss.order.service.IOrderService;
import com.diamondboss.util.pojo.CommunityPojo;
import com.diamondboss.util.pojo.ParterInfoPojo;
import com.diamondboss.util.pojo.ParterOrderPojo;
import com.diamondboss.util.vo.UserDetailVo;

/**
 * 小区服务实现类
 * @author xzf
 *
 */
@Service(value = "orderService")
public class OrderServiceImpl implements IOrderService {

	 @Resource
	 private CommunityMapper communityMapper;
	 
	 @Resource
	 private ParterInfoMapper parterInfoMapper;
	 
	 @Resource
	 private ParterOrderMapper parterOrderMapper;
	
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
	public Map<String, Integer> countParter(ParterInfoPojo parterInfoPojo) {
		List<ParterInfoPojo> parterInfos = parterInfoMapper.countParter(parterInfoPojo);
		int ParterNum = 0;
		for (ParterInfoPojo parterInfo : parterInfos) {
			ParterNum = ParterNum + Integer.valueOf(parterInfo.getRaisenumber());
		}
		
		
		
		Map<String, Integer> map = new HashMap<>();
		map.put("ParterNum", ParterNum);
		map.put("ParterSize", parterInfos.size());
		
		return map;
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

	/**
	 * 查询用户的预约明细
	 * @param communityId
	 * @return
	 */
	@Override
	public List<UserDetailVo> queryUserDetail(String parter_id) {
		Map<String, String> map = new HashMap<>();
		map.put("parter_id", parter_id);
		map.put("effective", "1");
		
		List<ParterOrderPojo>  userDetailList = parterOrderMapper.queryUserDetail(map);
		
		List<UserDetailVo> userDetails = new ArrayList<>();
		for (ParterOrderPojo parterOrder : userDetailList) {
			UserDetailVo userDetail = new UserDetailVo();
			userDetail.setOrderTime(parterOrder.getOrderTime());
			userDetail.setCreateTime(parterOrder.getCreateTime());
			userDetails.add(userDetail);
		}
		return userDetails;
	}
	
}
