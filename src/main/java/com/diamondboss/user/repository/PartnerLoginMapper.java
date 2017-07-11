package com.diamondboss.user.repository;

import java.util.Map;

import com.diamondboss.user.pojo.PartnerLoginPojo;

public interface PartnerLoginMapper {

	/**
	 * 登录查询
	 */
	public PartnerLoginPojo queryPartnerLoginByPhone(String phoneNumber);
	
	
	/**
	 * 插入合伙人的clientId到partnerClientId表
	 * @param map
	 * @return
	 */
	public int insertPartnerClientId(Map<String, String> map);
	
	/**
	 * 查询合伙人的clientId到partnerClientId表是否已经存在
	 * @param map
	 * @return
	 */
	public int selectPartnerClientId(Map<String, String> map);
	
}
