package com.diamondboss.wallet.pojo;

import java.math.BigDecimal;

/**
 * 合伙人钱包pojo
 * 
 * @author John
 * @since 2017-06-29
 *  
 */
public class PartnerWalletPojo {

	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 合伙人id
	 */
	private String partnerId;
	
	/**
	 * 金额
	 */
	private BigDecimal amt;
	
	/**
	 * 订单日期
	 */
	private String orderDate;
	
	/**
	 * 交易类型
	 */
	private String kind;
	
	/**
	 * 交易状态
	 */
	private String status;

	/**
	 * 合伙人钱包明细表
	 */
	private String partnerWalletDetail;
	
	/**
	 * 主键id
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 合伙人id
	 * @return
	 */
	public String getPartnerId() {
		return partnerId;
	}

	/**
	 * 合伙人id
	 * @param partnerId
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * 交易金额
	 * @return
	 */
	public BigDecimal getAmt() {
		return amt;
	}

	/**
	 * 交易金额
	 * @param amt
	 */
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	/**
	 * 订单日期
	 * @return
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * 订单日期
	 * @param orderDate
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 交易类型
	 * @return
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * 交易类型
	 * @param kind
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * 交易状态
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 交易状态
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 合伙人钱包明细表
	 * @return
	 */
	public String getPartnerWalletDetail() {
		return partnerWalletDetail;
	}

	/**
	 * 合伙人钱包明细表
	 * @param partnerWalletDetail
	 */
	public void setPartnerWalletDetail(String partnerWalletDetail) {
		this.partnerWalletDetail = partnerWalletDetail;
	}
	
	
}
