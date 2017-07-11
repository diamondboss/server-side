package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;
import com.diamondboss.util.vo.PartnerOrderVo;

public interface ParterOrderMapper {
    int deleteByPrimaryKey(Long id); 
    
    public List<PartnerOrderVo> queryTodayOrder(Map<String, Object> param);
}