package com.diamondboss.support.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.support.repository.FeedbackMapper;
import com.diamondboss.support.service.FeedbackService;
import com.diamondboss.support.vo.FeedbackVo;

/**
 * 意见反馈
 * 
 * @author John
 * @since 2017-07-13
 *  
 */
@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackMapper feedbackMapper;
	
	/**
	 * 提交反馈
	 */
	@Override
	public void submitFeedback(FeedbackVo vo){
		
		feedbackMapper.insertFeedbac(vo);
	}
}
