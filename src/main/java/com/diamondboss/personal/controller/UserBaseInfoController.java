package com.diamondboss.personal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.diamondboss.personal.service.IUserBaseInfoService;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.BaseInfoVo;

@Controller
@RequestMapping("/userBase")
public class UserBaseInfoController {
	private static Logger logger = LogManager.getLogger(UserBaseInfoController.class);
	
	@Autowired
	private IUserBaseInfoService userBaseInfo;
	
	/**
	 * 查询个人信息接口
	 */
	@ResponseBody
	@RequestMapping(value = "/query" ,method = RequestMethod.POST)
	private APPResponseBody queryBaseInfo(HttpServletRequest request){
		
		String phoneNum = request.getParameter("phoneNum");
		logger.info("查询个人信息:" + phoneNum);
		
		BaseInfoVo vo = userBaseInfo.queryBaseInfo(phoneNum);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(vo);
		app.setRetnCode(0);
		return app;
		
	}
	
	
	/**
	 * 编辑个人信息接口
	 */
	@ResponseBody
	@RequestMapping(value = "/edit" ,method = RequestMethod.POST)
	private APPResponseBody editBaseInfo(HttpServletRequest request){
		
		String phoneNum = request.getParameter("phoneNum");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		String remark = request.getParameter("remark");
		
		Map<String, String> map = new HashMap<>();
		
		map.put("phoneNum", phoneNum);
		map.put("name", name);
		map.put("age", age);
		map.put("sex", sex);
		map.put("address", address);
		map.put("remark", remark);
		
		int i = userBaseInfo.updateBaseInfo(map);
		
		APPResponseBody app = new APPResponseBody();
		
		if(i==1){
			app.setData("保存成功");
			app.setRetnCode(0);
		}else{
			app.setData("保存失败");
			app.setRetnCode(0);
		}
	
		return app;
		
	}
}
