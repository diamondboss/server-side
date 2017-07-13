package com.diamondboss.support.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.support.service.FeedbackService;
import com.diamondboss.support.vo.FeedbackVo;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 意见反馈
 * 
 * @author John
 * @since 2017-07-13
 *  
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody APPResponseBody submitFeedback(FeedbackVo vo){
		
		feedbackService.submitFeedback(vo);
		
		APPResponseBody app = new APPResponseBody();
		app.setData("保存成功");
		app.setRetnCode(0);
		return app;
	}
	
}
