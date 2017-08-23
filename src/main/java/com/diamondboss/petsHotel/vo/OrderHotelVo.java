package com.diamondboss.petsHotel.vo;

import java.util.ArrayList;
import java.util.List;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.petsHotel.pojo.OrderHotelPojo;

public class OrderHotelVo {

	/**
	 * 进行中的订单
	 */
	private List<OrderHotelPojo> underway = new ArrayList<>();
	
	/**
	 * 已取消的订单
	 */
	private List<OrderHotelPojo> canceled = new ArrayList<>();
	
	/**
	 * 已完成的订单
	 */
	private List<OrderHotelPojo> finish = new ArrayList<>();
	
	public OrderHotelVo(List<OrderHotelPojo> list){
			
		if(list != null && list.size() != 0){
			
			for(OrderHotelPojo i :list){

				if(PetConstants.ORDER_STATUS_REFUND.equals(i.getOrderStatus())){
					canceled.add(i);
				}else if(PetConstants.ORDER_STATUS_PAY_SUCCESS.equals(i.getOrderStatus())){
					underway.add(i);
				}else if(PetConstants.ORDER_STATUS_RECEIVED.equals(i.getOrderStatus())){
					finish.add(i);
				}
				
			}
		}
		
	}
	
	
	
}
