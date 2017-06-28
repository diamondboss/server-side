package com.diamondboss.user.service.impl;

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

}
