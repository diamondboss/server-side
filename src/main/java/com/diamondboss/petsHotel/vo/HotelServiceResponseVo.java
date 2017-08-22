package com.diamondboss.petsHotel.vo;

/**
 * 酒店服务类目列表返回Vo
 * @author xzf
 *
 */
public class HotelServiceResponseVo {
	/**
	 * 酒店ID
	 */
	private String hotelId;
	
	/**
	 * 服务id
	 */
	private String serviceId;
	
	/**
	 * 服务备注
	 */
	private String serviceRemark;
	
	/**
	 * 服务名字
	 */
	private String serviceName;
	
	/**
	 * 服务价格
	 */
	private String servicePrice;
	
	/**
	 * 备注1
	 */
	private String remark1;
	
	/**
	 * 备注2
	 */
	private String remark2;
	
	/**
	 * 备注3
	 */
	private String remark3;
	
	/**
	 * 状态
	 */
	private String status;

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceRemark() {
		return serviceRemark;
	}

	public void setServiceRemark(String serviceRemark) {
		this.serviceRemark = serviceRemark;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
