package com.diamondboss.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.repository.DistributeOrderMapper;
import com.diamondboss.order.repository.PlaceOrderMapper;
import com.diamondboss.order.service.DistributeOrderService;
import com.diamondboss.order.vo.PartnerClientVo;
import com.diamondboss.order.vo.SendNotifySmsInfoVo;
import com.diamondboss.payment.service.impl.PayConfirmServiceImpl;
import com.diamondboss.user.pojo.PartnerInfoPojo;
import com.diamondboss.user.service.PartnerInfoService;
import com.diamondboss.user.service.UserLoginService;
import com.diamondboss.util.pay.aliPay.Alipay;
import com.diamondboss.util.pay.weChatPay.WXPay;
import com.diamondboss.util.pay.weChatPay.WXPayReFundDTO;
import com.diamondboss.util.push.getui.PushList;
import com.diamondboss.util.push.getui.PushToSingle;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.tools.PropsUtil;
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
	
	private static final Logger logger = Logger.getLogger(DistributeOrderServiceImpl.class);
	
	/**
	 * 订单分配
	 */
	@Override
	public void DistributeOrder(OrderUserPojo pojo){
		
		logger.info("进入订单分配");
		
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
		
		
		logger.info("进入指定合伙人--订单分配");
		//组装发送通知短信Vo
		SendNotifySmsInfoVo sendSmsInfoToPartner = new SendNotifySmsInfoVo();
		sendSmsInfoToPartner.setUserName(pojo.getUserName());
		sendSmsInfoToPartner.setPartnerName(pojo.getPartnerName());
		sendSmsInfoToPartner.setOrderDate(pojo.getOrderDate());
		
		SendNotifySmsInfoVo sendSmsInfoToUser = new SendNotifySmsInfoVo();
		sendSmsInfoToUser.setUserName(pojo.getUserName());
		sendSmsInfoToUser.setPartnerName(pojo.getPartnerName());
		sendSmsInfoToUser.setOrderDate(pojo.getOrderDate());
		
		// 检查合伙人是否满足要求
		if(checkOrderCountsOfPartner(pojo.getPartnerId(), 
				pojo.getOrderDate())){
			
			logger.info("合伙人不满足");
			// 支付宝或微信退款
			if(StringUtils.contains(pojo.getPayType(), "0")){
				logger.info("调起支付宝退款");
				Alipay.refund(pojo.getTradeNo());
			}else if(StringUtils.contains(pojo.getPayType(), "1")){
				logger.info("调起微信退款");
				WXPayReFundDTO dto = new WXPayReFundDTO();
				dto.setOutTradeNo(pojo.getOutTradeNo());
				dto.setTotalFee(pojo.getAmt().multiply(new BigDecimal(100)));
				dto.setRefundFee(pojo.getAmt().multiply(new BigDecimal(100)));
				dto.setNotifyUrl(PropsUtil.getProperty("WXPay.refund"));
				//TODO 微信退款
				WXPay.refund(dto);
			}
			// APP推送用户
			Map<String, String> map = new HashMap<String, String>();
			map.put("CID", userLoginService.selectUserClientId(pojo.getUserId()));
			map.put("tiele", "订单派送失败");
			map.put("text", "抱歉，您的订单派送失败！");
			map.put("url", "http://www.baidu.com");
			
			//PushToSingle.pushToSingle(map);
			// 短信推送用户（订单没有匹配成功的短信）
			sendSmsInfoToUser.setPhone(pojo.getPhone());
			logger.info("发送短信，用户手机号：" + pojo.getPhone());
			sendMsgService.sendNotifyMsg(sendSmsInfoToUser, 0);
			
		}else{
			
			logger.info("进入指定合伙人--订单分配--满足要求--插入合伙人订单");
			
			// 满足-插入合伙人订单表;更新用户订单
			String orderPartner = TableUtils.getOrderTableName(Long.valueOf(pojo.getPartnerId()),
					PetConstants.ORDER_PARTNER_TABLE_PREFIX);
			
			
			pojo.setOrderPartner(orderPartner);
			
			logger.info("合伙人表名：" + pojo.getOrderPartner());
			logger.info("品种：" + pojo.getVarieties());
			try{
				distributeOrderMapper.insertOrderPartner(pojo);

				OrderUserPojo updatePojo = new OrderUserPojo();
				updatePojo.setId(pojo.getId());
				updatePojo.setPartnerId(pojo.getPartnerId());
				updatePojo.setOrderStatus(PetConstants.ORDER_STATUS_RECEIVED);
				
				String orderUser = TableUtils.getOrderTableName(Long.valueOf(pojo.getUserId()),
						PetConstants.ORDER_USER_TABLE_PREFIX);
				
				updatePojo.setOrderUser(orderUser);
				
				logger.info("用户表名：" + orderUser);
				
				distributeOrderMapper.updateOrderUser(updatePojo);
				logger.info("进入指定合伙人--订单分配--更新数据库订单信息成功");
			}catch(Exception e){
				logger.info(e.getMessage());
			}
			// APP推送用户/合伙人
			//Map<String, String> map = new HashMap<String, String>();
			/*String cid = userLoginService.selectUserClientId(pojo.getUserId());
			map.put("CID = ", cid);
			logger.info("进入指定合伙人--订单分配--CID：" + cid);
			map.put("tiele", "您有新的订单");
			map.put("text", "你有新的订单产生，点击查看");
			map.put("url", "http://www.baidu.com");*/
			
			//PushToSingle.pushToSingle(map);
			
			//查询到合伙人的手机号
			PartnerInfoPojo partnerInfoPojo = partnerInfoService.queryPhoneOfPartner(pojo.getPartnerId());
			// 短信推送合伙人
			sendSmsInfoToPartner.setPhone(partnerInfoPojo.getPhoneNumber());
			logger.info("发送短信，合伙人手机号：" + partnerInfoPojo.getPhoneNumber());
			sendMsgService.sendNotifyMsg(sendSmsInfoToPartner, 1);
			
			sendSmsInfoToUser.setPhone(pojo.getPhone());
			logger.info("发送短信，用户手机号：" + pojo.getPhone());
			sendMsgService.sendNotifyMsg(sendSmsInfoToUser, 0);
			
		}
		
	}
	
	/**
	 * 不指定合伙人派单
	 */
	private void randomPartner(OrderUserPojo pojo){
		
		logger.info("进入不指定合伙人--订单分配");
		
		// 查询合适的合伙人
		getPartnerList(pojo.getCommunityId(), pojo.getOrderDate());
		
		// APP推送合伙人抢单信息
		//1.查询出所在小区所有的合伙人clientId
		List<PartnerClientVo> partnerClientList = placeOrderMapper.queryPartnerClient(pojo.getCommunityId());
		//2.调用个推向指定群组推通知方法
		//PushList.pushListToUser(partnerClientList);
	}

	/**
	 * 确认合伙人是否可以接受订单
	 * @param partnerId
	 * @param riseNo
	 * @return true--不可下单;false--可以下单
	 */
	private boolean checkOrderCountsOfPartner(String partnerId, String orderDate){
		
		logger.info("检查合伙人是否满意要求");
		if(StringUtils.isBlank(partnerId)){
			return true;
		}
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(partnerId), 
				PetConstants.ORDER_PARTNER_TABLE_PREFIX);

		logger.info("tableName:" + tableName);
		logger.info("partnerId:" + partnerId);
		logger.info("orderDate:" + orderDate);
		
		// 查询合伙人的当日订单数量
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", tableName);
		params.put("partnerId", partnerId);
		params.put("orderDate", orderDate);
		params.put("orderStatus", "2");
		
		int counts = placeOrderMapper.queryCountsByPartnerAndDate(params);// 当前订单数量
		logger.info("counts:" + counts);
		int riseNo = placeOrderMapper.queryPartnerCondition(partnerId);// 可接受订单数量
		logger.info("riseNo:" + riseNo);
		logger.info("合伙人订单情况：" + String.valueOf(counts) +  "/" + String.valueOf(riseNo));
		
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
