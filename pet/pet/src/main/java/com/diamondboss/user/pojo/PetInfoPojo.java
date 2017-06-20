package com.diamondboss.user.pojo;

/**
 * 宠物信息pojo对象
 * 
 * @author John
 * @since 2017-06-20
 *  
 */
public class PetInfoPojo {

	/**
	 * 宠物id
	 */
	private String id;
	
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
	private String sex;
	
	/**
	 * 宠物品种
	 */
	private String varieties;

	/**
	 * 宠物id
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 宠物id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 用户id
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 宠物昵称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 宠物昵称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 宠物年龄
	 * @return
	 */
	public String getAge() {
		return age;
	}

	/**
	 * 宠物年龄
	 * @param age
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * 宠物性别
	 * @return
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 宠物性别
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 宠物品种
	 * @return
	 */
	public String getVarieties() {
		return varieties;
	}

	/**
	 * 宠物品种
	 * @param varieties
	 */
	public void setVarieties(String varieties) {
		this.varieties = varieties;
	}
	
}
