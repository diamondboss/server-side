package com.diamondboss.order.service;

import java.util.List;

import com.diamondboss.order.vo.CommunityResponseVo;
import com.diamondboss.util.vo.CommunityVo;

/**
 * 小区接口
 * @author xzf
 *
 */
public interface ICommunityService {

	/**
	 * 查询小区
	 * @return
	 */
	public List<CommunityVo> queryCommunitys();
	
	/**
	 * 根据小区名字，获得小区的Id
	 * @param communityName
	 * @return
	 */
	public CommunityResponseVo queryCommunityId(String communityName);
}
