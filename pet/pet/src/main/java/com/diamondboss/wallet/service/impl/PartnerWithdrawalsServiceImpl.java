package com.diamondboss.wallet.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.wallet.pojo.PartnerWalletPojo;
import com.diamondboss.wallet.repository.PartnerWithdrawalsMapper;
import com.diamondboss.wallet.service.PartnerWithdrawalsService;
import com.diamondboss.wallet.vo.PartnerTotalWalletVo;
import com.diamondboss.wallet.vo.PartnerWalletVo;
import com.diamondboss.wallet.vo.WalletSummaryVo;
import com.diamondboss.wallet.vo.WithdrawalsVo;

/**
 * 合伙人提现
 * 
 * @author John
 * @since 2017-06-28
 *  
 */
@Service
public class PartnerWithdrawalsServiceImpl implements PartnerWithdrawalsService{

	@Autowired
	private PartnerWithdrawalsMapper partnerWithdrawalsMapper;
	
	/**
	 * 提现
	 */
	@Override
	public boolean withdrawals(WithdrawalsVo vo) {
		
		if(isAvailable(vo)){
			
			PartnerWalletPojo pojo = voTopojo(vo);
			
			partnerWithdrawalsMapper.insertPartnerWalletDetailed(pojo);
			
			partnerWithdrawalsMapper.updatePartnerWallet(pojo);
			
			return true;
			
		}else{
			
			// 不可提现
			return false;
		}
		
	}

	/**
	 * 查询钱包汇总
	 */
	@Override
	public WalletSummaryVo querySummaryInfo(String partnerId) {
		
		WalletSummaryVo vo = new WalletSummaryVo();
		PartnerWalletPojo pojo = partnerWithdrawalsMapper.queryPartnerWallet(partnerId);
		
		if(pojo == null || pojo.getAmt() == null){
			vo.setAvailableBalance("0");
		}else{
			vo.setAvailableBalance(pojo.getAmt().toString());
		}
		
		PartnerWalletPojo pojo2 = partnerWithdrawalsMapper.queryPartnerWallet(partnerId);
		
		if(pojo2 == null || pojo2.getAmt() == null){
			vo.setRealBalance("0");
		}else{
			vo.setRealBalance(pojo.getAmt().toString());
		}
		
		Map<String, Object> param = new HashMap<>();
		param.put("partnerId", partnerId);
		param.put("orderDate", LocalDate.now().toString());
		
		param.put("partnerWalletDetail", TableUtils.getOrderTableName(
				Long.valueOf(partnerId), PetConstants.PARTNER_WALLET_DETAIL));
		
		String earningsToday = partnerWithdrawalsMapper.queryEarningsToday(param);
		if(StringUtils.isBlank(earningsToday)){
			vo.setEarningsToday("0");
		}else{
			vo.setEarningsToday(earningsToday);
		}
		
		
		return vo;
	}

	/**
	 * 查询钱包明细
	 */
	@Override
	public List<PartnerTotalWalletVo> queryDetailed(String partnerId) {
		
		PartnerWalletPojo pojo = new PartnerWalletPojo();
		pojo.setPartnerId(partnerId);
		pojo.setPartnerWalletDetail("partner_wallet_detail_1");
		List<PartnerTotalWalletVo> list = partnerWithdrawalsMapper.queryPartnerWalletDetailed(pojo);
		
		return list;
	}

	/**
	 * 查询钱包明细
	 */
	@Override
	public List<PartnerTotalWalletVo> queryTotalDetailed(String partnerId){
		
		PartnerWalletPojo pojo = new PartnerWalletPojo();
		pojo.setPartnerId(partnerId);
		pojo.setPartnerWalletDetail("partner_wallet_detail_1");
		List<PartnerTotalWalletVo> list = partnerWithdrawalsMapper.queryTotalDetailed(pojo);
		
		return list;
		
	}
	
	/**
	 * 提现金额是否可用
	 * @param vo
	 * @return true-可以提现;false-不可提现
	 */
	private Boolean isAvailable(WithdrawalsVo vo){
		
		BigDecimal value = new BigDecimal(vo.getValue());
		
		String date = LocalDate.now().toString();// 获取当前日期

		String amt = "";
		PartnerWalletPojo pojo = 
				partnerWithdrawalsMapper.queryPartnerWallet(vo.getPartnerId());
		
		if(pojo == null || pojo.getAmt() == null){
			amt = "0";
		}else{
			amt = pojo.getAmt().toString();
		}	
		
		
		BigDecimal quota = new BigDecimal(amt).subtract(value);// 可提现金额
		
		if(value.compareTo(quota) < 1)
			return true;
		
		return false;
		
	}
	
	private PartnerWalletPojo voTopojo(WithdrawalsVo vo){
		
		PartnerWalletPojo pojo = new PartnerWalletPojo();
		pojo.setAmt(new BigDecimal(vo.getValue()));
		pojo.setKind("2");
		pojo.setOrderDate(LocalDate.now().toString());
		pojo.setPartnerId(vo.getPartnerId());
		pojo.setPartnerWalletDetail(TableUtils.getOrderTableName(
				Long.valueOf(vo.getPartnerId()), 
				PetConstants.PARTNER_WALLET_DETAIL));
		
		return pojo;
	}
}
