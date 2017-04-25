package com.diamondboss.util.vo;

import java.util.List;

public class PetDetailedVo {

	private List<String> photoList;
	
	private List<String> basic;
	
	private List<String> introduce;
	
	private List<String> healthy;

	public List<String> getPhotoList() {
		
		return photoList;
	}

	public void setPhotoList(List<String> photoList) {
		this.photoList = photoList;
	}

	public List<String> getBasic() {
		return basic;
	}

	public void setBasic(List<String> basic) {
		this.basic = basic;
	}

	public List<String> getIntroduce() {
		return introduce;
	}

	public void setIntroduce(List<String> introduce) {
		this.introduce = introduce;
	}

	public List<String> getHealthy() {
		return healthy;
	}

	public void setHealthy(List<String> healthy) {
		this.healthy = healthy;
	}
	
}
