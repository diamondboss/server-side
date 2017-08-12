package com.diamondboss.user.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.order.service.PlaceOrderService;
import com.diamondboss.user.pojo.PartnerInfoPojo;
import com.diamondboss.user.repository.PartnerInfoMapper;
import com.diamondboss.user.service.PartnerInfoService;
import com.diamondboss.user.vo.PartnerEnvironmentVo;
import com.diamondboss.user.vo.ResponsePartnerOfCommunityVo;

@Service
public class PartnerInfoServiceImpl implements PartnerInfoService{
	
	@Autowired
	private PartnerInfoMapper partnerInfoMapper;

	@Autowired
	private PlaceOrderService placeOrderService;
	
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
	
	/**
	 * 查询合伙人饲养环境
	 * @return
	 */
	public PartnerEnvironmentVo queryEnvironment(String partnerId){
		
		String today = LocalDate.now().toString();
		String tomorrow = LocalDate.now().plusDays(1).toString();
		String twoDoday = LocalDate.now().plusDays(2).toString();
		
		boolean isToday = placeOrderService.checkOrderCountsOfPartner(partnerId,today);
		boolean isTodaytomorrow = placeOrderService.checkOrderCountsOfPartner(partnerId,tomorrow);
		boolean isTwoDoday = placeOrderService.checkOrderCountsOfPartner(partnerId,twoDoday);
		
		StringBuffer sb = new StringBuffer();
		sb.append("今日");
		
		if(isToday){
			sb.append("已满,明日");
		}else{
			sb.append("可接单,明日");
		}
		
		if(isTodaytomorrow){
			sb.append("已满,后日");
		}else{
			sb.append("可接单,后日");
		}
		
		if(isTwoDoday){
			sb.append("已满");
		}else{
			sb.append("可接单");
		}
		
		PartnerEnvironmentVo vo = new PartnerEnvironmentVo();
		vo.setOrderStatus(sb.toString());
		PartnerInfoPojo pojo = partnerInfoMapper.queryInfoByPartnerId(partnerId);
		vo = vo.pojoToVo(pojo, vo);
		return vo;
		
	}


	

}
