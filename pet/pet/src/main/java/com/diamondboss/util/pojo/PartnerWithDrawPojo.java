package com.diamondboss.util.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class PartnerWithDrawPojo {

	private Long id;
	
	private String parterId;
	 
	private BigDecimal amount;
	
	private String orderTime;
	
	private Integer withdrawState;
	
	private Integer effective;
	
	private Date createTime;

    private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParterId() {
		return parterId;
	}

	public void setParterId(String parterId) {
		this.parterId = parterId;
	}

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

	public Integer getWithdrawState() {
		return withdrawState;
	}

	public void setWithdrawState(Integer withdrawState) {
		this.withdrawState = withdrawState;
	}

	public Integer getEffective() {
		return effective;
	}

	public void setEffective(Integer effective) {
		this.effective = effective;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
