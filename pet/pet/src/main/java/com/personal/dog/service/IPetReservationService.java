package com.personal.dog.service;

import java.util.List;

import com.personal.util.pojo.PetBasePojo;
import com.personal.util.vo.PetDetailedVo;

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
