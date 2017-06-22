package com.diamondboss.user.service.impl;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.repository.UserOrderServiceMapper;
import com.diamondboss.user.pojo.PartnerLoginPojo;
import com.diamondboss.user.repository.PartnerLoginMapper;
import com.diamondboss.user.service.PartnerLoginService;
import com.diamondboss.user.vo.LoginVo;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.util.vo.UserOrderServiceVo;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public UserOrderServiceVo queryUserOrderService(String userId, String orderDate) {

		String tableName = TableUtils.getOrderTableName(Long.valueOf(userId), PetConstants.ORDER_USER_TABLE_PREFIX);

		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("orderDate", orderDate);
		map.put("tableName", tableName);

		UserOrderServiceVo userOrder = userOrderServiceMapper.queryUserOrderService(map);

		return userOrder;

	}
}
