package com.diamondboss.wallet.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.wallet.pojo.PartnerWalletPojo;
import com.diamondboss.wallet.repository.PartnerWithdrawalsMapper;
import com.diamondboss.wallet.service.PartnerWithdrawalsService;
import com.diamondboss.wallet.vo.PartnerTotalWalletVo;
import com.diamondboss.wallet.vo.PartnerWalletVo;
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
	public void withdrawals() {
		
		WithdrawalsVo vo = new WithdrawalsVo();
		if(isAvailable(vo)){
			
			PartnerWalletPojo pojo = voTopojo(vo);
			
			partnerWithdrawalsMapper.insertPartnerWalletDetailed(null);
			
			partnerWithdrawalsMapper.updatePartnerWallet(null);
			
		}else{
			// 不可提现
		}
		
	}

	/**
	 * 查询钱包汇总
	 */
	@Override
	public String querySummaryInfo(String partnerId) {
		
		PartnerWalletPojo pojo = partnerWithdrawalsMapper.queryPartnerWallet(partnerId);
		if(pojo == null || pojo.getAmt() == null){
			return "0";
		}else{
			return pojo.getAmt().toString();
		}
	}

	/**
	 * 查询钱包明细
	 */
	@Override
	public List<PartnerWalletVo> queryDetailed(String partnerId) {
		
		PartnerWalletPojo pojo = new PartnerWalletPojo();
		pojo.setPartnerId(partnerId);
		pojo.setPartnerWalletDetail("partner_wallet_detail_1");
		List<PartnerWalletVo> list = partnerWithdrawalsMapper.queryPartnerWalletDetailed(pojo);
		
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
		 
		// 查询可提现额度
		PartnerWalletPojo pojo = 
				partnerWithdrawalsMapper.queryPartnerWallet(vo.getPartnerId());
		
		if(pojo == null || pojo.getAmt() == null){
			pojo = new PartnerWalletPojo();
			pojo.setAmt(new BigDecimal("0"));
		}
		
//		List<PartnerWalletPojo> pojoList = 
//				partnerWithdrawalsMapper.queryPartnerWalletDetailed(null);
		
		BigDecimal unavailable = new BigDecimal("0");// 没有超过7天不可提现金额
//		for(PartnerWalletPojo i:pojoList){
//			unavailable = unavailable.add(i.getAmt());
//		}
		
		BigDecimal quota = pojo.getAmt().subtract(unavailable);// 可提现金额
		
		if(value.compareTo(quota) < 1)
			return true;
		
		return false;
		
	}
	
	private PartnerWalletPojo voTopojo(WithdrawalsVo vo){
		
		PartnerWalletPojo pojo = new PartnerWalletPojo();
		pojo.setAmt(new BigDecimal("-" + vo.getValue()));
		pojo.setKind("提现");
		pojo.setOrderDate(LocalDate.now().toString());
		pojo.setPartnerId(vo.getPartnerId());
		pojo.setPartnerWalletDetail("");
		pojo.setStatus("申请提现");
		
		return pojo;
	}
}
