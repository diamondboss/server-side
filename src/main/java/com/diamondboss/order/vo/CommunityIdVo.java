package com.diamondboss.order.vo;

/**
 * 根据小区名字获取小区Id请求Vo
 * @author xzf
 *
 */
public class CommunityIdVo {
	
	/**
	 * 小区名字
	 */
	private String communityName;

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
}
