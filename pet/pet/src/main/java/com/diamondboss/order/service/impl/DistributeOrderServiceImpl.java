package com.diamondboss.order.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.repository.DistributeOrderMapper;
import com.diamondboss.order.repository.PlaceOrderMapper;
import com.diamondboss.order.service.DistributeOrderService;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.tools.TableUtils;

/**
 * 订单分配
 * 
 * @author John
 * @since 2017-06-26
 *  
 */
@Service("distributeOrder")
public class DistributeOrderServiceImpl implements DistributeOrderService{

	@Autowired
	private PlaceOrderMapper placeOrderMapper;
	
	@Autowired
	private DistributeOrderMapper distributeOrderMapper;
	
	@Autowired
	private ISendMsgService sendMsgService;
	
	/**
	 * 订单分配
	 */
	@Override
	public void DistributeOrder(){
		
		// 查询用户订单如果有合伙人id则指定合伙人派单,否则不指定合伙人派单
		
		
		
	}
	
	/**
	 * 指定合伙人派单
	 */
	private void appointPartner(){
		
		// 检查合伙人是否满足要求
		if(checkOrderCountsOfPartner("","")){
			
			// 支付宝或微信退款
			
			// APP推送用户
			
			// 短信推送用户
			sendMsgService.sendNotifyMsg("");
			
		}else{
			
			// 满足-插入合伙人订单表;更新用户订单
			distributeOrderMapper.insertOrderPartner(null);
			
			distributeOrderMapper.updateOrderUser(null);
			
			// APP推送用户/合伙人
			
			
			// 短信推送合伙人
			sendMsgService.sendNotifyMsg("");
		}
		
	}
	
	/**
	 * 不指定合伙人派单
	 */
	private void randomPartner(){
		
		// 查询合适的合伙人
		
		// APP推送抢单信息
	}

	/**
	 * 确认合伙人是否可以接受订单
	 * @param partnerId
	 * @param riseNo
	 * @return true--不可下单;false--可以下单
	 */
	private boolean checkOrderCountsOfPartner(String partnerId, String orderDate){
		
		if(StringUtils.isBlank(partnerId)){
			return true;
		}
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(partnerId), 
				PetConstants.ORDER_PARTNER_TABLE_PREFIX);

		// 查询合伙人的当日订单数量
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", tableName);
		params.put("partnerId", partnerId);
		params.put("orderDate", orderDate);
		int counts = placeOrderMapper.queryCountsByPartnerAndDate(params);// 当前订单数量
		
		int riseNo = placeOrderMapper.queryPartnerCondition(partnerId);// 可接受订单数量
		
		// 小于饲养上限，则为可用
		return counts < riseNo ? false : true;
	}

	
}
