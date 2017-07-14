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
	
	/**
	 * 插入合伙人的clientId到partnerClientId表
	 * @param vo
	 * @return
	 */
	public int insertPartnerClientId(String partner, String clientId);
	
	/**
	 * 查询合伙人的clientId到partnerClientId表是否已经存在
	 * @param vo
	 * @return
	 */
	public int selectPartnerClientId(String partnerId, String clientId);
	
	/**
	 * 根据合伙人Id，查询到他的CID
	 * @param vo
	 * @return
	 */
	public String selectPartnerClientId(String partnerId);
	
}
