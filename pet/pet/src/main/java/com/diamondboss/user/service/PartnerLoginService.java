package com.diamondboss.user.service;

import com.diamondboss.user.pojo.PartnerLoginPojo;
import com.diamondboss.user.util.vo.LoginVo;

public interface PartnerLoginService {

	public PartnerLoginPojo login(LoginVo vo);
	
}
