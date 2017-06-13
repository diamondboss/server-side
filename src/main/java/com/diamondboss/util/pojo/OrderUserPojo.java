package com.diamondboss.util.pojo;

import java.math.BigDecimal;

public class OrderUserPojo {

	/**
	 * 主键id
	 */
	public String id;
	
	/**
	 * 宠物接收时间
	 */
	public String receiveTime;
	
	/**
	 * 宠物送还时间
	 */
	public String returnTime;
	
	/**
	 * 宠物名称
	 */
	public String petName;
	
	/**
	 * 宠物性别
	 */
	public int sex;
	
	/**
	 * 宠物年龄
	 */
	public int age;
	
	/**
	 * 联系方式
	 */
	public String phone;
	
	/**
	 * 用户姓名
	 */
	public String userName;
	
	/**
	 * 备注
	 */
	public String remark;
	
	/**
	 * 用户id
	 */
	public String userId;
	
	/**
	 * 合伙人id
	 */
	public String partnerId;
	
	/**
	 * 订单日期
	 */
	public String orderDate;
	
	/**
	 * 订单状态
	 */
	public String orderStatus;
	
	/**
	 * 金额
	 */
	public BigDecimal amt;
	
	/**
	 * 对应用户表名(非字段)
	 */
	public String orderUser;

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
	public int getSex() {
		return sex;
	}

	/**
	 * 宠物性别
	 * @param sex
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * 宠物年龄
	 * @return
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 宠物年龄
	 * @param age
	 */
	public void setAge(int age) {
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
	 * 合伙人id
	 * @return
	 */
	public String getPartnerId() {
		return partnerId;
	}

	/**
	 * 合伙人id
	 * @param partnerId
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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
	public BigDecimal getAmt() {
		return amt;
	}

	/**
	 * 金额
	 * @param amt
	 */
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	/**
	 * 对应用户表名(非字段)
	 * @return
	 */
	public String getOrderUser() {
		return orderUser;
	}

	/**
	 * 对应用户表名(非字段)
	 * @param orderUser
	 */
	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}
}
