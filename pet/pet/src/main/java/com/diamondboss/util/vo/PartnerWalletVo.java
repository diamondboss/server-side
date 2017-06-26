package com.diamondboss.util.vo;

import java.math.BigDecimal;

public class PartnerWalletVo {
	private Long id;
	
	private String partnerId;
	
	private BigDecimal amount;
	
	public PartnerWalletVo(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
