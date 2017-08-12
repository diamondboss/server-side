package com.diamondboss.order.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.repository.CancelOrderMapper;
import com.diamondboss.order.repository.DistributeOrderMapper;
import com.diamondboss.order.service.CancelOrderService;
import com.diamondboss.order.vo.CancelOrderVo;
import com.diamondboss.order.vo.SendNotifySmsInfoVo;
import com.diamondboss.user.pojo.PartnerInfoPojo;
import com.diamondboss.user.service.PartnerInfoService;
import com.diamondboss.util.pay.aliPay.Alipay;
import com.diamondboss.util.pay.weChatPay.WXPay;
import com.diamondboss.util.pay.weChatPay.WXPayReFundDTO;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.tools.PropsUtil;
import com.diamondboss.util.tools.TableUtils;

/**
 * 用户取消预约
 * 
 * @author John
 * @since 2017-08-10
 *  
 */
@Service
public class CancelOrderServiceImpl implements CancelOrderService{
	
	private static final Logger logger = Logger.getLogger(CancelOrderServiceImpl.class);

	@Autowired
	private CancelOrderMapper cancelOrder;
	
	@Autowired
	private DistributeOrderMapper distributeOrderMapper;
	
	@Autowired
	private ISendMsgService sendMsgService;
	
	@Autowired
	private PartnerInfoService partnerInfoService;
	
	/**
	 * 用户取消预约
	 * 
	 * @param vo
	 * @return
	 */
	@Override
	public boolean cancelOrder(CancelOrderVo vo) {

		// 1.根据订单编号跟用户id查询订单
		OrderUserPojo userOrder = queryOrder(vo);
		
		// 2.检查订单是否可以取消
		if(cheakOrder(userOrder)){
			
			// 3.取消预约
			cancel(userOrder);
			
		}
		
		return false;
	}

	/**
	 * 根据订单编号跟用户id查询订单
	 * 
	 * @param vo 取消预约订单编号
	 * @return 取消预约订单信息
	 */
	private OrderUserPojo queryOrder(CancelOrderVo vo){
		// 根据订单编号跟用户id查询订单
		Map<String, Object> param = new HashMap<>(); 
		param.put("userId", vo.getUserId());
		param.put("userTable", TableUtils.getOrderTableName(
				vo.getUserId(), PetConstants.ORDER_USER_TABLE_PREFIX));
		param.put("id", vo.getOrderId());
		OrderUserPojo pojo = cancelOrder.queryOrderById(param);
		
		return pojo;
	}
	
	/**
	 * 检查订单是否可以取消
	 * 
	 * @param pojo 取消预约订单信息
	 * @return true-可以取消;false-不可取消
	 */
	private boolean cheakOrder(OrderUserPojo pojo){
		
		// 如果订单状态不是2或4 不可取消
		if(!"2".equals(pojo.getOrderStatus())&&
				!"4".equals(pojo.getOrderStatus())){
			
			return false;
		}
		
		// 如果订单时间大于今日,可以取消
		String today = LocalDate.now().toString();
		
		if(today.compareTo(pojo.getOrderDate()) < 0){
			return true;
		}else if(today.compareTo(pojo.getOrderDate()) > 0){
			return false;
		}
		
		// 如果订单还有2个小时不可取消
		String time = LocalTime.now().plusHours(2).withNano(0).withSecond(0).toString();
		String[] orderTime = pojo.getReceiveTime().split(" ");
		if(time.compareTo(orderTime[1]) > 0){
			return false;
		}
		
		return false;
		
	}
	
	private void cancel(OrderUserPojo pojo){
		
		Map<String, Object> param = new HashMap<>(); 
		param.put("userId", pojo.getUserId());
		param.put("userTable", TableUtils.getOrderTableName(
				pojo.getUserId(), PetConstants.ORDER_USER_TABLE_PREFIX));
		param.put("id", pojo.getId());
		// 更新用户订单表
		int i = cancelOrder.updateUserOrderById(param);
		if(i==0){
			return;
		}
		
		// 更新用户登录表 订单数-1
		cancelOrder.updateUserLoginByUserId(param);
		
		if(StringUtils.isBlank(pojo.getPartnerId())){
			
			// 根据用户订单小区id查询小区下合伙人
			param.put("communityId", pojo.getCommunityId());
			List<String> partnerList = cancelOrder.queryPartnerId(param);
			
			if(partnerList == null || partnerList.size() ==0){
				return;
			}
			
			// 更新合伙人抢单表
			for(String partnerId:partnerList){
				param.put("partnerId", partnerId);
				param.put("partnerTable", TableUtils.getOrderTableName(
						partnerId, PetConstants.GRAB_ORDER_INFO_TABLE_PREFIX));
				cancelOrder.updateGrabOrderByPartnerId(param);
			}
			
		}else{
			param.put("outTradeNo", pojo.getOutTradeNo());
			param.put("partnerId", pojo.getPartnerId());
			param.put("partnerTable", TableUtils.getOrderTableName(
					pojo.getPartnerId(), PetConstants.ORDER_PARTNER_TABLE_PREFIX));
			// 更新合伙人订单表
			cancelOrder.updatePartnerOrder(param);
		}
		
		// 退款
		if("0".equals(pojo.getPayType())){
			// 支付宝退款
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
			
		}else if("1".equals(pojo.getPayType())){
			// 微信退款
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
		
		// 短信/消息推送合伙人
		if(StringUtils.isNotBlank(pojo.getPartnerId())){
			//查询到合伙人的手机号
			PartnerInfoPojo partnerInfoPojo = partnerInfoService.queryPhoneOfPartner(pojo.getPartnerId());
			// 短信推送合伙人
			SendNotifySmsInfoVo sendSmsInfoToPartner = new SendNotifySmsInfoVo();
			sendSmsInfoToPartner.setUserName(pojo.getUserName());
			sendSmsInfoToPartner.setPartnerName(pojo.getPartnerName());
			sendSmsInfoToPartner.setOrderDate(pojo.getOrderDate());
			sendSmsInfoToPartner.setPhone(partnerInfoPojo.getPhoneNumber());
			logger.info("发送短信，合伙人手机号：" + partnerInfoPojo.getPhoneNumber());
			sendMsgService.sendNotifyMsg(sendSmsInfoToPartner, 3);
		}
		
		// 消息推送
		
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
