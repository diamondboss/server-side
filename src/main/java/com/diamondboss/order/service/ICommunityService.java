package com.diamondboss.order.service;

import java.util.List;

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
}
