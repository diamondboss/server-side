package com.diamondboss.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.GrabOrderPojo;
import com.diamondboss.order.repository.GrabOrderMapper;
import com.diamondboss.order.service.IGrabOrderService;
import com.diamondboss.order.vo.GrabOrderVo;
import com.diamondboss.util.tools.TableUtils;

@Service
public class GrabOrderServiceImpl implements IGrabOrderService{

	@Autowired
	private GrabOrderMapper grabOrder;
	
	@Override
	public int grabOrder(GrabOrderVo vo){
		
		// 查询合伙人抢单表
		String tableId = TableUtils.getOrderTableName(Long.valueOf(vo.getPartnerId()), 
				PetConstants.GRAB_ORDER_INFO_TABLE_PREFIX);
		Map<String, Object> param = new HashMap<>();
		param.put("grabOrderTable", tableId);
		param.put("id", vo.getId());
		GrabOrderPojo pojo = grabOrder.queryGrabOrderUserId(param);
		
		// 检查自己是否能接单
		if(cheakSelfOrderNum(pojo.getPartnerId(), pojo.getOrderDate())){
			return 3; // 接单数量已满,接单失败
		}
		
		pojo.setUserTableId((PetConstants.ORDER_USER_TABLE_PREFIX) + pojo.getUserTableId());
		// 更新用户订单表
		int i = grabOrder.updateOrderUser(pojo);
		if(i==0){// 如果更新失败,则返回
			grabOrder.updateGrabOrderUserId(param);
			return 2;
		}
		
		// 插入合伙人表
		pojo.setPartnerTableId(TableUtils.getOrderTableName(Long.valueOf(pojo.getPartnerId()), 
				PetConstants.ORDER_PARTNER_TABLE_PREFIX));
		grabOrder.insertOrderPartner(pojo);
		
		// 更新用户登录表
		grabOrder.updateUserLogin(pojo.getUserId());
		
		// 推送用户
		return 0;
	}
	
	@Override
	public List<GrabOrderVo> queryOrder(GrabOrderVo vo){
		
		Map<String, Object> param = new HashMap<>();
		param.put("partnerId", vo.getPartnerId());
		param.put("grabOrder", TableUtils.getOrderTableName(Long.valueOf(vo.getPartnerId()), 
				PetConstants.GRAB_ORDER_INFO_TABLE_PREFIX));
		
		return grabOrder.queryGrabOrderByPartnerId(param);
	}
	
	/**
	 * 检查自己是否能接单
	 * 
	 * @param partnerId 合伙人id
	 * @param date 订单日期
	 * @return true 接单数量已满,接单失败;false 运行接单
	 */
	private Boolean cheakSelfOrderNum(String partnerId, String orderDate){
		
		String orderPartner = TableUtils.getOrderTableName(Long.valueOf(partnerId),
				PetConstants.ORDER_USER_TABLE_PREFIX);
		
		Map<String, String> map = new HashMap<>();
		map.put("partnerId", partnerId);
		map.put("orderDate", orderDate);
		map.put("orderPartner", orderPartner);
		
		// 查询合伙人能容纳数量
		int total = grabOrder.querySelfOrdertotal(partnerId);
		
		// 查询合伙人接单数量
		int num = grabOrder.querySelfOrderNum(map);
		
		if(total > num){
			return false;
		}     
		
		return true;
		
	}
	
}
