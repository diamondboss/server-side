package com.diamondboss.user.vo;

import java.math.BigDecimal;

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
	
	/**
	 * 小区Id
	 */
	private String communityId;
	
	/**
	 * 经度
	 */
	private BigDecimal longitude;
	
	/**
	 * 纬度
	 */
	private BigDecimal latitude;

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

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	
	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

}
