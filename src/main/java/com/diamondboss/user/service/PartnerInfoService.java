package com.diamondboss.user.service;

import java.util.List;

import com.diamondboss.user.pojo.PartnerInfoPojo;
import com.diamondboss.user.vo.PartnerEnvironmentVo;
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
	
	/**
	 * 根据合伙人id查询到具体某个合伙人信息
	 * @return
	 */
	public PartnerInfoPojo queryPartnerInfo(String partnerId);
	
	/**
	 * 查询合伙人饲养环境
	 * @return
	 */
	public PartnerEnvironmentVo queryEnvironment(String partnerId);
	
}
