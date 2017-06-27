package com.diamondboss.order.pojo;

import java.math.BigDecimal;

/**
 * 用户下单
 * 
 * @author John
 * @since 2017-06-24
 *  
 */
public class OrderUserPojo {

	public OrderUserPojo(){
		
	}
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 接收时间
	 */
	private String receiveTime;

	/**
	 * 送还时间
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
	 * 用户昵称
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
	 * 合伙人id
	 */
	private String partnerId;
	
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
	private BigDecimal amt;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 更新时间
	 */
	private String updateTime;
	
	/**
	 * 表名
	 */
	private String orderUser;

	/**
	 * 主键
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 接收时间
	 * @return
	 */
	public String getReceiveTime() {
		return receiveTime;
	}

	/**
	 * 接收时间
	 * @param receiveTime
	 */
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	/**
	 * 送还时间
	 * @return
	 */
	public String getReturnTime() {
		return returnTime;
	}

	/**
	 * 送还时间
	 * @param returnTime
	 */
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	/**
	 * 宠物昵称
	 * @return
	 */
	public String getPetName() {
		return petName;
	}

	/**
	 * 宠物昵称
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
	 * 宠物年龄
	 * @return
	 */
	public String getAge() {
		return age;
	}

	/**
	 * 宠物年龄
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
	 * 用户昵称
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 用户昵称
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
	 * 创建时间
	 * @return
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * @return
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}
	
}
