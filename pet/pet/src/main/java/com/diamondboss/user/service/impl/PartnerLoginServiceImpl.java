package com.diamondboss.user.service.impl;

import com.diamondboss.user.pojo.PartnerLoginPojo;
import com.diamondboss.user.repository.PartnerLoginMapper;
import com.diamondboss.user.service.PartnerLoginService;
import com.diamondboss.user.util.vo.LoginVo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerLoginServiceImpl implements PartnerLoginService{

	private static final Logger log = Logger.getLogger(PartnerLoginServiceImpl.class);
	
	@Autowired
	private PartnerLoginMapper partnerLoginMapper;
	
	@Override
	public PartnerLoginPojo login(LoginVo vo) {
		PartnerLoginPojo partnerLogin = partnerLoginMapper.queryPartnerLoginByPhone(vo.getPhone());
		if(partnerLogin == null){
			log.info("查询合伙人不存在，继续查找用户");
			return partnerLogin;
		}
		
		return partnerLogin;
	}

}
