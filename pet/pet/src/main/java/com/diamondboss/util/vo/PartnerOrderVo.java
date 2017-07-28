package com.diamondboss.util.vo;

public class PartnerOrderVo {
	
	/**
	 * id
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
	 * 宠物昵称
	 */
	private String petName;
	
	/**
	 * 宠物性别
	 */
	private String sex;
	
	/**
	 * 宠物年龄
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
	 * 是否携带狗粮
	 */
	private String dogFood;
	
	/**
	 * 状态
	 */
	private String orderStatus;
	
	private String userId;
	
	private String varieties;
	
	private String orderDate;
	
	private String outTradeNo;
	
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getDogFood() {
		return dogFood;
	}
	
	public void setDogFood(String dogFood) {
		this.dogFood = dogFood;
	}

	public PartnerOrderVo(){
		
	}
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVarieties() {
		return varieties;
	}

	public void setVarieties(String varieties) {
		this.varieties = varieties;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
