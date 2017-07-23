package com.diamondboss.user.service;

import com.diamondboss.user.vo.PartnerConfirmOrderVo;

public interface PartnerConfirmOrderService {

	public void receive(PartnerConfirmOrderVo vo);
	
	public void giveBack(PartnerConfirmOrderVo vo);
	
}
