package com.diamondboss.petsHotel.vo;

/**
 * 酒店端登录返回信息
 * 
 * @author John
 * @since 2017-08-20
 *  
 */
public class HotelLoginVo {

	/**
	 * 酒店id
	 */
	private String hotelId;
	
	/**
	 * 酒店电话
	 */
	private String phone;
	
	/**
	 * 酒店名称
	 */
	private String hotelName;

	/**
	 * 酒店id
	 * @return
	 */
	public String getHotelId() {
		return hotelId;
	}

	/**
	 * 酒店id
	 * @param hotelId
	 */
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	/**
	 * 酒店电话
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 酒店电话
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 酒店名称
	 * @return
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * 酒店名称
	 * @param hotelName
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	
}
