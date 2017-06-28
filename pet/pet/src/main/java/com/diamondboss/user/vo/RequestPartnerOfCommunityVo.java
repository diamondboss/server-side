package com.diamondboss.user.vo;

/**
 * 查询某个小区中所有的合伙人Vo
 * @author xzf
 *
 */
public class RequestPartnerOfCommunityVo {
	/**
	 * 小区名字
	 */
	private String communityName;
	
	public RequestPartnerOfCommunityVo(){
		
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
	
}
