package com.diamondboss.dog.service;

import java.util.List;

import com.diamondboss.util.pojo.PetBasePojo;
import com.diamondboss.util.vo.PetDetailedVo;

public interface IPetReservationService {

	public List<PetBasePojo> queryPetBase();
	
	/**
	 * 查询展示宠物明细信息
	 * 1.图片url
	 * 2.基本信息
	 * 3.自我介绍
	 * 4.健康情况
	 */
	public PetDetailedVo queryPetDetailed(int id);
}
