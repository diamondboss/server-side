package com.personal.dog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personal.dog.dao.IPetBaseDao;
import com.personal.dog.dao.IPetBaseInfoDao;
import com.personal.dog.dao.IPetBasePhotoDao;
import com.personal.dog.service.IPetReservationService;
import com.personal.util.dto.PetBaseInfoDto;
import com.personal.util.dto.PetBasePhotoDto;
import com.personal.util.pojo.PetBasePojo;
import com.personal.util.vo.PetDetailedVo;

@Service("petReservationService")
public class PetReservationServiceImpl implements IPetReservationService{

	@Resource  
	private IPetBaseDao petBaseDao;  	
	@Resource  
	private IPetBasePhotoDao petBasePhotoDao;  
	@Resource
	private IPetBaseInfoDao petBaseInfoDao;
	
	
	@Override
	public List<PetBasePojo> queryPetBase(){
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		return petBaseDao.selectByLimit(param);
	}
	
	/**
	 * 查询展示宠物明细信息
	 * 1.图片url
	 * 2.基本信息
	 * 3.自我介绍
	 * 4.健康情况
	 */
	@Override
	public PetDetailedVo queryPetDetailed(int id){
		
		List<String> photoList = petBasePhotoDao.queryURLById(id);
		
		List<PetBaseInfoDto> infoList = petBaseInfoDao.queryInfoById(id);
		
		List<String> basic = new ArrayList<String>();
		List<String> introduce = new ArrayList<String>();
		List<String> healthy = new ArrayList<String>();
		if(infoList != null && 0 != infoList.size()){
			for(PetBaseInfoDto i:infoList){
				if(1 == i.getTextType()){
					basic.add(i.getText());
				}else if(2 == i.getTextType()){
					introduce.add(i.getText());
				}else if(3 == i.getTextType()){
					healthy.add(i.getText());
				}
			}
		}
		
		PetDetailedVo vo = new PetDetailedVo();
		vo.setPhotoList(photoList);
		vo.setBasic(basic);
		vo.setIntroduce(introduce);
		vo.setHealthy(healthy);
		
		return vo;
	}
	
	
	
	
	
}
