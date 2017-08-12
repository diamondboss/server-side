package com.diamondboss.order.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.repository.CancelOrderMapper;
import com.diamondboss.order.service.CancelOrderService;
import com.diamondboss.order.vo.CancelOrderVo;
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

	@Autowired
	private CancelOrderMapper cancelOrder;
	
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
			
		}else if("1".equals(pojo.getPayType())){
			
			// 微信退款
		}
		
		// 短信/消息推送合伙人
		if(StringUtils.isNotBlank(pojo.getPartnerId())){
			
		}
		
		// 消息推送
		
	}
	
}
