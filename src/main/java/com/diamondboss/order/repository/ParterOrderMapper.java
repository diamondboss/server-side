package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

import com.diamondboss.util.pojo.ParterOrderPojo;
import com.diamondboss.util.vo.PartnerOrderVo;

public interface ParterOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ParterOrderPojo record);

    int insertSelective(ParterOrderPojo record);

    ParterOrderPojo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ParterOrderPojo record);

    int updateByPrimaryKey(ParterOrderPojo record);
    
    /**
     * 计算某个小区的已预约宠物
     * @param parterInfoPojo
     * @return
     */
    int countParterOrder(ParterOrderPojo parterOrderPojo);
    
    /**
     * 查询用户的预约明细
     * @param communityId
     * @return
     */
    public List<ParterOrderPojo> queryUserDetail(Map<String, String> map);
    
    public List<PartnerOrderVo> queryTodayOrder(Map<String, Object> param);
}