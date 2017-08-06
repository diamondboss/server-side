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
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(pojo.getUserId()), 
				PetConstants.SMS_USER_TABLE_PREFIX);
		
		Map<String, String> map =  new HashMap<>();
		map.put("userId", pojo.getUserId());
		map.put("partnerId", pojo.getPartnerId());
		map.put("parrtnerName", pojo.getPartnerName());
		map.put("smsSource", pojo.getSmsSource());
		map.put("smsTypeId", pojo.getSmsTypeId());
		map.put("smsStatus", pojo.getSmsStatus());
		map.put("tableName", tableName);
		
		return smsCenterMapper.insertSmsCenter(map);
	}
	
	@Override
	public int insertSmsForPartner(SmsCenterPojo pojo) {
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(pojo.getPartnerId()), 
				PetConstants.SMS_PARTNER_TABLE_PREFIX);
		
		Map<String, String> map =  new HashMap<>();
		map.put("userId", pojo.getUserId());
		map.put("partnerId", pojo.getPartnerId());
		map.put("parrtnerName", pojo.getPartnerName());
		map.put("smsSource", pojo.getSmsSource());
		map.put("smsTypeId", pojo.getSmsTypeId());
		map.put("smsStatus", pojo.getSmsStatus());
		map.put("tableName", tableName);
		
		return smsCenterMapper.insertSmsCenterForPartner(map);
	}

	@Override
	public int queryNewSmsForUser(String userId) {
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(userId), 
				PetConstants.SMS_USER_TABLE_PREFIX);

		Map<String, String> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("userId",userId);
		
		return smsCenterMapper.queryNewSmsForUser(map);
	}
	
	@Override
	public int queryNewSmsForPartner(String partnerId) {
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(partnerId), 
				PetConstants.SMS_PARTNER_TABLE_PREFIX);

		Map<String, String> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("partnerId",partnerId);
		
		return smsCenterMapper.queryNewSmsForPartner(map);
	}

	@Override
	public List<SmsQueryListVo> querySmsListForUser(String userId) {
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(userId), 
				PetConstants.SMS_USER_TABLE_PREFIX);

		Map<String, String> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("userId",userId);
		
		return smsCenterMapper.querySmsListForUser(map);
	}
	
	@Override
	public List<SmsQueryListVo> querySmsListForPartner(String partnerId) {
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(partnerId), 
				PetConstants.SMS_PARTNER_TABLE_PREFIX);

		Map<String, String> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("partnerId",partnerId);
		
		return smsCenterMapper.querySmsListForPartner(map);
	}

	@Override
	public int updateSmsStatusForUser(String userId) {
		String tableName = TableUtils.getOrderTableName(Long.valueOf(userId), 
				PetConstants.SMS_USER_TABLE_PREFIX);
		
		Map<String, String> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("userId",userId);
		
		return smsCenterMapper.updateSmsStatusForUser(map);
	}
	
	@Override
	public int updateSmsStatusForPartner(String partnerId) {
		String tableName = TableUtils.getOrderTableName(Long.valueOf(partnerId), 
				PetConstants.SMS_PARTNER_TABLE_PREFIX);
		
		Map<String, String> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("partnerId",partnerId);
		
		return smsCenterMapper.updateSmsStatusForPartner(map);
	}

}
