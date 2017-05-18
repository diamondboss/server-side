package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.util.pojo.ParterWithDrawPojo;

public interface ParterWithDrawMapper {

	/**
     * 查询合伙人提现明细
     * @param communityId
     * @return
     */
    List<ParterWithDrawPojo> queryParterDetail(Map<String, String> map);
		
}