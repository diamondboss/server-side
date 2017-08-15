package com.diamondboss.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.repository.UserOrderServiceMapper;
import com.diamondboss.user.pojo.PartnerLoginPojo;
import com.diamondboss.user.repository.PartnerLoginMapper;
import com.diamondboss.user.service.PartnerLoginService;
import com.diamondboss.user.vo.LoginVo;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.util.vo.UserOrderServiceVo;

@Service
public class PartnerLoginServiceImpl implements PartnerLoginService{

	private static final Logger log = Logger.getLogger(PartnerLoginServiceImpl.class);
	
	@Autowired
	private PartnerLoginMapper partnerLoginMapper;
	
	@Autowired
	private UserOrderServiceMapper userOrderServiceMapper;
	
	
	@Override
	public PartnerLoginPojo login(LoginVo vo) {
		PartnerLoginPojo partnerLogin = partnerLoginMapper.queryPartnerLoginByPhone(vo.getPhone());
		if (partnerLogin == null) {
			log.info("查询合伙人不存在，继续查找用户");
			return partnerLogin;
		}

		return partnerLogin;
	}

	/**
	 * 查询用户的实时订单
	 * 
	 * @param userId
	 * @param orderDate
	 * @return
	 */
	@Override
	public List<UserOrderServiceVo> queryUserOrderService(String userId, String orderDate) {

		String tableName = TableUtils.getOrderTableName(Long.valueOf(userId), PetConstants.ORDER_USER_TABLE_PREFIX);

		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("orderDate", orderDate);
		map.put("tableName", tableName);

		List<UserOrderServiceVo> userOrder = new ArrayList<>();
		
		UserOrderServiceVo UserOrderServiceVo = userOrderServiceMapper.queryUserOrderService(map);
		 
		if(UserOrderServiceVo == null){
			return userOrder;
		}else{
			String tableNameOfPartner = TableUtils.getOrderTableName(Long.valueOf(UserOrderServiceVo.getPartnerId()), PetConstants.ORDER_PARTNER_TABLE_PREFIX);
			
			Map<String, String> mapOfPartner = new HashMap<>();
			mapOfPartner.put("partnerId", UserOrderServiceVo.getPartnerId());
			mapOfPartner.put("orderDate", orderDate);
			mapOfPartner.put("tableNameOfPartner", tableNameOfPartner);
			
			int orderCount = userOrderServiceMapper.queryOrderCountOfPartner(mapOfPartner);
			UserOrderServiceVo.setOrderCount(String.valueOf(orderCount));

			userOrder.add(UserOrderServiceVo);
		}
	
		
		return userOrder;
	}
	
	/**
	 * 插入合伙人clientId
	 */
	@Override
	public int insertPartnerClientId(String partnerId, String clientId) {
		Map<String, String> param = new HashMap<>();
		param.put("partnerId", partnerId);
		param.put("clientId", clientId);
		
		return partnerLoginMapper.insertPartnerClientId(param);
	}

	/**
	 * 查询合伙人的clientId到partnerClientId表是否已经存在
	 * @param vo
	 * @return
	 */
	public int selectPartnerClientId(String partnerId, String clientId) {
		Map<String, String> param = new HashMap<>();
		param.put("partnerId", partnerId);
		param.put("clientId", clientId);
		
		return partnerLoginMapper.selectPartnerClientId(param);
	}

	@Override
	public String selectPartnerClientId(String partnerId) {
		Map<String, String> param = new HashMap<>();
		param.put("partnerId", partnerId);
		
		return partnerLoginMapper.selectPartnerCID(param);
	}
}
