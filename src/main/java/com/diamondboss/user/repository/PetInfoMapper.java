package com.diamondboss.user.repository;

import com.diamondboss.user.pojo.PetInfoPojo;

/**
 * 宠物信息数据访问
 * 
 * @author John
 * @since 2017-06-20
 *  
 */
public interface PetInfoMapper {

	/**
	 * 根据用户id查询宠物信息
	 * @param userId
	 */
	public void queryPetInfoByUserId(String userId);
	
	/**
	 * 插入宠物信息
	 */
	public void insertPetInfo(PetInfoPojo pojo);
	
	/**
	 * 更新宠物信息
	 */
	public void updatePetInfo(PetInfoPojo pojo);
	
}
