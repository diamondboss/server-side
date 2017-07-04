package com.diamondboss.user.service;

import java.util.List;

import com.diamondboss.user.pojo.PartnerInfoPojo;
import com.diamondboss.user.vo.ResponsePartnerOfCommunityVo;

public interface PartnerInfoService {
	
	/**
	 * 根据小区名字，返回该小区所有的合伙人信息
	 * @param communityName
	 * @return
	 */
	public List<ResponsePartnerOfCommunityVo> queryPartnerOfCommunityList(String communityName);
	
	/**
	 * 根据合伙人id，返回合伙人的信息
	 * @param partnerId
	 * @return
	 */
	public PartnerInfoPojo queryPhoneOfPartner(String partnerId);
}
