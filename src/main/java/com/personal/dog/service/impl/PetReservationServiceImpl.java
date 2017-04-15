package com.personal.dog.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personal.dog.dao.IpetBaseDao;
import com.personal.dog.service.IPetReservationService;
import com.personal.util.pojo.PetBasePojo;

@Service("petReservationService")
public class PetReservationServiceImpl implements IPetReservationService{

	@Resource  
	private IpetBaseDao petBaseDao;  
	
	@Override
	public PetBasePojo queryPetBase(){
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		return petBaseDao.selectByLimti(param);
	}
	
	/**
	 * 查询展示宠物明细信息
	 * 1.图片url
	 * 2.基本信息
	 * 3.自我介绍
	 * 4.健康情况
	 */
	public void queryPetBaseInfo(){
		
		Map<String, Object> param = new HashMap<String, Object>();
		
	}
	
	
	
	
	
}
