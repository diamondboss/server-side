package com.diamondboss.order.repository;

import java.util.Map;

import com.diamondboss.util.pojo.PartnerWalletPojo;

public interface PartnerWalletMapper {

	/**
	 * 查询合伙人余额
	 * @param map
	 * @return
	 */
	PartnerWalletPojo queryPartnerWalletAmount(Map<String, Object> map);
}
