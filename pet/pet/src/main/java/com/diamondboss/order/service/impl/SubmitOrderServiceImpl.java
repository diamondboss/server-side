package com.diamondboss.order.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.order.repository.SubmitOrderMapper;
import com.diamondboss.order.service.ISubmitOrderService;

@Service
public class SubmitOrderServiceImpl implements ISubmitOrderService{

	@Autowired
	private SubmitOrderMapper submitOrder;
	
	/**
	 * 根据用户id查询已经预定的订单
	 * @param userId
	 */
	@Override
	public List<String> queryOrderByUser(String petId) {
		
		// 1.根据用户id查询已经预定的订单
		LocalDate today = LocalDate.now();		
		Map<String, String> map = new HashMap<>();
		map.put("today", today.toString());
		map.put("petId", petId);
		List<String> resultList = submitOrder.queryOrderByUser(map);
		
		return resultList;
		
	}

	
	/**
	 * 根据用户id选择日期预约订单
	 */
	@Override
	public List submitOrderByUser() {
		
		// 晚上10点至早5点不可下单
		
		List<Object> list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		
		// 黑名单过滤
		
		// 查询该小区符合条件的社区合伙人
		List<String> parList = submitOrder.queryParByComm(map);
		
		// 随机合伙人
		if(parList == null || parList.size() == 0){
			return null;
		}
		
		Random random = new Random();
		int i = random.nextInt(parList.size());
		String parId = parList.get(i);
		
		// 新增订单
		// 插入订单临时表
		submitOrder.insertParterOrder();
		
		// 再查询该合伙人是否超过订单数量
		submitOrder.queryParterOrder();
		
		// 如果超过删除该订单
		if(false){// false
			
		}
		
		// 将该订单日期加入列表
			
		
		return list;
	}

	// 根据小区筛选黑名单
	private void blacklist(){
		
	}

	public List queryOrderTotal(){
	
		
		
		return null;
	}
}
