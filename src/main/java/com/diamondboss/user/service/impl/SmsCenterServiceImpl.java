package com.diamondboss.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.user.pojo.SmsCenterPojo;
import com.diamondboss.user.repository.SmsCenterMapper;
import com.diamondboss.user.service.SmsCenterService;
import com.diamondboss.user.vo.SmsQueryListVo;
import com.diamondboss.util.tools.TableUtils;

public class SmsCenterServiceImpl implements SmsCenterService {
	
	@Autowired
	private SmsCenterMapper smsCenterMapper;

	@Override
	public int insertSmsForUser(SmsCenterPojo pojo) {
		return 0;
	}

	@Override
	public int queryNewSms(String userId) {
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(userId), 
				PetConstants.SMS_USER_TABLE_PREFIX);

		Map<String, String> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("userId",userId);
		
		return smsCenterMapper.queryNewSms(map);
	}

	@Override
	public List<SmsQueryListVo> querySmsListForUser(String userId) {
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(userId), 
				PetConstants.SMS_USER_TABLE_PREFIX);

		Map<String, String> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("userId",userId);
		
		return smsCenterMapper.querySmsList(map);
	}

	@Override
	public int updateSmsStatusForUser(String userId) {
		String tableName = TableUtils.getOrderTableName(Long.valueOf(userId), 
				PetConstants.SMS_USER_TABLE_PREFIX);
		
		Map<String, String> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("userId",userId);
		
		return 0;
	}

}
