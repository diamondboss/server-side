package com.diamondboss.order.service;

import java.util.List;

import com.diamondboss.util.pojo.ParterWithDrawPojo;

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
	public List<ParterWithDrawPojo> queryParterDetail(String parterId);	
}
