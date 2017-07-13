package com.diamondboss.support.repository;

import com.diamondboss.support.vo.FeedbackVo;

/**
 * 意见反馈
 * 
 * @author John
 * @since 2017-07-13
 *  
 */
public interface FeedbackMapper {

	/**
	 * 插入意见反馈表
	 * @param vo
	 * @return
	 */
	public int insertFeedbac(FeedbackVo vo);
	
}
