package com.diamondboss.util.pojo;

import java.math.BigDecimal;

public class OrderUserPojo {

	/**
	 * 主键id
	 */
	public int id;
	
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
	public int userId;
	
	/**
	 * 合伙人id
	 */
	public int partnerId;
	
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
	public int getId() {
		return id;
	}

	/**
	 * 主键id
	 * @param id
	 */
	public void setId(int id) {
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}
}
