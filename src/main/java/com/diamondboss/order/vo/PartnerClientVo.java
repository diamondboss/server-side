package com.diamondboss.order.vo;

/**
 * 合伙人clientId
 * @author xzf
 *
 */
public class PartnerClientVo {
	/**
	 * 合伙人Id
	 */
	private String partnerId;
	
	/**
	 * 合伙人姓名
	 */
	private String partnerName;

	/**
	 * 客户端Id（合伙人）
	 */
	private String clientId;

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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
