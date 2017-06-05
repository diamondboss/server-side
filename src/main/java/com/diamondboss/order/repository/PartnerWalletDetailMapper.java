package com.diamondboss.order.repository;

import java.util.Map;
import com.diamondboss.util.vo.PartnerWalletDetailVo;

public interface PartnerWalletDetailMapper {

	/**
	 * 查询合伙人钱包明细
	 * @param parmMap
	 * @return
	 */
	PartnerWalletDetailVo queryPartnerAmountDetails(Map<String, Object> parmMap);
}
