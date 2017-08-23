package com.diamondboss.petsHotel.service;

import java.util.List;

import com.diamondboss.petsHotel.vo.HotelInfoVo;
import com.diamondboss.petsHotel.vo.HotelLoginVo;
import com.diamondboss.petsHotel.vo.HotelWalletDetail;
import com.diamondboss.petsHotel.vo.OrderHotelVo;

/**
 * 酒店服务类
 * 
 * @author John
 * @since 2017-08-16
 *  
 */
public interface HotelService {

	/**
	 * 酒店端登录查询
	 * 
	 * @param phone
	 * @return
	 */
	public HotelLoginVo queryHotel(String phone);
	
	/**
	 * 根据酒店id查询酒店信息
	 * 
	 * @param id
	 * @return
	 */
	public HotelInfoVo queryHotelInfo(String hotelId);
	
	/**
	 * 根据酒店id查询酒店钱包信息
	 * 
	 * @param id
	 * @return
	 */
	public String queryHotelWallet(String hotelId);
	
	/**
	 * 根据酒店id查询酒店钱包明细信息
	 * 
	 * @param id
	 * @return
	 */
	public List<HotelWalletDetail> queryHotelWalletDetail(String hotelId);
	
	/**
	 * 酒店订单展示
	 * 
	 * @param hotelId
	 * @return
	 */
	public OrderHotelVo queryOrderList(String hotelId);
	
}
