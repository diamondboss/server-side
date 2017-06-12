package com.diamondboss.util.pojo;

import java.math.BigDecimal;

public class OrderUserPojo {

	/**
	 * 主键id
	 */
	public int id;
	
	/**
	 * 宠物接收时间
	 */
	public String receiveTime;
	
	/**
	 * 宠物送还时间
	 */
	public String returnTime;
	
	/**
	 * 宠物名称
	 */
	public String petName;
	
	/**
	 * 宠物性别
	 */
	public int sex;
	
	/**
	 * 宠物年龄
	 */
	public int age;
	
	/**
	 * 联系方式
	 */
	public String phone;
	
	/**
	 * 用户姓名
	 */
	public String userName;
	
	/**
	 * 备注
	 */
	public String remark;
	
	/**
	 * 用户id
	 */
	public int userId;
	
	/**
	 * 合伙人id
	 */
	public int partnerId;
	
	/**
	 * 订单日期
	 */
	public String orderDate;
	
	/**
	 * 订单状态
	 */
	public String orderStatus;
	
	/**
	 * 金额
	 */
	public BigDecimal amt;
	
}
