package com.diamondboss.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.user.vo.RequestTokenVo;
import com.diamondboss.util.pojo.RYGetTokenReturnInfo;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 融云前后台相关接口
 * @author xzf
 *
 */
@Controller
@RequestMapping("/rongyun")
public class RongYuController {
	
	@Autowired
	private ISendMsgService sendMsgService; 
	
	/**
	 * 获取融云token
	 * @param vo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/smsSendOfToken", method = RequestMethod.POST)
	public APPResponseBody login(@RequestBody RequestTokenVo vo, 
			HttpServletRequest request){
		APPResponseBody app =  new APPResponseBody();
		
		RYGetTokenReturnInfo ryGetTokenReturnInfo = sendMsgService.getToken(vo.getUserId(), "我", 
				"http://zfxue-test.oss-cn-shanghai.aliyuncs.com/dbmap/map1.jpg");
		app.setRetnCode(0);
		app.setRetnDesc("发送成功");
		app.setData(ryGetTokenReturnInfo);
		
		return app;
	}	
}
