package com.diamondboss.order.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.repository.ParterInfoMapper;
import com.diamondboss.util.pojo.ParterInfoPojo;
import com.diamondboss.util.tools.TableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.order.repository.SubmitOrderMapper;
import com.diamondboss.order.service.ISubmitOrderService;
import org.springframework.util.CollectionUtils;

@Service
public class SubmitOrderServiceImpl implements ISubmitOrderService{

	@Autowired
	private SubmitOrderMapper submitOrderMapper;

	@Autowired
	private ParterInfoMapper parterInfoMapper;
	
	/**
	 * 根据用户id查询已经预定的订单
	 * @param petId
	 */
	@Override
	public List<String> queryOrderByUser(String petId) {
		
		// 1.根据用户id查询已经预定的订单
		LocalDate today = LocalDate.now();		
		Map<String, String> map = new HashMap<>();
		map.put("today", today.toString());
		map.put("petId", petId);
		List<String> resultList = submitOrderMapper.queryOrderByUser(map);
		
		return resultList;
		
	}

	
	/**
	 * 根据用户id选择日期预约订单
	 */
	@Override
	public List submitOrderByUser() {
		List result = new ArrayList();
		// 1、 晚上10点至早5点不可下单
		int currentHour = LocalDateTime.now().getHour();
		if ((21 < currentHour && currentHour < 23) || (0 < currentHour && currentHour < 4)){
			return result;
		}

		
		List<Object> list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		

		//TODO 小区id
		String commId = "3000004";
		// 2、查询该小区符合条件的社区合伙人
		List<Long> partnerList = getPartnerList(commId);

		// 黑名单过滤

		// 随机合伙人
		if(partnerList == null || partnerList.size() == 0){
			return null;
		}
		Random random = new Random();
		int i = random.nextInt(partnerList.size());
		Long partnerId = partnerList.get(i);


		// 新增订单
		// 插入订单临时表
		submitOrderMapper.insertParterOrder();
		
		// 再查询该合伙人是否超过订单数量
		submitOrderMapper.queryParterOrder();
		
		// 如果超过删除该订单
		if(false){// false
			
		}
		
		// 将该订单日期加入列表
			
		
		return list;
	}

	private List<Long> getPartnerList(String commId){
		List<Long> partnerList = new ArrayList();
		// 1、根据社区id查询有几个合伙人
		List<ParterInfoPojo> partners = parterInfoMapper.queryPartnerByCommunityId(commId);

		if (!CollectionUtils.isEmpty(partners)){
			for (ParterInfoPojo parterInfoPojo : partners){
				// 2、确认合伙人的订单是否已满
				Long id = parterInfoPojo.getId();
				if (checkOrderCountsOfPartner(id, parterInfoPojo.getRaisenumber())) {
					partnerList.add(id);
				}
			}

		}
		return partnerList;
	}

	private boolean checkOrderCountsOfPartner(Long partnerId, String riseNo){
		String tableName = TableUtils.getOrderTableName(partnerId, PetConstants.ORDER_PARTNER_TABLE_PREFIX);

		//查询合伙人的当日订单数量
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", tableName);
		params.put("partnerId", partnerId);
		params.put("currentDate", LocalDate.now());
		int counts = submitOrderMapper.queryCountsByPartnerAndDate(params);

		//小于饲养上限，则为可用
		return counts < Integer.valueOf(riseNo) ? true : false;
	}




	// 根据小区筛选黑名单
	private void blacklist(){
		
	}

	public List queryOrderTotal(){
	
		
		
		return null;
	}
}
