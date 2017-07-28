package com.diamondboss.order.pojo;

/**
 * 用户订单展示列表pojo
 * 
 * @author John
 * @since 2017-06-27
 *  
 */
public class UserOrderListPojo{

	/**
	 * 订单编号
	 */
	private String id;
	
	/**
	 * 送宠时间
	 */
	private String receiveTime;
	
	/**
	 * 取宠时间
	 */
	private String returnTime;
	
	/**
	 * 合伙人Id
	 */
	private String userId;
	

	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 用户电话
	 */
	private String userPhone;

	/**
	 * 订单金额
	 */
	private String amt;
	
	/**
	 * 合伙人Id
	 */
	private String partnerId;

	/**
	 * 合伙人姓名
	 */
	private String partnerName;
	
	/**
	 * 合伙人姓名(订单详情)
	 */
	private String partnerNameOfOrder;

	/**
	 * 合伙人电话
	 */
	private String partnerPhone;
	
	/**
	 * 订单号
	 */
	private String outTradeNo;

	/**
	 * 订单状态
	 */
	private String orderStatus;
	
	/**
	 * 宠物性别
	 */
	private String sex;
	
	/**
	 * 宠物年龄
	 */
	private String age;
	
	/**
	 * 宠物品种
	 */
	private String varieties;
	
	/**
	 * 订单备注
	 */
	private String remark;
	
	/**
	 * 订单日期
	 */
	private String orderDate;
	
	/**
	 * 订单创建时间
	 */
	private String createTime;

	/**
	 * 宠物名称
	 */
	private String petName;
	
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
	 * 订单编号
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 订单编号
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 订单金额
	 * @return
	 */
	public String getAmt() {
		return amt;
	}

	/**
	 * 订单金额
	 * @param amt
	 */
	public void setAmt(String amt) {
		this.amt = amt;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * 合伙人姓名
	 * @return
	 */
	public String getPartnerName() {
		return partnerName;
	}

	/**
	 * 合伙人姓名
	 * @param partnerName
	 */
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	
	public String getPartnerNameOfOrder() {
		return partnerNameOfOrder;
	}

	public void setPartnerNameOfOrder(String partnerNameOfOrder) {
		if(partnerNameOfOrder == null){
			this.partnerNameOfOrder = partnerNameOfOrder;
		}else{
			this.partnerNameOfOrder = partnerNameOfOrder;
		}
	}
	
	public String getPartnerPhone() {
		return partnerPhone;
	}

	public void setPartnerPhone(String partnerPhone) {
		if(partnerPhone == null){
			this.partnerPhone = "";
		}else{
			this.partnerPhone = partnerPhone;
		}
	}
	
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
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
	 * 取宠时间
	 * @return
	 */
	public String getReturnTime() {
		return returnTime;
	}

	/**
	 * 取宠时间
	 * @param returnTime
	 */
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
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
	 * 订单创建时间
	 * @return
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 订单创建时间
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	
	
}
