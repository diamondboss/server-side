package com.diamondboss.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.repository.DistributeOrderMapper;
import com.diamondboss.order.repository.PlaceOrderMapper;
import com.diamondboss.order.service.DistributeOrderService;
import com.diamondboss.user.pojo.PartnerInfoPojo;
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
		OrderUserPojo pojo = new OrderUserPojo();// TODO 查询
		if(pojo == null){
			return;
		}
		
		if(pojo.getPartnerId() != null || !"".equals(pojo.getPartnerId())){
			appointPartner(pojo);
		}else{
			randomPartner(pojo);
		}
		
		
	}
	
	/**
	 * 指定合伙人派单
	 */
	private void appointPartner(OrderUserPojo pojo){
		
		// 检查合伙人是否满足要求
		if(checkOrderCountsOfPartner(pojo.getPartnerId(), 
				pojo.getOrderDate())){
			
			// 支付宝或微信退款
			
			// APP推送用户
			
			// 短信推送合伙人
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
	private void randomPartner(OrderUserPojo pojo){
		
		// 查询合适的合伙人
		getPartnerList(pojo.getCommunityId(), pojo.getOrderDate());
		
		// APP推送合伙人抢单信息
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

	/**
	 * 查询小区可接单的合伙人
	 * @param commId
	 * @return
	 */
	private List<PartnerInfoPojo> getPartnerList(String CommunityId, String orderDate){
		List<PartnerInfoPojo> partnerList = new ArrayList();
		// 1、根据社区id查询有几个合伙人
		List<PartnerInfoPojo> partners = null;
//				parterInfoMapper.queryPartnerByCommunityId(CommunityId);

		if (!CollectionUtils.isEmpty(partners)){
			for (PartnerInfoPojo pojo : partners){
				// 2、确认合伙人的订单是否已满
				if (!checkOrderCountsOfPartner(pojo.getId(), orderDate)) {
					partnerList.add(pojo);
				}
			}

		}
		return partnerList;
	}
	
}
