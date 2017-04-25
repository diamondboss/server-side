package com.diamondboss.dog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.diamondboss.dog.repository.PetBaseInfoMapper;
import org.springframework.stereotype.Service;

import com.diamondboss.dog.repository.PetBaseMapper;
import com.diamondboss.dog.repository.PetBasePhotoMapper;
import com.diamondboss.dog.service.IPetReservationService;
import com.diamondboss.util.dto.PetBaseInfoDto;
import com.diamondboss.util.pojo.PetBasePojo;
import com.diamondboss.util.vo.PetDetailedVo;

@Service("petReservationService")
public class PetReservationServiceImpl implements IPetReservationService{

	@Resource  
	private PetBaseMapper petBaseDao;
	@Resource  
	private PetBasePhotoMapper petBasePhotoDao;
	@Resource
	private PetBaseInfoMapper petBaseInfoDao;
	
	
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
