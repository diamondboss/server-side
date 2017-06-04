package com.diamondboss.order.service;

import java.util.List;

import com.diamondboss.util.vo.PartnerDetailVo;

/**
 * 合伙人提现接口
 * @author xzf
 *
 */
public interface IPartnerWithDraw {

	/**
	 * 查询合伙人的提现明细
	 * @param partnerId
	 * @return
	 */
	public List<PartnerDetailVo> queryPartnerDetail(String partnerId);
}
