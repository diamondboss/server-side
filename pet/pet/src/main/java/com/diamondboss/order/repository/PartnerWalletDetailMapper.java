package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;
import com.diamondboss.util.vo.PartnerWalletDetailVo;

public interface PartnerWalletDetailMapper {

	/**
	 * 查询合伙人钱包明细
	 * @param parmMap
	 * @return
	 */
	List<PartnerWalletDetailVo> queryPartnerAmountDetails(Map<String, Object> parmMap);
}
