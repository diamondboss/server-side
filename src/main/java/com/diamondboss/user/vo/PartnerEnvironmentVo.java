package com.diamondboss.user.vo;

import com.diamondboss.user.pojo.PartnerInfoPojo;

/**
 * 合伙人环境展示
 * 
 * @author John
 * @since 2017-08-06
 *  
 */
public class PartnerEnvironmentVo {

	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 订单状态
	 */
	private String orderStatus;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 年龄
	 */
	private String age;
	
	/**
	 * 备注
	 */
	private String remark;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public PartnerEnvironmentVo pojoToVo(PartnerInfoPojo pojo, 
			PartnerEnvironmentVo vo){
		
		if(pojo == null){
			return vo;
		}
		
		vo.setAddress(pojo.getAddress());
		vo.setPhone(pojo.getPhoneNumber());
		vo.setName(pojo.getName());
		vo.setSex(pojo.getSex());
		vo.setAge(pojo.getAge());
		vo.setRemark(pojo.getRemark());
		
		return vo;
	}
	
}
