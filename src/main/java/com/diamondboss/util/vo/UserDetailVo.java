package com.diamondboss.util.vo;

import java.util.Date;

/**
 * 用户预约明细Vo
 * @author xzf
 *
 */
public class UserDetailVo {
	private String orderTime;

    private Date createTime;

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
