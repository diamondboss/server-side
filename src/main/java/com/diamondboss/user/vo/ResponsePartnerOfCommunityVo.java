package com.diamondboss.user.vo;

/**
 * 查询小区合伙人信息返回Vo
 * @author xzf
 *
 */
public class ResponsePartnerOfCommunityVo {
	
	/**
	 * 合伙人Id
	 */
	private Long partnerId;

	/**
	 * 合伙人名字
	 */
	private String partnerName;

	/**
	 * 合伙人手机号
	 */
	private String phonenumber;

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


}
