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
import com.diamondboss.user.pojo.PartnerInfoPojo;
import com.diamondboss.user.pojo.SmsCenterPojo;
import com.diamondboss.user.service.PartnerInfoService;
import com.diamondboss.user.service.SmsCenterService;
import com.diamondboss.user.service.UserLoginService;
import com.diamondboss.util.pay.aliPay.Alipay;
import com.diamondboss.util.pay.weChatPay.WXPay;
import com.diamondboss.util.pay.weChatPay.WXPayReFundDTO;
import com.diamondboss.util.pojo.OutTradeNoPojo;
import com.diamondboss.util.push.getui.PushList;
import com.diamondboss.util.push.getui.PushToSingle;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.tools.PropsUtil;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.util.tools.UUIDUtil;
import com.diamondboss.wallet.service.PartnerRebateService;

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
	private PartnerRebateService partnerRebateService;
	
	@Autowired
	private SmsCenterService smsCenterService;
	
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
		
		logger.info("parnterId:" + pojo.getPartnerId());
		logger.info("parnterName:" + pojo.getPartnerName());
		if(StringUtils.isNotBlank(pojo.getPartnerId())){
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
		sendSmsInfoToPartner.setUserPhone(pojo.getPhone());
		sendSmsInfoToPartner.setPartnerName(pojo.getPartnerName());
		sendSmsInfoToPartner.setOrderDate(pojo.getOrderDate());
		
		SendNotifySmsInfoVo sendSmsInfoToUser = new SendNotifySmsInfoVo();
		sendSmsInfoToUser.setUserName(pojo.getUserName());
		sendSmsInfoToUser.setUserPhone(pojo.getPhone());
		sendSmsInfoToUser.setPartnerName(pojo.getPartnerName());
		sendSmsInfoToUser.setOrderDate(pojo.getOrderDate());
		
		//消息pojo
		SmsCenterPojo smsCenterPojo = new SmsCenterPojo();
		
		// 检查合伙人是否满足要求
		if(checkOrderCountsOfPartner(pojo.getPartnerId(), 
				pojo.getOrderDate())){
			
			logger.info("合伙人不满足");
			// 支付宝或微信退款
			if(StringUtils.contains(pojo.getPayType(), "0")){
				logger.info("调起支付宝退款");
				if(Alipay.refund(pojo.getTradeNo(), pojo.getAmt())){
					logger.info("支付宝退款成功！ ^_^ 订单号：" + pojo.getTradeNo());
					
					try{
						updateUserStateOfRefund(pojo);
					}catch(Exception e){
						logger.info("退款后【支付宝】更新用户订单状态异常：" + e.getMessage());
					}
				}else{
					logger.info("支付宝退款失败！订单号：" + pojo.getTradeNo());
				}
				
			}else if(StringUtils.contains(pojo.getPayType(), "1")){
				logger.info("调起微信退款");
				WXPayReFundDTO dto = new WXPayReFundDTO();
				dto.setUserId(pojo.getUserId());
				dto.setOutTradeNo(pojo.getOutTradeNo());
				dto.setTotalFee(pojo.getAmt().multiply(new BigDecimal(100)).setScale(0));
				dto.setRefundFee(pojo.getAmt().multiply(new BigDecimal(100)).setScale(0));
				dto.setNotifyUrl(PropsUtil.getProperty("WXPay.refund"));
				//TODO 微信退款
				Map result = WXPay.refund(dto);
				if("SUCCESS".equals(result.get("result"))){
					logger.info("微信退款成功");
					try{
						updateUserStateOfRefund(pojo);
					}catch(Exception e){
						logger.info("退款后【微信】更新用户订单状态异常：" + e.getMessage());
					}
					
					logger.info("进入指定合伙人--订单分配--更新数据库订单信息成功");
				}else{
					logger.info("微信退款失败");
				}
			}
			//用户的clientId
			String CID = userLoginService.selectUserClientId(pojo.getUserId());
			PushToSingle.pushSMSToClient(CID, "订单派送失败", "由于您的小区订单已满，本次下单失败。支付金额已返还。如有任何疑问，"
					+ "请及时联系我们帮您处理！", "1");
			
			// 短信推送用户（订单没有匹配成功的短信）
			logger.info("插入用户消息");
			smsCenterPojo.setUserId(pojo.getUserId());
			smsCenterPojo.setPartnerId(pojo.getPartnerId());
			smsCenterPojo.setPartnerName(pojo.getPartnerName());
			smsCenterPojo.setSmsSource("1");
			smsCenterPojo.setSmsTypeId("3000016");
			smsCenterPojo.setSmsStatus("1");
			
			smsCenterService.insertSmsForUser(smsCenterPojo);
			logger.info("插入用户消息成功！ ^_^");
			
			sendSmsInfoToUser.setPhone(pojo.getPhone());
			logger.info("发送短信，用户手机号：" + pojo.getPhone());
			sendMsgService.sendNotifyMsg(sendSmsInfoToUser, 1);
			
		}else{
			
			logger.info("进入指定合伙人--订单分配--满足要求--插入合伙人订单");
			
			OrderUserPojo updatePojo = new OrderUserPojo();
			updatePojo.setId(pojo.getId());
			updatePojo.setPartnerId(pojo.getPartnerId());
			updatePojo.setOrderStatus(PetConstants.ORDER_STATUS_RECEIVED);
			
			String orderUser = TableUtils.getOrderTableName(Long.valueOf(pojo.getUserId()),
					PetConstants.ORDER_USER_TABLE_PREFIX);
			
			updatePojo.setOrderUser(orderUser);
			logger.info("用户表名：" + orderUser);
			
			try{
				int i = distributeOrderMapper.updateOrderUser(updatePojo);
				if(i == 0){
					return;
				}
			}catch(Exception e){
				logger.info("更新订单信息异常。" + e.getMessage());
				return;
			}
			
			// 满足-插入合伙人订单表;更新用户订单
			String orderPartner = TableUtils.getOrderTableName(Long.valueOf(pojo.getPartnerId()),
					PetConstants.ORDER_PARTNER_TABLE_PREFIX);
			
			
			pojo.setOrderPartner(orderPartner);
			
			logger.info("合伙人表名：" + pojo.getOrderPartner());
			logger.info("品种：" + pojo.getVarieties());
			try{
				pojo.setOrderStatus("4");
				try{
					int i = distributeOrderMapper.insertOrderPartner(pojo);
					if(i == 0){
						return;
					}
				}catch(Exception e){
					logger.info("插入合伙人订单信息异常。" + e.getMessage());
					return;
				}
				
				logger.info("进入指定合伙人--订单分配--更新数据库订单信息成功");
			}catch(Exception e){
				logger.info(e.getMessage());
			}
			// APP推送用户/合伙人
			//用户的clientId
			String userCID = userLoginService.selectUserClientId(pojo.getUserId());
			PushToSingle.pushSMSToClient(userCID, "订单派送成功", "您的订单已派发成功，请及时查看。如有任何疑问，请及时联系我们帮您处理！", "0");
			
			//合伙人的clientId
			String partnerCID = userLoginService.selectPartnerClientId(pojo.getPartnerId());
			PushToSingle.pushSMSToClient(partnerCID, "订单派送成功", "您有新订单，请及时查看。如有任何疑问，请及时联系我们帮您处理！", "2");
			

			try{
				partnerRebateService.rebate(pojo, true);
			}catch(Exception e){
				logger.info("更新合伙人钱包金额异常。" + e.getMessage());
				logger.info(e.getMessage());
			}
			
			smsCenterPojo.setUserId(pojo.getUserId());
			smsCenterPojo.setPartnerId(pojo.getPartnerId());
			smsCenterPojo.setPartnerName(pojo.getPartnerName());
			smsCenterPojo.setSmsSource("1");
			smsCenterPojo.setSmsTypeId("3000015");
			smsCenterPojo.setSmsStatus("1");
			smsCenterService.insertSmsForUser(smsCenterPojo);
			
			//插入合伙人消息pojo
			SmsCenterPojo partnerPojo = new SmsCenterPojo();
			partnerPojo.setUserId(pojo.getUserId());
			partnerPojo.setPartnerId(pojo.getPartnerId());
			partnerPojo.setPartnerName(pojo.getPartnerName());
			partnerPojo.setSmsSource("1");
			partnerPojo.setSmsTypeId("3000018");
			partnerPojo.setSmsStatus("1");
			smsCenterService.insertSmsForPartner(partnerPojo);
			
			//查询到合伙人的手机号
			PartnerInfoPojo partnerInfoPojo = partnerInfoService.queryPhoneOfPartner(pojo.getPartnerId());
			// 短信推送合伙人
			sendSmsInfoToPartner.setPhone(partnerInfoPojo.getPhoneNumber());
			logger.info("发送短信，合伙人手机号：" + partnerInfoPojo.getPhoneNumber());
			sendMsgService.sendNotifyMsg(sendSmsInfoToPartner, 2);
			
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
		List<PartnerClientVo> list = getPartnerList(pojo.getCommunityId(), pojo.getOrderDate());
		
		logger.info("合适的合伙人数量："  + list.size());
		
		if(list == null || list.size() == 0){
			
			return;// 无合适合伙人,等待系统自动处理，插入异常信息
		}
		
		// APP推送合伙人抢单信息
		// 插入合伙人抢单表
		Map<String, Object> param = new HashMap<>();
		OutTradeNoPojo tradeNoPojo = UUIDUtil.getInfoFromTradeNo(pojo.getOutTradeNo());
		param.put("userKeyId",tradeNoPojo.getId());
		param.put("tableId",tradeNoPojo.getTableId());
		param.put("userTableId", 
				PetConstants.ORDER_USER_TABLE_PREFIX + tradeNoPojo.getTableId());
		
		logger.info("------" + param);
		for(PartnerClientVo i :list){
			
			param.put("partnerId", i.getPartnerId());
			param.put("partnerName", i.getPartnerName());
			param.put("partnerTableId", 
			TableUtils.getOrderTableName(Long.valueOf(i.getPartnerId()), 
					PetConstants.GRAB_ORDER_INFO_TABLE_PREFIX));
			try{
				distributeOrderMapper.insertGrabOrderInfo(param);
			}catch(Exception e){
				logger.info("插入抢单表异常：" + e.getMessage());
			}
		}
		
		//1.查询出所在小区所有的合伙人clientId
		//List<PartnerClientVo> partnerClientList = placeOrderMapper.queryPartnerClient(pojo.getCommunityId());
		// TODO 有问题 获取的合伙人可能不合适
		
		Map<String, String> toListMap = new HashMap<>();
		toListMap.put("title", "附近新单子......");
		toListMap.put("text", "您的附近有新单子，请注意查看。如有任何疑问，请及时联系我们帮您处理！");
		toListMap.put("type", "6");
		//2.调用个推向指定群组推通知方法
		PushList.pushListToUser(list, toListMap);
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
		params.put("orderStatus", "4");
		
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
	private List<PartnerClientVo> getPartnerList(String CommunityId, String orderDate){
		List<PartnerClientVo> partnerList = new ArrayList<>();
		logger.info("根据社区id查询有几个合伙人，社区ID：" + CommunityId);
		// 1、根据社区id查询有几个合伙人
		List<PartnerClientVo> partners = 
				placeOrderMapper.queryPartnerClient(CommunityId);

		
		logger.info("合伙人数量：" + partners.size());
		if (!CollectionUtils.isEmpty(partners)){
			for (PartnerClientVo pojo : partners){
				// 2、确认合伙人的订单是否已满
					partnerList.add(pojo);
					if (!checkOrderCountsOfPartner(pojo.getPartnerId(), orderDate)) {
				}
			}

		}
		return partnerList;
	}
	
	/**
	 * 退款后更新用户订单的状态为7：已退款
	 * @param pojo
	 */
	private void updateUserStateOfRefund(OrderUserPojo pojo){
		OrderUserPojo updatePojo = new OrderUserPojo();
		updatePojo.setId(pojo.getId());
		updatePojo.setPartnerId(pojo.getPartnerId());
		updatePojo.setOrderStatus(PetConstants.ORDER_STATUS_REFUND);
		
		String orderUser = TableUtils.getOrderTableName(Long.valueOf(pojo.getUserId()),
				PetConstants.ORDER_USER_TABLE_PREFIX);
		updatePojo.setOrderUser(orderUser);
		
		logger.info("用户表名：" + orderUser);
		
		distributeOrderMapper.updateOrderUser(updatePojo);
	}
	
}
