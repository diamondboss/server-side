package com.diamondboss.support.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.util.vo.APPResponseBody;

/**
 * 版本控制
 * 
 * @author John
 * @since 2017-08-12
 *  
 */
@Controller
@RequestMapping("/version")
public class VersionController {

	@RequestMapping(value = "/getVersion", method = RequestMethod.POST)
	public @ResponseBody APPResponseBody getVersion(String s){
	
		Map<String, Object> param = new HashMap<>();
		param.put("version", "1.3.0");
		param.put("UpdateContent", "1.增加合伙人头像展示");
		
		
		APPResponseBody app = new APPResponseBody();
		app.setData(param);
		app.setRetnCode(0);
		return app;
	}
	
}
