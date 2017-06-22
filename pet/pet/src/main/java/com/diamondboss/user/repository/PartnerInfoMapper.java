package com.diamondboss.user.repository;

import com.diamondboss.user.pojo.PartnerInfoPojo;

/**
 * 合伙人信息数据访问
 * 
 * @author John
 * @since 2017-06-21
 *  
 */
public interface PartnerInfoMapper {

	/**
	 * 根据合伙人id查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public PartnerInfoPojo queryInfoByPartnerId(String partnerId);
	
}
