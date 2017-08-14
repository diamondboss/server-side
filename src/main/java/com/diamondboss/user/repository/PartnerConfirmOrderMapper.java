package com.diamondboss.user.repository;

import com.diamondboss.user.pojo.PartnerConfirmOrderPojo;
import com.diamondboss.wallet.pojo.PartnerWalletPojo;

public interface PartnerConfirmOrderMapper {

	/**
	 * 更新合伙人订单状态-已接收
	 * @param pojo
	 */
	public void updatePartnerOrderForReceive(PartnerConfirmOrderPojo pojo);
	
	/**
	 * 更新用户订单状态-已接收
	 * @param pojo
	 */
	public void updateUserOrderForReceive(PartnerConfirmOrderPojo pojo);
	
	/**
	 * 更新合伙人状态-已送还
	 * @param pojo
	 */
	public void updatePartnerOrderForGiveBack(PartnerConfirmOrderPojo pojo);

	/**
	 * 更新用户状态-已送还
	 * @param pojo
	 */
	public void updateUserOrderForGiveBack(PartnerConfirmOrderPojo pojo);
	
	/**
	 * 根据订单编号查询钱包信息
	 * @param pojo
	 * @return
	 */
	public PartnerWalletPojo queryWalletbyPojo(PartnerConfirmOrderPojo pojo);
	
}
