package com.diamondboss.order.service.impl;

import java.time.LocalDate;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.UserOrderListPojo;
import com.diamondboss.order.repository.GrabOrderMapper;
import com.diamondboss.order.repository.UserOrderServiceMapper;
import com.diamondboss.order.service.IOrderService;
import com.diamondboss.order.vo.UserOrderListVo;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.util.vo.PartnerOrderServiceVo;
import com.diamondboss.util.vo.UserOrderServiceVo;
import com.diamondboss.util.vo.UserOrdersServiceVo;

/**
 * 小区服务实现类
 * @author xzf
 *
 */
@Service(value = "orderService")
public class OrderServiceImpl implements IOrderService {
	 @Autowired
	 private UserOrderServiceMapper userOrderServiceMapper;
	 
	 @Autowired
	 private GrabOrderMapper grabOrder;
	
	/**
	 * 查询用户的实时订单
	 * @param userId
	 * @param orderDate
	 * @return
	 */
	public Map<String, Object> queryUserOrderService(String userId, String orderDate){
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(userId),
				PetConstants.ORDER_USER_TABLE_PREFIX);
		
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("orderDate", orderDate);
		map.put("tableName", tableName);
		
		List<UserOrderServiceVo> userOrder = new ArrayList<>();
		userOrder.add(userOrderServiceMapper.queryUserOrderService(map));
		
		
		
		Map<String, Object> parmMap = new HashMap<>();
		parmMap.put("userId", userId);
		//parmMap.put("partnerId", userOrder.getPartnerId());
		parmMap.put("orderDate", orderDate);
		parmMap.put("tableName", tableName);
		List<UserOrdersServiceVo> userOrders = userOrderServiceMapper.queryUserOrders(parmMap);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("userOrder", userOrder);
		responseMap.put("userOrders", userOrders);
		
		return responseMap;
	}

	@Override
	public void synchronizedPaymentResult(String orderId, Map<String, Objects> params) {
		//TODO 如何判定订单属于哪张表
		//更新支付结果到对应的表的记录中

		//如果为支付成功，还要继续出发推送任务
	}

	/**
	 * 查询合伙人的实时订单
	 * @param userId
	 * @param orderDate
	 * @return
	 */
	@Override
	public PartnerOrderServiceVo queryPartnerOrderService(String partnerId) {
		String tableName = TableUtils.getOrderTableName(Long.valueOf(partnerId),
				PetConstants.ORDER_PARTNER_TABLE_PREFIX);
		
		Map<String, String> map = new HashMap<>();
		map.put("partnerId", partnerId);
		map.put("tableNameOfPartner", tableName);
		
		PartnerOrderServiceVo partnerOrder= userOrderServiceMapper.queryPartnerOrderService(map);
		partnerOrder.setOrderCount(String.valueOf(userOrderServiceMapper.queryOrderCountOfPartner2(map)));

		return partnerOrder;
	}

	@Override
	public Map<String, String> NumByPartnerOrder(String partnerId) {

		String orderDate = LocalDate.now().toString();
		
		String orderPartner = TableUtils.getOrderTableName(Long.valueOf(partnerId),
				PetConstants.ORDER_USER_TABLE_PREFIX);
		
		Map<String, String> map = new HashMap<>();
		map.put("partnerId", partnerId);
		map.put("orderDate", orderDate);
		map.put("orderPartner", orderPartner);
		map.put("orderStatus", "1");
		map.put("effective", "1");
		
		// 查询合伙人能容纳数量
		int total = grabOrder.querySelfOrdertotal(partnerId);		
		// 查询合伙人接单数量
		int num = grabOrder.querySelfOrderNum(map);
		
		Map<String, String> resultmap = new HashMap<String, String>();
		resultmap.put("total", String.valueOf(total));
		resultmap.put("num", String.valueOf(num));
		
		return resultmap;
	}

	/**
	 * 查询用户订单列表
	 * 
	 * @param userId 用户id
	 * @return 用户订单列表
	 */
	@Override
	public UserOrderListVo queryUserOrderList(String userId){
		
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		param.put("orderUser", TableUtils.getOrderTableName(
				Long.valueOf(userId), PetConstants.ORDER_USER_TABLE_PREFIX));
		
		List<UserOrderListPojo> list = userOrderServiceMapper.queryUserOrderList(param);
		UserOrderListVo vo = new UserOrderListVo(list,1);
		return vo;
	}

	/**
	 * 查询合伙人订单列表
	 * 
	 * @param userId
	 * @return 用户订单列表
	 */
	@Override
	public UserOrderListVo queryPartnerOrderList(String partnerId){
		
		Map<String, Object> param = new HashMap<>();
		param.put("partnerId", partnerId);
		param.put("orderUser", TableUtils.getOrderTableName(
				Long.valueOf(partnerId), PetConstants.ORDER_PARTNER_TABLE_PREFIX));
		
		List<UserOrderListPojo> list = userOrderServiceMapper.queryPartnerOrderList(param);
		UserOrderListVo vo = new UserOrderListVo(list,0);
		return vo;
	}
	
	
}
