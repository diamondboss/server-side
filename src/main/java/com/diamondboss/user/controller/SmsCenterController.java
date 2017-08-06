package com.diamondboss.user.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.diamondboss.order.vo.OrderUserVo;
import com.diamondboss.user.vo.SmsQueryListVo;
import com.diamondboss.user.vo.SmsQueryNewVo;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 消息中心
 * @author xzf
 *
 */
@Controller
@RequestMapping("/smsCenter")
public class SmsCenterController {
	
	private static final Logger log = Logger.getLogger(SmsCenterController.class);
	
	@Autowired
	private com.diamondboss.user.service.SmsCenterService SmsCenterService;
	
	/**
	 * 查询是否有新消息
	 * 
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/queryNewSms", method = RequestMethod.POST)
	public APPResponseBody queryNewSms(OrderUserVo vo) {
		
		log.info("查询是否有新消息");
		APPResponseBody app = new APPResponseBody();
		
		if(vo.getUserId() == null){
			app.setRetnDesc("参数非法");
			app.setRetnCode(1);
			return app;
		}
		
		int count = 0;
		try{
			count = SmsCenterService.queryNewSms(vo.getUserId());
		}catch(Exception e){
			log.info("查询是否有新消息,异常：" + e.getMessage());
		}
		
		SmsQueryNewVo response = new SmsQueryNewVo();
		
		if(count > 0){
			response.setSmsNum(String.valueOf(count));
			response.setFlag("1");
		}else{
			response.setSmsNum(String.valueOf(count));
			response.setFlag("0");
		}
		
		app.setRetnCode(0);
		app.setData(response);
		return app;
	}
	
	/**
	 * 获取用户消息列表
	 * 
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/querySmsListOfUser", method = RequestMethod.POST)
	public APPResponseBody querySmsListOfUser(OrderUserVo vo) {
		log.info("获取用户消息列表");
		APPResponseBody app = new APPResponseBody();
		
		if(vo.getUserId() == null){
			app.setRetnDesc("参数非法");
			app.setRetnCode(1);
			return app;
		}
		
		List<SmsQueryListVo> userSmsList = SmsCenterService.querySmsListForUser(vo.getUserId());
		
		app.setRetnCode(0);
		app.setData(userSmsList);
		return app;
	}
	
	/**
	 * 更改用户消息的状态
	 * 
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/changeSmsStatus", method = RequestMethod.POST)
	public APPResponseBody changeSmsStatus(OrderUserVo vo) {
		log.info("更改用户消息的状态");
		APPResponseBody app = new APPResponseBody();
		
		if(vo.getUserId() == null){
			app.setRetnDesc("参数非法");
			app.setRetnCode(1);
			return app;
		}
		
		
		
		return app;
	}
}
