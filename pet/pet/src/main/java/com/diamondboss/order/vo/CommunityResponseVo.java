package com.diamondboss.order.vo;

/**
 * 根据小区名字获取小区Id返回Vo
 * @author xzf
 *
 */
public class CommunityResponseVo {
	/**
	 * 小区Id
	 */
	private String communityId;
	
	/**
	 * 小区名字
	 */
	private String communityName;

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
}
