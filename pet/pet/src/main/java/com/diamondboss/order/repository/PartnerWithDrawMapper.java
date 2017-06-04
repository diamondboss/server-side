package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.util.pojo.PartnerWithDrawPojo;

public interface PartnerWithDrawMapper {

	/**
     * 查询合伙人提现明细
     * @param map
     * @return
     */
    List<PartnerWithDrawPojo> queryPartnerDetail(Map<String, String> map);
		
}