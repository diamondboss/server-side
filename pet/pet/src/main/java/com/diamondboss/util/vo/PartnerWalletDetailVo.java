package com.diamondboss.util.vo;

import java.math.BigDecimal;
import java.util.Date;

public class PartnerWalletDetailVo {
	private Long id;
	
	private String partnerId;
	
	private BigDecimal amount;
	
	private Integer kind;
	
	private String orderDate;
	
	public PartnerWalletDetailVo(){
		
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

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
}
