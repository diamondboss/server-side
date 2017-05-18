package com.diamondboss.personal.repository;

import java.util.Map;

import com.diamondboss.util.vo.BaseInfoVo;

public interface UserBaseInfoMapper {

	BaseInfoVo queryBaseInfo(String phoneNum);
	
	void insertBaseInfo(Map<String, String> param);
	
	int updateBaseInfo(Map<String, String> param);
}
