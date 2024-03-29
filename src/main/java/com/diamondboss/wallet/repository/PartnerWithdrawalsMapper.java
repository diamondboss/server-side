package com.diamondboss.wallet.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.wallet.pojo.PartnerWalletPojo;
import com.diamondboss.wallet.vo.PartnerTotalWalletVo;

/**
 * 合伙人提现
 * 
 * @author John
 * @since 2017-06-29
 *  
 */
public interface PartnerWithdrawalsMapper {

	/**
	 * 根据合伙人id查询钱包汇总
	 * @param partnerId
	 * @return
	 */
	public PartnerWalletPojo queryPartnerWallet(String partnerId);
	
	/**
	 * 根据合伙人id更新合伙人钱包
	 * @param Pojo
	 * @return
	 */
	public int updatePartnerWallet(PartnerWalletPojo Pojo);
	
	/**
	 * 根据合伙人id查询钱包明细
	 * @param Pojo
	 * @return
	 */
	public List<PartnerTotalWalletVo> queryPartnerWalletDetailed(PartnerWalletPojo pojo);
	
	/**
	 * 根据合伙人id查询钱包明细
	 * @param Pojo
	 * @return
	 */
	public List<PartnerTotalWalletVo> queryTotalDetailed(PartnerWalletPojo pojo);
	
	/**
	 * 根据合伙人id插入合伙人钱包明细
	 * @param Pojo
	 * @return
	 */
	public int insertPartnerWalletDetailed(PartnerWalletPojo Pojo);
	
	/**
	 * 查询今日收益
	 * @param partnerId
	 * @return
	 */
	public String queryEarningsToday(Map<String, Object> param);
	
	/**
	 * 根据合伙人id查询钱包
	 * @param param
	 * @return
	 */
	public PartnerWalletPojo queryAvailableWallet(Map<String, Object> param);
	
}
