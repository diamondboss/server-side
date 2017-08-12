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
		param.put("version", "3");
		param.put("UpdateContent", "1,修改bug1#2,修改bug2#3,修改bug3");
		param.put("isMustUpdate","0");
		
		
		APPResponseBody app = new APPResponseBody();
		app.setData(param);
		app.setRetnCode(0);
		return app;
	}
	
	public static void main(String[] args) {
		Map<String, Object> param = new HashMap<>();
		param.put("version", "3");
		param.put("UpdateContent", "1,修改bug1#2,修改bug2#3,修改bug3");
		param.put("isMustUpdate","0");
		
		System.out.println(param);
	}
	
}
