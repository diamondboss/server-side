package com.diamondboss.user.vo;

/**
 * 插入宠物信息Vo
 * @author xzf
 *
 */
public class InputPetInfoVo {
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 宠物昵称
	 */
	private String name;
	
	/**
	 * 宠物年龄
	 */
	private String age;
	
	/**
	 * 宠物性别
	 */
	private int sex;
	
	/**
	 * 宠物品种
	 */
	private String varieties;
	
	public InputPetInfoVo(){
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getVarieties() {
		return varieties;
	}

	public void setVarieties(String varieties) {
		this.varieties = varieties;
	}
}
