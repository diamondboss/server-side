package com.diamondboss.order.vo;

import java.math.BigDecimal;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.util.tools.TableUtils;

/**
 * 用户下单
 * 
 * @author John
 * @since 2017-06-24
 *  
 */
public class OrderUserVo {

	public OrderUserVo(){
		
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
	 * 品种
	 */
	private String varieties;

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
	private String remark = " ";
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 合伙人id
	 */
	private String partnerId;
	
	/**
	 * 合伙人名字
	 */
	private String partnerName;

	/**
	 * 订单日期
	 */
	private String orderDate;
	
	/**
	 * 订单状态
	 */
	private String orderStatus = "1";
	
	/**
	 * 金额
	 */
	private BigDecimal amt;
	
	/**
	 * 宠物食物
	 */
	private String dogFood;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 更新时间
	 */
	private String updateTime;

	/**
	 * 小区id
	 */
	private String communityId;
	
	/**
	 * 用户ip
	 */
	private String userIp;
	
	/**
	 * 用户ip
	 * @return
	 */
	public String getUserIp() {
		return userIp;
	}

	/**
	 * 用户ip
	 * @param userIp
	 */
	public void setUserIp(String userIp) {
		this.userIp = userIp;
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
	
	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
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
	
	/**
	 * 小区id
	 * @return
	 */
	public String getCommunityId() {
		return communityId;
	}

	/**
	 * 小区id
	 * @param communityId
	 */
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	/**
	 * 是否携带宠物食物 0-否 1-是
	 * @return
	 */
	public String getDogFood() {
		return dogFood;
	}

	/**
	 * 是否携带宠物食物 0-否 1-是
	 * @param dogFood
	 */
	public void setDogFood(String dogFood) {
		this.dogFood = dogFood;
	}

	public OrderUserPojo voToPojo(OrderUserVo vo){
		
		OrderUserPojo pojo = new  OrderUserPojo();
		
		if(vo == null){
			return pojo;
		}
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(vo.getUserId()), 
				PetConstants.ORDER_USER_TABLE_PREFIX);
		
		pojo.setReceiveTime(vo.getReceiveTime());
		pojo.setReturnTime(vo.getReturnTime());
		pojo.setPetName(vo.getPetName());
		pojo.setSex(vo.getSex());
		pojo.setAge(vo.getAge());
		pojo.setVarieties(vo.getVarieties());
		pojo.setPhone(vo.getPhone());
		pojo.setUserName(vo.getUserName());
		pojo.setRemark(vo.getRemark());
		pojo.setUserId(vo.getUserId());
		pojo.setCommunityId(vo.getCommunityId());
		pojo.setDogFood(vo.getDogFood());
		
		
		if(vo.getPartnerId() != null){
			pojo.setPartnerId(vo.getPartnerId());
		}
		pojo.setOrderDate(vo.getOrderDate());
		pojo.setOrderStatus("0");
		pojo.setAmt(new BigDecimal("0.01"));
		pojo.setOrderUser(tableName);
		
		if(vo.getPartnerName() != null){
			pojo.setPartnerName(vo.getPartnerName());
		}
		return pojo;
	}
}
