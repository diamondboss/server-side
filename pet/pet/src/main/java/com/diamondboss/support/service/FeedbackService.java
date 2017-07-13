package com.diamondboss.support.service;

import com.diamondboss.support.vo.FeedbackVo;

/**
 * 意见反馈
 * 
 * @author John
 * @since 2017-07-13
 *  
 */
public interface FeedbackService {

	/**
	 * 提交反馈
	 */
	public void submitFeedback(FeedbackVo vo);
	
}
