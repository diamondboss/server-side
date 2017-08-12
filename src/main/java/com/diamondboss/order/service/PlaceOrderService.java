package com.diamondboss.order.service;

import java.util.Map;

import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.vo.AlipayOrderSubmitVo;

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
	public boolean appointPartner(OrderUserPojo pojo);
	
	/**
	 * 不指定合伙人
	 * @param vo
	 * @return
	 */
	public boolean randomPartner(OrderUserPojo pojo);

	/**
	 * 支付宝支付-签名生成订单信息
	 * @param vo
	 */
	public AlipayOrderSubmitVo combinationOrderInfo(OrderUserPojo pojo);
	
	/**
	 * 微信支付-签名生成订单信息
	 * @param vo
	 */
	public Map<String, Object> combinationOrderInfoWXPay(OrderUserPojo pojo);
	
	/**
	 * 确认合伙人是否可以接受订单
	 * @param partnerId
	 * @param orderDate
	 * @return true--不可下单;false--可以下单
	 */
	public boolean checkOrderCountsOfPartner(String partnerId, String orderDate);
}
