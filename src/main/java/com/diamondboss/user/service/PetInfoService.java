package com.diamondboss.user.service;

import com.diamondboss.user.pojo.PetInfoPojo;
import com.diamondboss.user.vo.InputPetInfoVo;

public interface PetInfoService {
	
	/**
	 * 根据用户ID，查询宠物信息
	 * @param userId 用户ID
	 * @return
	 */
	public PetInfoPojo queryPetInfo(String userId);
	
	/**
	 * 插入宠物信息
	 * @param vo
	 * @return
	 */
	public int inputPetInfo(InputPetInfoVo vo);
	
	/**
	 * 更新宠物信息
	 * @param vo
	 * @return
	 */
	public int updatePetInfo(PetInfoPojo pojo);
}
