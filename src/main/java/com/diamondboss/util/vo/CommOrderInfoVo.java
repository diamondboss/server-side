package com.diamondboss.util.vo;

public class CommOrderInfoVo {

	/**
	 * 小区社区合伙人数量
	 */
	public String commParNum;
	
	/**
	 * 已预订宠物量
	 */
	public String commPetNum;
	
	public CommOrderInfoVo(){
		
	}

	/**
	 * 小区社区合伙人数量
	 * @return
	 */
	public String getCommParNum() {
		return commParNum;
	}

	/**
	 * 小区社区合伙人数量
	 * @param commParNum
	 */
	public void setCommParNum(String commParNum) {
		this.commParNum = commParNum;
	}

	/**
	 * 已预订宠物量
	 * @return
	 */
	public String getCommPetNum() {
		return commPetNum;
	}

	/**
	 * 已预订宠物量
	 * @param commPetNum
	 */
	public void setCommPetNum(String commPetNum) {
		this.commPetNum = commPetNum;
	}
	
	
	
}
