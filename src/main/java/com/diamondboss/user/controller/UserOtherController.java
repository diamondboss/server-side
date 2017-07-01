package com.diamondboss.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.user.vo.UserFeedBackVo;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 用户其他非业务交易接口
 * 
 * @author 
 * @since 2017-06-19
 *  
 */
@Controller
@RequestMapping("/userOther")
public class UserOtherController {
	
	private static Logger logger = Logger.getLogger(UserOtherController.class);
	
	@ResponseBody
	@RequestMapping(value = "/userFeedBack", method = RequestMethod.POST)
	public APPResponseBody userFeedBack(UserFeedBackVo vo,
			HttpServletRequest request){
		APPResponseBody app = new APPResponseBody();
		
		//1.将用户反馈信息插入到数据库中
		
		logger.info("用户反馈信息写入数据库成功！");
		app.setRetnCode(0);
		app.setData("");
		return app;
	}
}
