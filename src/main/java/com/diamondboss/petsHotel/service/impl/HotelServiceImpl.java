package com.diamondboss.petsHotel.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.petsHotel.pojo.OrderHotelPojo;
import com.diamondboss.petsHotel.repository.HotelServiceMapper;
import com.diamondboss.petsHotel.service.HotelService;
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
@Service
public class HotelServiceImpl implements HotelService{

	private static final Logger log =  
			Logger.getLogger(HotelServiceImpl.class);
	
	@Autowired
	private HotelServiceMapper hotelServiceMapper;
	
	/**
	 * 酒店端登录查询
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public HotelLoginVo queryHotel(String phone) {
		
		return hotelServiceMapper.queryHotelListByPhone(phone);
		
	}
	
	/**
	 * 根据酒店id查询酒店信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public HotelInfoVo queryHotelInfo(String hotelId){
		
		return hotelServiceMapper.queryHotelInfoById(hotelId);
		
	}
	
	/**
	 * 根据酒店id查询酒店钱包信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public String queryHotelWallet(String hotelId){
		
		String amt = hotelServiceMapper.queryHotelWallet(hotelId);
		
		if(StringUtils.isBlank(amt)){
				return "0.00";
		}
		
		return amt;
	}
	
	/**
	 * 根据酒店id查询酒店钱包明细信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<HotelWalletDetail> queryHotelWalletDetail(String hotelId){
		
		return hotelServiceMapper.queryHotelWalletDetail(hotelId);
		
	}

	
	/**
	 * 酒店订单展示
	 * 
	 * @param hotelId
	 * @return
	 */
	@Override
	public OrderHotelVo queryOrderList(String hotelId){
		
	
		List<OrderHotelPojo> list = hotelServiceMapper.queryOrderList(hotelId);
		
		OrderHotelVo vo = new OrderHotelVo(list);
		
		return vo;
	}
	
}
