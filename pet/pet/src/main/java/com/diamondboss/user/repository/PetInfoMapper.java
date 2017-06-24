package com.diamondboss.user.repository;

import com.diamondboss.user.pojo.PetInfoPojo;
import com.diamondboss.user.vo.InputPetInfoVo;

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
	 * @return 
	 */
	public PetInfoPojo queryPetInfoByUserId(String userId);
	
	/**
	 * 插入宠物信息
	 */
	public int insertPetInfo(InputPetInfoVo vo);
	
	/**
	 * 更新宠物信息
	 */
	public int updatePetInfo(PetInfoPojo pojo);
	
}
