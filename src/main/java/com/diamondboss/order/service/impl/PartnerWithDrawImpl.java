package com.diamondboss.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.diamondboss.order.repository.PartnerWithDrawMapper;
import com.diamondboss.order.service.IPartnerWithDraw;
import com.diamondboss.util.pojo.PartnerWithDrawPojo;
import com.diamondboss.util.vo.PartnerDetailVo;

/**
 * 合伙人提现接口实现类
 * @author xzf
 *
 */
@Service(value = "partnerWithDraw")
public class PartnerWithDrawImpl implements IPartnerWithDraw {
	
	 @Resource
	 private PartnerWithDrawMapper partnerWithDrawMapper;

	@Override
	public List<PartnerDetailVo> queryPartnerDetail(String partnerId) {
		Map<String, String> map = new HashMap<>();
		map.put("parter_id", partnerId);
		map.put("effective", "1");

		List<PartnerWithDrawPojo> partnerDetailList = partnerWithDrawMapper.queryPartnerDetail(map);

		List<PartnerDetailVo> partnerDetails = new ArrayList<>();
		for (PartnerWithDrawPojo partnerWithDraw : partnerDetailList) {
			PartnerDetailVo partnerDetailVo = new PartnerDetailVo();
			partnerDetailVo.setAmount(partnerWithDraw.getAmount());
			partnerDetailVo.setOrderTime(partnerWithDraw.getOrderTime());

			partnerDetails.add(partnerDetailVo);
		}
		return partnerDetails;
	}

}
