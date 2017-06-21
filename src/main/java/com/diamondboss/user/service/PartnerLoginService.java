package com.diamondboss.user.service;

import com.diamondboss.user.pojo.PartnerLoginPojo;
import com.diamondboss.user.vo.LoginVo;

public interface PartnerLoginService {

	public PartnerLoginPojo login(LoginVo vo);
	
}
