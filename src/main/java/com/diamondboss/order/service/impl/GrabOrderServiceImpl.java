package com.diamondboss.order.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.GrabOrderPojo;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.repository.GrabOrderMapper;
import com.diamondboss.order.service.IGrabOrderService;
import com.diamondboss.order.vo.GrabOrderVo;
import com.diamondboss.user.service.UserLoginService;
import com.diamondboss.util.push.getui.PushToSingle;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.wallet.service.PartnerRebateService;

@Service
public class GrabOrderServiceImpl implements IGrabOrderService{

	private static final Logger log = Logger.getLogger(GrabOrderServiceImpl.class);
	
	@Autowired
	private GrabOrderMapper grabOrder;
	
	@Autowired
	private PartnerRebateService partnerRebateService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	
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
			log.info("合伙人:" + pojo.getPartnerId() + "接单数已满");
			return 3; // 接单数量已满,接单失败
		}
		
		pojo.setUserTableId((PetConstants.ORDER_USER_TABLE_PREFIX) + pojo.getUserTableId());
		// 更新用户订单表
		int i = grabOrder.updateOrderUser(pojo);
		if(i==0){// 如果更新失败,则返回
			grabOrder.updateGrabOrderUserId(param);
			log.info("合伙人:" + pojo.getPartnerId() + "更新订单失败");
			log.info("订单表:" + tableId);
			log.info("订单主键:" + vo.getId());
			return 2;
		}
		grabOrder.updateGrabOrderUserId(param);
		// 插入合伙人表
		pojo.setPartnerTableId(TableUtils.getOrderTableName(Long.valueOf(pojo.getPartnerId()), 
				PetConstants.ORDER_PARTNER_TABLE_PREFIX));
		grabOrder.insertOrderPartner(pojo);
		
		// 更新用户登录表
		grabOrder.updateUserLogin(pojo.getUserId());
		
		// APP推送用户
		//用户的clientId
		String userCID = userLoginService.selectUserClientId(pojo.getUserId());
		PushToSingle.pushSMSToClient(userCID, "订单派送成功", "您的订单已派发成功，请及时查看。如有任何疑问，请及时联系我们帮您处理！", "3");
		
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
				PetConstants.ORDER_PARTNER_TABLE_PREFIX);
		
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
