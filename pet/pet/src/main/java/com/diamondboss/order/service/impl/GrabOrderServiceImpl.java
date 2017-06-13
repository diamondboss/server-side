package com.diamondboss.order.service.impl;

import org.springframework.stereotype.Service;

import com.diamondboss.order.repository.GrabOrderMapper;
import com.diamondboss.order.service.IGrabOrderService;
import com.diamondboss.util.pojo.OrderPartnerPojo;
import com.diamondboss.util.pojo.OrderUserPojo;

@Service
public class GrabOrderServiceImpl implements IGrabOrderService{

	private GrabOrderMapper grabOrder;
	
	@Override
	public void grabOrder(){
		
		String date = "";
		String partnerId = "";// APP 传入
		String userId = "";// 表中获取
		String orderUserId = "";// 表中获取
		String userTableId = ""; // UUID获取
		String partnerTableId = ""; // UUID获取
		
		// 检查自己是否能接单
		if(cheakSelfOrderNum(partnerId, date)){
			return; // 接单数量已满,接单失败
		}
		
		OrderUserPojo userPojo = new OrderUserPojo();
		userPojo.setId(orderUserId);
		userPojo.setPartnerId(partnerId);
		userPojo.setUserId(userId);
		userPojo.setOrderStatus("1");
		userPojo.setOrderUser(userTableId);
		
		// 更新用户订单表
		int i = grabOrder.updateOrderUser(userPojo);
		
		if(i==0){
			return;
		}
		
		OrderPartnerPojo partnerPojo = new OrderPartnerPojo();
		partnerPojo.setId(orderUserId);
		
		
		
		// 插入合伙人表
		grabOrder.insertOrderPartner(partnerPojo);
		
		
		// 更新用户登录表
		grabOrder.updateUserLogin(userId);
		
		// 推送用户
		
	}
	
	/**
	 * 检查自己是否能接单
	 * true - 接单数量已满,接单失败
	 * false - 运行接单
	 * @param partnerId 合伙人id
	 * @param date 订单日期
	 * @return
	 */
	private Boolean cheakSelfOrderNum(String partnerId, String orderDate){
		
		// 查询合伙人能容纳数量
		int total = grabOrder.querySelfOrdertotal(partnerId);
		
		// 查询合伙人接单数量
		int num = grabOrder.querySelfOrderNum(partnerId, orderDate);
		
		if(total > num){
			return false;
		}
		
		return true;
		
	}
	
}
