package com.diamondboss.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.diamondboss.order.repository.ParterWithDrawMapper;
import com.diamondboss.order.service.IParterWithDraw;
import com.diamondboss.util.pojo.ParterWithDrawPojo;
import com.diamondboss.util.vo.ParterDetailVo;

/**
 * 合伙人提现接口实现类
 * @author xzf
 *
 */
@Service(value = "parterWithDraw")
public class ParterWithDrawImpl implements IParterWithDraw {
	
	 @Resource
	 private ParterWithDrawMapper parterWithDrawMapper;

	@Override
	public List<ParterDetailVo> queryParterDetail(String parterId) {
		Map<String, String> map = new HashMap<>();
		map.put("parter_id", parterId);
		map.put("effective", "1");

		List<ParterWithDrawPojo> parterDetailList = parterWithDrawMapper.queryParterDetail(map);

		List<ParterDetailVo> parterDetails = new ArrayList<>();
		for (ParterWithDrawPojo parterWithDraw : parterDetailList) {
			ParterDetailVo ParterDetailVo = new ParterDetailVo();
			ParterDetailVo.setAmount(parterWithDraw.getAmount());
			ParterDetailVo.setOrderTime(parterWithDraw.getOrderTime());

			parterDetails.add(ParterDetailVo);
		}
		return parterDetails;
	}

}
