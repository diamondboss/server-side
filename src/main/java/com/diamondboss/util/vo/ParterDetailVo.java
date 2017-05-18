package com.diamondboss.util.vo;

import java.math.BigDecimal;

/**
 * 合伙人提现返回前台Vo
 * @author xzf
 *
 */
public class ParterDetailVo {
	private BigDecimal amount;
	
	private String orderTime;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
}
