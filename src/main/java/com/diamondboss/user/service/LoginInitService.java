package com.diamondboss.user.service;

import java.util.List;

public interface LoginInitService {

	public List<String> queryPartnerInCommunity(Object o);
	
	public void queryPartnerDetail(String partnerId);
	
	public void queryOrderInHand(String userId);
}
