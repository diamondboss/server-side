package com.diamondboss.user.repository;

import com.diamondboss.user.pojo.PartnerLoginPojo;

public interface PartnerLoginMapper {

	/**
	 * 登录查询
	 */
	public PartnerLoginPojo queryPartnerLoginByPhone(String phoneNumber);
	
	
}
