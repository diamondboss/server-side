package com.diamondboss.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.repository.DistributeOrderMapper;
import com.diamondboss.order.repository.PlaceOrderMapper;
import com.diamondboss.order.service.DistributeOrderService;
import com.diamondboss.order.vo.SendNotifySmsInfoVo;
import com.diamondboss.user.pojo.PartnerInfoPojo;
import com.diamondboss.user.service.PartnerInfoService;
import com.diamondboss.user.service.UserLoginService;
import com.diamondboss.util.pay.aliPay.Alipay;
import com.diamondboss.util.pay.weChatPay.WXPay;
import com.diamondboss.util.pay.weChatPay.WXPayReFundDTO;
import com.diamondboss.util.push.getui.PushToSingle;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.tools.TableUtils;

/**
 * 订单分配
 * 
 * @author John
 * @since 2017-06-26
 *  
 */
@Service("distributeOrder")
public class DistributeOrderServiceImpl implements DistributeOrderService{

	@Autowired
	private PlaceOrderMapper placeOrderMapper;
	
	@Autowired
	private DistributeOrderMapper distributeOrderMapper;
	
	@Autowired
	private ISendMsgService sendMsgService;
	
	@Autowired
	private PartnerInfoService partnerInfoService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	/**
	 * 订单分配
	 */
	@Override
	public void DistributeOrder(OrderUserPojo pojo){
		
		if(pojo == null){
			return;
		}
		
		if(pojo.getPartnerId() != null || !"".equals(pojo.getPartnerId())){
			appointPartner(pojo);
		}else{
			randomPartner(pojo);
		}
		
		
	}
	
	/**
	 * 指定合伙人派单
	 */
	private void appointPartner(OrderUserPojo pojo){
		
		//组装发送通知短信Vo
		SendNotifySmsInfoVo sendSmsInfo = new SendNotifySmsInfoVo();
		sendSmsInfo.setUserName(pojo.getUserName());
		sendSmsInfo.setPartnerName(pojo.getPartnerName());
		sendSmsInfo.setOrderDate(pojo.getOrderDate());
		
		// 检查合伙人是否满足要求
		if(checkOrderCountsOfPartner(pojo.getPartnerId(), 
				pojo.getOrderDate())){
			// 支付宝或微信退款
			if(StringUtils.contains(pojo.getPayType(), "0")){
				Alipay.refund(pojo.getTradeNo());
			}else if(StringUtils.contains(pojo.getPayType(), "1")){
				WXPayReFundDTO dto = new WXPayReFundDTO();
				dto.setOutTradeNo(pojo.getOutTradeNo());
				dto.setTotalFee(pojo.getAmt());
				dto.setRefundFee(pojo.getAmt());
				//TODO 微信退款
				WXPay.refund(dto);
			}
			// APP推送用户
			Map<String, String> map = new HashMap<String, String>();
			map.put("CID", userLoginService.selectUserClientId(pojo.getUserId()));
			map.put("tiele", "订单派送失败");
			map.put("text", "抱歉，您的订单派送失败！");
			map.put("url", "http://www.baidu.com");
			
			PushToSingle.pushToSingle(map);
			// 短信推送用户（订单没有匹配成功的短信）
			sendSmsInfo.setPhone(pojo.getPhone());
			sendMsgService.sendNotifyMsg(sendSmsInfo, 0);
		}else{
			
			// 满足-插入合伙人订单表;更新用户订单
			distributeOrderMapper.insertOrderPartner(pojo);
			
			OrderUserPojo updatePojo = new OrderUserPojo();
			updatePojo.setId(pojo.getId());
			updatePojo.setPartnerId(pojo.getPartnerId());
			updatePojo.setOrderStatus(PetConstants.ORDER_STATUS_RECEIVED);
			distributeOrderMapper.updateOrderUser(updatePojo);
			// APP推送用户/合伙人
			Map<String, String> map = new HashMap<String, String>();
			map.put("CID", userLoginService.selectUserClientId(pojo.getUserId()));
			map.put("tiele", "您有新的订单");
			map.put("text", "你有新的订单产生，点击查看");
			map.put("url", "http://www.baidu.com");
			
			PushToSingle.pushToSingle(map);
			
			//查询到合伙人的手机号
			PartnerInfoPojo partnerInfoPojo = partnerInfoService.queryPhoneOfPartner(pojo.getPartnerId());
			
			// 短信推送合伙人
			sendSmsInfo.setPhone(partnerInfoPojo.getPhoneNumber());
			sendMsgService.sendNotifyMsg(sendSmsInfo, 1);
		}
		
	}
	
	/**
	 * 不指定合伙人派单
	 */
	private void randomPartner(OrderUserPojo pojo){
		
		// 查询合适的合伙人
		getPartnerList(pojo.getCommunityId(), pojo.getOrderDate());
		
		// APP推送合伙人抢单信息
	}

	/**
	 * 确认合伙人是否可以接受订单
	 * @param partnerId
	 * @param riseNo
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
		params.put("tableName", tableName);
		params.put("partnerId", partnerId);
		params.put("orderDate", orderDate);
		int counts = placeOrderMapper.queryCountsByPartnerAndDate(params);// 当前订单数量
		
		int riseNo = placeOrderMapper.queryPartnerCondition(partnerId);// 可接受订单数量
		
		// 小于饲养上限，则为可用
		return counts < riseNo ? false : true;
	}

	/**
	 * 查询小区可接单的合伙人
	 * @param commId
	 * @return
	 */
	private List<PartnerInfoPojo> getPartnerList(String CommunityId, String orderDate){
		List<PartnerInfoPojo> partnerList = new ArrayList();
		// 1、根据社区id查询有几个合伙人
		List<PartnerInfoPojo> partners = null;
//				parterInfoMapper.queryPartnerByCommunityId(CommunityId);

		if (!CollectionUtils.isEmpty(partners)){
			for (PartnerInfoPojo pojo : partners){
				// 2、确认合伙人的订单是否已满
				if (!checkOrderCountsOfPartner(pojo.getId(), orderDate)) {
					partnerList.add(pojo);
				}
			}

		}
		return partnerList;
	}
	
}
