package com.diamondboss.order.repository;

import java.util.Map;

import com.diamondboss.util.vo.PartnerWalletVo;

public interface PartnerWalletMapper {

	/**
	 * 查询合伙人余额
	 * @param map
	 * @return
	 */
	PartnerWalletVo queryPartnerWalletAmount(Map<String, Object> map);
}
