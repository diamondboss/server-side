package com.diamondboss.user.repository;

import java.util.List;

import com.diamondboss.user.pojo.PartnerInfoPojo;
import com.diamondboss.user.vo.ResponsePartnerOfCommunityVo;

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
	
	/**
	 * 根据小区名字，返回该小区的所有合伙人
	 * @param communityName
	 * @return
	 */
	public List<ResponsePartnerOfCommunityVo> queryPartnerOfCommunityList(String communityName);
	
	
	
}
