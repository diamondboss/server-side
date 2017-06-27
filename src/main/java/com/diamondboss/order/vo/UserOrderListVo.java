package com.diamondboss.order.vo;

import java.util.List;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.constants.PetInfoConstants;
import com.diamondboss.order.pojo.UserOrderListPojo;

/**
 * 用户订单展示列表vo
 * 
 * @author John
 * @since 2017-06-27
 *  
 */
public class UserOrderListVo {

	public UserOrderListVo(){
		
	}
	
	public UserOrderListVo(List<UserOrderListPojo> pojo){
		
		for(UserOrderListPojo i:pojo){
			
			if(PetConstants.ORDER_STATUS_FINISH.equals(i.getOrderStatus())){// 已完成订单
				
				i.setPartnerName(PetInfoConstants.ORDER_PARTNER_NAME 
						+ "：" + i.getPartnerName());
				
				finish.add(i);// 加入已完成订单队列
				
			}else if(PetConstants.ORDER_STATUS_RECEIVED.equals(i.getOrderStatus())){// 已接单订单
				
				i.setPartnerName(PetInfoConstants.ORDER_PARTNER_NAME 
						+ "：" + i.getPartnerName());
				
				underway.add(i);// 加入进行中订单队列
				
			}else if(PetConstants.ORDER_STATUS_EXCEPTION.equals(i.getOrderStatus())){// 异常订单
				
				i.setPartnerName(PetInfoConstants.ORDER_CUSTOMER_SERVICE);
				
				underway.add(i);// 加入进行中订单队列
				
			}else if(PetConstants.ORDER_STATUS_PAY_SUCCESS.equals(i.getOrderStatus())){// 派单中订单
				
				i.setPartnerName(PetInfoConstants.ORDER_DISTRIBUTE);
				
				underway.add(i);// 加入进行中订单队列
				
		
			}else if(PetConstants.ORDER_STATUS_PAY_FAILURE.equals(i.getOrderStatus())){// 支付失败订单
				
				i.setPartnerName(PetInfoConstants.ORDER_PAY_FAILURE);
				
				canceled.add(i);// 加入已取消订单队列
				
			}else if(PetConstants.ORDER_STATUS_UNPAID.equals(i.getOrderStatus())){// 未支付订单
				
				i.setPartnerName(PetInfoConstants.ORDER_UNPAID);
				
				canceled.add(i);// 加入已取消订单队列
				
			}else{
				
				canceled.add(i);// 加入已取消订单队列
			}
			
			
		}
	}
	
	/**
	 * 进行中的订单
	 */
	private List<UserOrderListPojo> underway;
	
	/**
	 * 已取消的订单
	 */
	private List<UserOrderListPojo> canceled;
	
	/**
	 * 已完成的订单
	 */
	private List<UserOrderListPojo> finish;
		
}

