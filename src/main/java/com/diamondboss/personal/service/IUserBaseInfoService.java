package com.diamondboss.personal.service;

import java.util.Map;

import com.diamondboss.util.vo.BaseInfoVo;

public interface IUserBaseInfoService {

	BaseInfoVo queryBaseInfo(String phoneNum);
	
	Integer updateBaseInfo(Map<String, String> map);
}
