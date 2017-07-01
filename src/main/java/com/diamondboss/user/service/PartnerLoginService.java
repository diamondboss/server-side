package com.diamondboss.user.service;

import java.util.List;

import com.diamondboss.user.pojo.PartnerLoginPojo;
import com.diamondboss.user.vo.LoginVo;
import com.diamondboss.util.vo.UserOrderServiceVo;

public interface PartnerLoginService {

	/**
	 * 合伙人登录查询
	 * @param vo
	 * @return
	 */
	public PartnerLoginPojo login(LoginVo vo);
	
	/**
	 * 查询用户当日订单
	 * @param map
	 * @return
	 */
	public List<UserOrderServiceVo> queryUserOrderService(String userId, String orderDate);
	
}
