package com.diamondboss.user.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.service.impl.DistributeOrderServiceImpl;
import com.diamondboss.user.pojo.PartnerConfirmOrderPojo;
import com.diamondboss.user.repository.PartnerConfirmOrderMapper;
import com.diamondboss.user.service.PartnerConfirmOrderService;
import com.diamondboss.user.vo.PartnerConfirmOrderVo;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.wallet.pojo.PartnerWalletPojo;
import com.diamondboss.wallet.service.PartnerRebateService;

@Service
public class PartnerConfirmOrderServiceImpl implements PartnerConfirmOrderService{

	private static final Logger logger = Logger.getLogger(PartnerConfirmOrderServiceImpl.class);
	
	@Autowired
	private PartnerConfirmOrderMapper confirmOrderMapper;
	
	@Autowired
	private PartnerRebateService partnerRebateService;
	
	@Override
	public void receive(PartnerConfirmOrderVo vo) {
		
		PartnerConfirmOrderPojo pojo = new PartnerConfirmOrderPojo();
		pojo.setPartnerTable(TableUtils.getOrderTableName(Long.valueOf(vo.getPartnerId()), 
				PetConstants.ORDER_PARTNER_TABLE_PREFIX));
		pojo.setUserTable(TableUtils.getOrderTableName(Long.valueOf(vo.getUserId()), 
				PetConstants.ORDER_USER_TABLE_PREFIX));
		pojo.setOutTradeNo(vo.getOutTradeNo());
		LocalDate date = LocalDate.now();
		date.getMonthValue();
		date.getDayOfMonth();
		LocalTime time = LocalTime.now().withNano(0).withSecond(0);
		
		pojo.setTime(date.getMonthValue() + "/" + date.getDayOfMonth() + " " + time.toString());
		
		// 更新合伙人状态
		confirmOrderMapper.updatePartnerOrderForReceive(pojo);
		
		// 更新用户状态
		confirmOrderMapper.updateUserOrderForReceive(pojo);
		
	}

	@Override
	public void giveBack(PartnerConfirmOrderVo vo) {
		
		PartnerConfirmOrderPojo pojo = new PartnerConfirmOrderPojo();
		pojo.setPartnerTable(TableUtils.getOrderTableName(Long.valueOf(vo.getPartnerId()), 
				PetConstants.ORDER_PARTNER_TABLE_PREFIX));
		pojo.setUserTable(TableUtils.getOrderTableName(Long.valueOf(vo.getUserId()), 
				PetConstants.ORDER_USER_TABLE_PREFIX));
		pojo.setOutTradeNo(vo.getOutTradeNo());
		
		LocalDate date = LocalDate.now();
		date.getMonthValue();
		date.getDayOfMonth();
		LocalTime time = LocalTime.now().withNano(0).withSecond(0);
		
		pojo.setTime(date.getMonthValue() + "/" + date.getDayOfMonth() + " " + time.toString());
		
		// 更新合伙人状态
		confirmOrderMapper.updatePartnerOrderForGiveBack(pojo);
		
		// 更新用户状态
		confirmOrderMapper.updateUserOrderForGiveBack(pojo);
		
		try{
			PartnerWalletPojo wallet = confirmOrderMapper.queryWalletbyPojo(pojo);
			partnerRebateService.rebate(wallet);
		}catch(Exception e){
			logger.error("更新合伙人钱包金额异常" + e.getMessage());
		}
		
	}
}
