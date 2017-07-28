package com.diamondboss.order.vo;

/**
 * 数据库水平拆分表信息pojo
 * 
 * @author John
 * @since 2017-07-03
 *  
 */
public class GrabOrderVo {

	public GrabOrderVo(){
		
	}
	
	/**
	 * 主鍵id
	 */
	private String id;
	
	/**
	 * 送宠时间
	 */
	private String receiveTime;
	
	/**
	 * 接宠时间
	 */
	private String returnTime;
	
	/**
	 * 宠物名字
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
	 * 电话
	 */
	private String userPhone;

	/**
	 * 用户姓名
	 */
	private String userName;
	
	/**
	 * 订单备注
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
	 * 宠物品种
	 */
	private String varieties;

	/**
	 * 用户表对应的主键
	 */
	private String userKeyId;
	
	private String amt;
	
	/**
	 * 是否携带狗粮
	 */
	private String dogFood;
	
	public String getDogFood() {
		return dogFood;
	}

	public void setDogFood(String dogFood) {
		this.dogFood = dogFood;
	}

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
	 * 送宠时间
	 * @return
	 */
	public String getReceiveTime() {
		return receiveTime;
	}

	/**
	 * 送宠时间
	 * @param receiveTime
	 */
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	/**
	 * 接宠时间
	 * @return
	 */
	public String getReturnTime() {
		return returnTime;
	}

	/**
	 * 接宠时间
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
	 * 电话
	 * @return
	 */
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
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
		this.userName = "托管用户:" + userName;
	}

	/**
	 * 订单备注
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 订单备注
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
	 * 宠物品种
	 * @return
	 */
	public String getVarieties() {
		return varieties;
	}

	/**
	 * 宠物品种
	 * @param varieties
	 */
	public void setVarieties(String varieties) {
		this.varieties = varieties;
	}

	public String getUserKeyId() {
		return userKeyId;
	}

	public void setUserKeyId(String userKeyId) {
		this.userKeyId = userKeyId;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}
	
}
