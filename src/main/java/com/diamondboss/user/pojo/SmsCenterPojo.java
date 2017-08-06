package com.diamondboss.user.pojo;

/**
 * 消息中心POJO
 * @author xzf
 *
 */
public class SmsCenterPojo {
	
	/**
	 * 用户Id
	 */
	private String userId;
	
	/**
	 * 合伙人Id
	 */
	private String partnerId;

	/**
	 * 合伙人名字
	 */
	private String partnerName;
	
	/**
	 * 消息来源。 0：系统通知    1：订单信息
	 */
	private String smsSource;
	
	/**
	 * 消息类型Id
	 */
	private String smsTypeId;
	
	/**
	 * 消息状态
	 */
	private String smsStatus;
	
	/**
	 * 消息标题
	 */
	private String smsTitle;
	
	/**
	 * 消息内容 
	 */
	private String smsContext;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 更新时间
	 */
	private String updateTime;

	/**
	 * 表名字
	 */
	private String tableName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getSmsSource() {
		return smsSource;
	}

	public void setSmsSource(String smsSource) {
		this.smsSource = smsSource;
	}

	public String getSmsTypeId() {
		return smsTypeId;
	}

	public void setSmsTypeId(String smsTypeId) {
		this.smsTypeId = smsTypeId;
	}

	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getSmsTitle() {
		return smsTitle;
	}

	public void setSmsTitle(String smsTitle) {
		this.smsTitle = smsTitle;
	}

	public String getSmsContext() {
		return smsContext;
	}

	public void setSmsContext(String smsContext) {
		this.smsContext = smsContext;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
