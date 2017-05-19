package com.diamondboss.order.service;

import java.util.List;

import com.diamondboss.util.vo.ParterDetailVo;

/**
 * 合伙人提现接口
 * @author xzf
 *
 */
public interface IParterWithDraw {

	/**
	 * 查询合伙人的提现明细
	 * @param communityId
	 * @return
	 */
	public List<ParterDetailVo> queryParterDetail(String parterId);	
}
