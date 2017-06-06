package com.diamondboss.order.repository;

import java.util.Map;

import com.diamondboss.util.vo.UserOrderServiceVo;

public interface UserOrderServiceMapper {

	
	UserOrderServiceVo queryUserOrderService(Map<String, String> map);
}
