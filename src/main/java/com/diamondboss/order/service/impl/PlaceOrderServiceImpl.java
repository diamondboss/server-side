package com.diamondboss.order.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.pojo.RaiseNumberPojo;
import com.diamondboss.order.repository.PlaceOrderMapper;
import com.diamondboss.order.service.PlaceOrderService;
import com.diamondboss.order.vo.AlipayOrderSubmitVo;
import com.diamondboss.order.vo.PartnerClientVo;
import com.diamondboss.util.pay.aliPay.Alipay;
import com.diamondboss.util.pay.weChatPay.WXPay;
import com.diamondboss.util.pay.weChatPay.WXPayDto;
import com.diamondboss.util.push.getui.PushList;
import com.diamondboss.util.tools.PropsUtil;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.util.tools.UUIDUtil;

/**
 * 用户下单
 * 
 * @author John
 * @since 2017-06-24
 *  
 */
@Service
public class PlaceOrderServiceImpl implements PlaceOrderService{
	
	private static final Logger log = Logger.getLogger(PlaceOrderServiceImpl.class);

	@Autowired
	public PlaceOrderMapper placeOrderMapper;
	
	/**
	 * 用户下单-指定合伙人
	 */
	@Override
	public boolean appointPartner(OrderUserPojo pojo){
		
		// 查询该合伙人是否满足条件
		if(checkOrderCountsOfPartner(pojo.getPartnerId(), pojo.getOrderDate())){
			// 如果不满足
			return false;
		}
		
		// 插入用户订单
//		OrderUserPojo pojo = vo.voToPojo(vo);
		pojo.setAmt(new BigDecimal(getAmtByPet(pojo.getVarieties(), pojo.getAge(),pojo.getDogFood())));
		
		int i = placeOrderMapper.insertUserOrder(pojo);
		if(i==0){
			return false;
		}
		return true;
		
	}
	
	
	/**
	 * 不指定合伙人
	 * @param vo
	 * @return true-下单成功 false-小区合伙人订单已满
	 */
	@Override
	public boolean randomPartner(OrderUserPojo pojo){
		
		// 查询小区允许下单总数
		if(cheakCommunityOrderNum(pojo.getCommunityId(), pojo.getOrderDate())){
			return false;
		}
		
		pojo.setAmt(new BigDecimal(getAmtByPet(pojo.getVarieties(), pojo.getAge(),pojo.getDogFood())));
		// 插入用户订单表
		int i = placeOrderMapper.insertUserOrder(pojo);
		
		if(i==0){
			return false;
		}
		//TODO：给用户所在小区所有的合伙人推送抢单通知
		
		//1.查询出所在小区所有的合伙人clientId
		List<PartnerClientVo> partnerClientList = placeOrderMapper.queryPartnerClient(pojo.getCommunityId());
		//2.调用个推向指定群组推通知方法
		PushList.pushListToUser(partnerClientList);
		
		return true;
	}
	
	/**
	 * 确认合伙人是否可以接受订单
	 * @param partnerId
	 * @param orderDate
	 * @return true--不可下单;false--可以下单
	 */
	private boolean checkOrderCountsOfPartner(String partnerId, String orderDate){
		
		if(StringUtils.isBlank(partnerId)){
			return true;
		}
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(partnerId), 
				PetConstants.ORDER_PARTNER_TABLE_PREFIX);

		// 查询合伙人的当日订单数量
		Map<String, Object> params = new HashMap<>();
		params.put("orderPartner", tableName);
		params.put("partnerId", partnerId);
		params.put("orderDate", orderDate);
		params.put("orderStatus", PetConstants.ORDER_STATUS_PAY_SUCCESS);
		int counts = placeOrderMapper.queryCountsByPartnerAndDate(params);// 当前订单数量
		
		int riseNo = placeOrderMapper.queryPartnerCondition(partnerId);// 可接受订单数量
		
		// 小于饲养上限，则为可用
		return counts < riseNo ? false : true;
	}

	/**
	 * 检查小区宠物的总数
	 * @param communityId
	 * @param orderDate
	 * @return false-允许下单/true-宠物已满不允许下单
	 */
	private Boolean cheakCommunityOrderNum(String communityId, String orderDate){
		
		if(StringUtils.isBlank(communityId)){
			return true;
		}
		
		// 根据小区id查询合伙人表获取小区宠物饲养上限
		List<RaiseNumberPojo> partnerlist = 
				placeOrderMapper.queryTotalByCommunityId(communityId);
		
		if(partnerlist==null || partnerlist.size() == 0){
			return true;
		}
		
		// 根据小区id查询用户表获取小区目前订单数量
		int num = 0;// 小区已接单数
		int total = 0;// 小区接单总数 
		
		for(RaiseNumberPojo i:partnerlist){
			
			total += Integer.valueOf(i.getorderNum());// 总数
			
			// 查询合伙人的当日订单数量
			String tableName = TableUtils.getOrderTableName(Long.valueOf(i.getId()), 
					PetConstants.ORDER_PARTNER_TABLE_PREFIX);
			Map<String, Object> params = new HashMap<>();
			params.put("orderPartner", tableName);
			params.put("partnerId", i.getId());
			params.put("orderDate", orderDate);
			num += 	placeOrderMapper.queryCountsByPartnerAndDate(params);
		}
		
		if(num < total){
			return false;
		}
		return true;		
	}
	
	/**
	 * 创建订单信息
	 * @param vo
	 */
	public AlipayOrderSubmitVo combinationOrderInfo(OrderUserPojo pojo){
		
		String notifyUrl = PropsUtil.getProperty("alipay.notifyUrl");
		
		//表Id，tableId。
		int tableId =  Integer.valueOf(pojo.getUserId()) / 100 + 1;
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(pojo.getUserId()),
				PetConstants.ORDER_USER_TABLE_PREFIX);
		
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("userId", pojo.getUserId());
		map.put("orderDate", LocalDate.now().toString());
		
		//订单主键ID
		String idKey = pojo.getId();

        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("呆萌博士-宠物托管费用");
        model.setSubject("呆萌博士-宠物托管费用");
        model.setOutTradeNo(UUIDUtil.makeTradeNo(tableId, idKey));// 订单编号
        model.setTimeoutExpress(PropsUtil.getProperty("alipay.timeoutExpress"));
        model.setTotalAmount(getAmtByPet(
				pojo.getVarieties(), pojo.getAge(), pojo.getDogFood()));
        
//        model.setTotalAmount("0.02");
        model.setProductCode(PropsUtil.getProperty("alipay.productCode"));
        String orderInfo = Alipay.getPreOrder(model, notifyUrl);
        
        AlipayOrderSubmitVo alipayOrderSubmitVo = new AlipayOrderSubmitVo();
        alipayOrderSubmitVo.setOrderInfo(model.getBody());
        alipayOrderSubmitVo.setOrderAmt(model.getTotalAmount());
        alipayOrderSubmitVo.setAlipaySign(orderInfo);
        
        log.info("支付宝签名：" + orderInfo);

	    return alipayOrderSubmitVo;
	}
	
	/**
	 * 微信支付-签名生成订单信息
	 * @param vo
	 */
	@Override
	public Map<String, Object> combinationOrderInfoWXPay(OrderUserPojo pojo){
		
		String notifyUrl = PropsUtil.getProperty("WXPay.notifyUrl");
		
		//表Id，tableId。
		int tableId =  Integer.valueOf(pojo.getUserId()) / 100 + 1;
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(pojo.getUserId()),
				PetConstants.ORDER_USER_TABLE_PREFIX);
		
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("userId", pojo.getUserId());
		map.put("orderDate", LocalDate.now().toString());
		
		//订单主键ID
		String idKey = pojo.getId();
		String value = UUIDUtil.makeTradeNo(tableId, idKey);
		WXPayDto wxPayDto = new WXPayDto();
		wxPayDto.setBody("呆萌博士-宠物托管费用");
		wxPayDto.setNotifyUrl(notifyUrl);
		wxPayDto.setOutTradeNo(value);
		wxPayDto.setOrderId(value);
		wxPayDto.setIp("220.112.121.93");
		wxPayDto.setFee(Integer.valueOf((new BigDecimal(getAmtByPet(
				pojo.getVarieties(), pojo.getAge(), pojo.getDogFood()))
				.multiply(new BigDecimal("100"))).setScale(0).toString()));
//		wxPayDto.setFee(1);
		Map<String, Object> resultMap = WXPay.sendPreOrder(wxPayDto);
		resultMap.put("outTradeNo", value);
		
		return resultMap;
				
	}
	
	
	private String getAmtByPet(String varieties, String age, String dogFood){
		
		Map<String, Object> param = new HashMap<>();
		
		param.put("varieties", varieties);
		param.put("age", age);
		String amt = placeOrderMapper.getAmtByPet(param);
		if(null == amt || "".equals(amt)){
			return "20";
		}
		if("0".equals(dogFood)){
			
			return "" + (new BigDecimal(amt).add(new BigDecimal("5")) );
			
		}
		return amt;
	}
	
}
