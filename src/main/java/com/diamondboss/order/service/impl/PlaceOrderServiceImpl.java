package com.diamondboss.order.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.pojo.RaiseNumberPojo;
import com.diamondboss.order.repository.PlaceOrderMapper;
import com.diamondboss.order.service.PlaceOrderService;
import com.diamondboss.order.vo.OrderUserVo;
import com.diamondboss.util.tools.TableUtils;

/**
 * 用户下单
 * 
 * @author John
 * @since 2017-06-24
 *  
 */
@Service
public class PlaceOrderServiceImpl implements PlaceOrderService{

	@Autowired
	public PlaceOrderMapper placeOrderMapper;
	
	/**
	 * 用户下单-指定合伙人
	 */
	@Override
	public boolean appointPartner(OrderUserVo vo){
		
		// 查询该合伙人是否满足条件
		if(checkOrderCountsOfPartner(vo.getPartnerId())){
			
			// 如果不满足
			return false;
		}
		
		// 插入用户订单
		int i = placeOrderMapper.insertUserOrder(vo.voToPojo(vo));
		
		if(i==0){
			
			return false;
			
		}
		
		return true;
		
	}
	
	
	/**
	 * 不指定合伙人
	 * @param vo
	 * @return true-下单成功 false-小区合伙人订单已满
	 */
	@Override
	public boolean randomPartner(OrderUserVo vo){
		
		// 查询小区允许下单总数
		if(cheakCommunityOrderNum(vo.getCommunityId())){
			return false;
		}
		
		// 插入用户订单表
		int i = placeOrderMapper.insertUserOrder(vo.voToPojo(vo));
		
		if(i==0){
			return false;
		}
		return true;
	}
	
	/**
	 * 确认合伙人是否可以接受订单
	 * @param partnerId
	 * @param riseNo
	 * @return true--不可下单;false--可以下单
	 */
	private boolean checkOrderCountsOfPartner(String partnerId){
		
		if(StringUtils.isBlank(partnerId)){
			return true;
		}
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(partnerId), 
				PetConstants.ORDER_PARTNER_TABLE_PREFIX);

		// 查询合伙人的当日订单数量
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", tableName);
		params.put("partnerId", partnerId);
		params.put("currentDate", LocalDate.now());
		int counts = placeOrderMapper.queryCountsByPartnerAndDate(params);// 当前订单数量
		
		int riseNo = placeOrderMapper.queryPartnerCondition(partnerId);// 可接受订单数量
		
		// 小于饲养上限，则为可用
		return counts < riseNo ? false : true;
	}

	/**
	 * 检查小区宠物的总数
	 * @param userId
	 * @return false-允许下单/true-宠物已满不允许下单
	 */
	private Boolean cheakCommunityOrderNum(String communityId){
		
		// 根据小区id查询合伙人表获取小区宠物饲养上限
		List<RaiseNumberPojo> partnerlist = 
				placeOrderMapper.queryTotalByCommunityId(communityId);
		
		if(partnerlist==null || partnerlist.size() == 0){
			return true;
		}
		
		// 根据小区id查询用户表获取小区目前订单数量
		int num = 0;// 小区已接单数
		int total = 0;// 小区接单总数 
		
		for(RaiseNumberPojo i:partnerlist){
			total += Integer.valueOf(i.getorderNum());// 总数
			// TODO
			num += 	placeOrderMapper.querNumByPartnerId(null);
		}
		
		if(num < total){
			return false;
		}
		return true;		
	}
	
	/**
	 * 创建订单信息
	 * @param vo
	 */
	private OrderUserPojo combinationOrderInfo(OrderUserVo vo){
		
		
		
		return null;
	}
	
}