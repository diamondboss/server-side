package com.diamondboss.petsHotel.repository;

import java.util.List;

import com.diamondboss.petsHotel.pojo.OrderHotelPojo;
import com.diamondboss.petsHotel.vo.HotelInfoVo;
import com.diamondboss.petsHotel.vo.HotelLoginVo;
import com.diamondboss.petsHotel.vo.HotelWalletDetail;

/**
 * 酒店服务类
 * 
 * @author John
 * @since 2017-08-16
 *  
 */
public interface HotelServiceMapper {

	/**
	 * 根据登录手机号查询酒店信息
	 * 
	 * @param phone
	 * @return
	 */
	public HotelLoginVo queryHotelListByPhone(String phone);
	
	
	/**
	 * 根据酒店id查询酒店信息
	 * 
	 * @param id
	 * @return
	 */
	public HotelInfoVo queryHotelInfoById(String id);
	
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
	 * 根据酒店id查询酒店订单
	 * 
	 * @param hotelId
	 * @return
	 */
	public List<OrderHotelPojo> queryOrderList(String hotelId);
}
