package com.diamondboss.user.service.impl;

import com.diamondboss.user.pojo.PartnerInfoPojo;
import com.diamondboss.user.repository.PartnerInfoMapper;
import com.diamondboss.user.service.PartnerInfoService;
import com.diamondboss.user.vo.ResponsePartnerOfCommunityVo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerInfoServiceImpl implements PartnerInfoService{
	
	@Autowired
	private PartnerInfoMapper partnerInfoMapper;

	@Override
	public List<ResponsePartnerOfCommunityVo> queryPartnerOfCommunityList(String communityName) {
		return partnerInfoMapper.queryPartnerOfCommunityList(communityName);
	}

	@Override
	public PartnerInfoPojo queryPhoneOfPartner(String partnerId) {
		return partnerInfoMapper.queryInfoByPartnerId(partnerId);
	}
	
	/**
	 * 根据合伙人id查询到具体某个合伙人信息
	 * @return
	 */
	@Override
	public PartnerInfoPojo queryPartnerInfo(String partnerId){
		
		PartnerInfoPojo pojo = partnerInfoMapper.queryInfoByPartnerId(partnerId);
		return pojo;
	}

}
