package com.diamondboss.petsHotel.pojo;

/**
 * 酒店订单pojo
 * 
 * @author John
 * @since 2017-08-22
 *  
 */
public class OrderHotelPojo {

	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 宠物接收时间
	 */
	private String receiveTime;
	
	/**
	 * 宠物送还时间
	 */
	private String returnTime;
	
	/**
	 * 宠物名称
	 */
	private String petName;
	
	/**
	 * 宠物性别
	 */
	private String sex;
	
	/**
	 * 年龄
	 */
	private String age;
	
	/**
	 * 联系方式
	 */
	private String phone;
	
	/**
	 * 用户姓名
	 */
	private String userName;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 酒店id
	 */
	private String hotelId;
	
	/**
	 * 酒店名称
	 */
	private String hotelName;
	
	/**
	 * 服务类别id
	 */
	private String serviceId;
	
	/**
	 * 订单日期
	 */
	private String orderDate;
	
	/**
	 * 订单状态
	 */
	private String orderStatus;
	
	/**
	 * 金额
	 */
	private String amt;
	
	/**
	 * 系统内订单id
	 */
	private String outTradeNo;
	
	/**
	 * 品种
	 */
	private String varieties;
	
	/**
	 * 第三方交易id
	 */
	private String tradeNo;
	
	/**
	 * 付款方式： 0-支付宝,1-微信,3-其他
	 */
	private String payType;

	/**
	 * 主键id
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 宠物接收时间
	 * @return
	 */
	public String getReceiveTime() {
		return receiveTime;
	}

	/**
	 * 宠物接收时间
	 * @param receiveTime
	 */
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	/**
	 * 宠物送还时间
	 * @return
	 */
	public String getReturnTime() {
		return returnTime;
	}

	/**
	 * 宠物送还时间
	 * @param returnTime
	 */
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	/**
	 * 宠物名称
	 * @return
	 */
	public String getPetName() {
		return petName;
	}

	/**
	 * 宠物名称
	 * @param petName
	 */
	public void setPetName(String petName) {
		this.petName = petName;
	}

	/**
	 * 宠物性别
	 * @return
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 宠物性别
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 年龄
	 * @return
	 */
	public String getAge() {
		return age;
	}

	/**
	 * 年龄
	 * @param age
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * 联系方式
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 联系方式
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 用户姓名
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 用户姓名
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 备注
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 用户id
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	/**
	 * 服务类别id
	 * @return
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * 服务类别id
	 * @param serviceId
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * 订单日期
	 * @return
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * 订单日期
	 * @param orderDate
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 订单状态
	 * @return
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 订单状态
	 * @param orderStatus
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 金额
	 * @return
	 */
	public String getAmt() {
		return amt;
	}

	/**
	 * 金额
	 * @param amt
	 */
	public void setAmt(String amt) {
		this.amt = amt;
	}

	/**
	 * 系统内订单id
	 * @return
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}

	/**
	 * 系统内订单id
	 * @param outTradeNo
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	/**
	 * 品种
	 * @return
	 */
	public String getVarieties() {
		return varieties;
	}

	/**
	 * 品种
	 * @param varieties
	 */
	public void setVarieties(String varieties) {
		this.varieties = varieties;
	}

	/**
	 * 第三方交易id
	 * @return
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * 第三方交易id
	 * @param tradeNo
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/**
	 * 付款方式： 0-支付宝,1-微信,3-其他
	 * @return
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * 付款方式： 0-支付宝,1-微信,3-其他
	 * @param payType
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
}
