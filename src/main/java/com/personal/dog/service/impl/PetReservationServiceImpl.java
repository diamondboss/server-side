package com.personal.dog.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personal.dog.dao.IpetBaseDao;
import com.personal.dog.service.IPetReservationService;
import com.personal.util.pojo.petBasePojo;

@Service("petReservationService")
public class PetReservationServiceImpl implements IPetReservationService{

	@Resource  
	private IpetBaseDao petBaseDao;  
	
	@Override
	public petBasePojo queryPetBase(){
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		return petBaseDao.selectByLimti(param);
	}
	
}
