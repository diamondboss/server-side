package com.diamondboss.user.service.impl;

import com.diamondboss.user.pojo.PetInfoPojo;
import com.diamondboss.user.repository.PetInfoMapper;
import com.diamondboss.user.service.PetInfoService;
import com.diamondboss.user.vo.InputPetInfoVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetInfoServiceImpl implements PetInfoService{
	
	@Autowired
	private PetInfoMapper petInfoMapper;

	@Override
	public PetInfoPojo queryPetInfo(String userId) {
		return petInfoMapper.queryPetInfoByUserId(userId);
	}

	@Override
	public int inputPetInfo(InputPetInfoVo vo) {
		return petInfoMapper.insertPetInfo(vo);
	}

	@Override
	public int updatePetInfo(PetInfoPojo pojo) {
		return petInfoMapper.updatePetInfo(pojo);
	}

}
