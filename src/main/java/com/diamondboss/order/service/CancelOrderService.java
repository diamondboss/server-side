package com.diamondboss.order.service;

import com.diamondboss.order.vo.CancelOrderVo;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 用户取消预约
 * 
 * @author John
 * @since 2017-08-10
 *  
 */
public interface CancelOrderService {

	/**
	 * 用户取消预约
	 * 
	 * @param vo
	 * @return
	 */
	public APPResponseBody cancelOrder(CancelOrderVo vo);
	
}
