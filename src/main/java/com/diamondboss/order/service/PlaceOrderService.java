package com.diamondboss.order.service;

import com.diamondboss.order.vo.AlipayOrderSubmitVo;
import com.diamondboss.order.vo.OrderUserVo;

/**
 * 用户下单
 * 
 * @author John
 * @since 2017-06-24
 *  
 */
public interface PlaceOrderService {

	/**
	 * 指定合伙人
	 * @param vo
	 * @return
	 */
	public boolean appointPartner(OrderUserVo vo);
	
	/**
	 * 不指定合伙人
	 * @param vo
	 * @return
	 */
	public boolean randomPartner(OrderUserVo vo);

	/**
	 * 签名生成订单信息
	 * @param vo
	 */
	public AlipayOrderSubmitVo combinationOrderInfo(OrderUserVo vo);
	
}