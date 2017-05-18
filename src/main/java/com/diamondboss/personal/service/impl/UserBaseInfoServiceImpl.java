package com.diamondboss.personal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.personal.repository.UserBaseInfoMapper;
import com.diamondboss.personal.service.IUserBaseInfoService;
import com.diamondboss.util.tools.UUIDUtil;
import com.diamondboss.util.vo.BaseInfoVo;

@Service
public class UserBaseInfoServiceImpl implements IUserBaseInfoService{

	@Autowired
	private UserBaseInfoMapper userBaseInfo;
	
	@Override
	public BaseInfoVo queryBaseInfo(String phoneNum) {
		
		// 如果电话号为空则返回
		if(phoneNum == null || "".equals(phoneNum)){
			return null;
		}
		
		// 查询
		BaseInfoVo vo = userBaseInfo.queryBaseInfo(phoneNum);
		if(vo != null){
			return vo;
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("phoneNum", phoneNum);
		
		String name = UUIDUtil.getOrderIdByUUID();
		map.put("name", name);
		
		BaseInfoVo newVo = new BaseInfoVo();
		newVo.setName(name);
				
				
		userBaseInfo.insertBaseInfo(map);
		
		return newVo;
	}

	public Integer updateBaseInfo(Map<String, String> map){
		
		int i = userBaseInfo.updateBaseInfo(map);
		
		if(i!=0){
			return 1;
		}
		return 0;
	}
	
}
