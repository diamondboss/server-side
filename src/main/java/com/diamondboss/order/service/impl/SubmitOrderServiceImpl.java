package com.diamondboss.order.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.repository.ParterInfoMapper;
import com.diamondboss.util.pojo.OrderUserPojo;
import com.diamondboss.util.pojo.ParterInfoPojo;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.util.tools.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondboss.order.repository.SubmitOrderMapper;
import com.diamondboss.order.service.ISubmitOrderService;
import org.springframework.util.CollectionUtils;

@Service
public class SubmitOrderServiceImpl implements ISubmitOrderService{

	@Autowired
	private SubmitOrderMapper submitOrderMapper;

	@Autowired
	private ParterInfoMapper parterInfoMapper;
	
	/**
	 * 根据用户id查询已经预定的订单
	 * @param petId
	 */
	@Override
	public List<String> queryOrderByUser(String petId) {
		
		// 1.根据用户id查询已经预定的订单
		LocalDate today = LocalDate.now();		
		Map<String, String> map = new HashMap<>();
		map.put("today", today.toString());
		map.put("petId", petId);
		List<String> resultList = submitOrderMapper.queryOrderByUser(map);
		
		return resultList;
		
	}

	
	/**
	 * 根据用户id选择日期预约订单
	 */
	@Override
	public List submitOrderByUser() {
		// TODO 此处返回的类型需要跟前端确定
		List result = new ArrayList();
		// 1、 晚上10点至早5点不可下单
		int currentHour = LocalDateTime.now().getHour();
		if ((21 < currentHour && currentHour < 23) || (0 < currentHour && currentHour < 4)){
			return result;
		}

		// 2、查询该小区符合条件的社区合伙人
		String commId = "3000004";
		List<ParterInfoPojo> partnerList = getPartnerList(commId);

		// 黑名单过滤（暂时不做）

		// 3、随机合伙人
		if(partnerList == null || partnerList.size() == 0){
			return result;
		}
		Random random = new Random();
		int i = random.nextInt(partnerList.size());
		ParterInfoPojo partner = partnerList.get(i);

		Long partnerId = partner.getId();
		String maxRaise = partner.getRaisenumber();

		// 4、订单入库
		String partnerTableName = TableUtils.getOrderTableName(partnerId, PetConstants.ORDER_PARTNER_TABLE_PREFIX);
		String userTableName = TableUtils.getOrderTableName(partnerId, PetConstants.ORDER_USER_TABLE_PREFIX);
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", partnerTableName);
		params.put("partnerId", partnerId);
		params.put("orderDate", LocalDate.now());
		submitOrderMapper.insertPartnerOrder(params);

		params.put("tableName", userTableName);
		submitOrderMapper.insertUserOrder(params);

		// 5、再查询该合伙人是否超过订单数量,如果超过则将该订单置为无效
		if(!checkOrderCountsOfPartner(partnerId,maxRaise)){
			Map<String, Object> updateParams = new HashMap<>();
			updateParams.put("tableName", partnerTableName);
			updateParams.put("effective", "1");
//			updateParams.put("userId", userId);
			updateParams.put("partnerId", partnerId);
			updateParams.put("orderDate", partnerId);
//			updateParams.put("orderStatus", orderStatus);
			submitOrderMapper.updatePartnerOrder(updateParams);

			updateParams.put("tableName", userTableName);
			submitOrderMapper.updateUserOrder(updateParams);
		}
		return result;
	}

	/**
	 * 查询小区可接单的合伙人
	 * @param commId
	 * @return
	 */
	private List<ParterInfoPojo> getPartnerList(String commId){
		List<ParterInfoPojo> partnerList = new ArrayList();
		// 1、根据社区id查询有几个合伙人
		List<ParterInfoPojo> partners = parterInfoMapper.queryPartnerByCommunityId(commId);

		if (!CollectionUtils.isEmpty(partners)){
			for (ParterInfoPojo parterInfoPojo : partners){
				// 2、确认合伙人的订单是否已满
				Long id = parterInfoPojo.getId();
				if (checkOrderCountsOfPartner(id, parterInfoPojo.getRaisenumber())) {
					partnerList.add(parterInfoPojo);
				}
			}

		}
		return partnerList;
	}

	/**
	 * 确认合伙人是否可以接受订单
	 * @param partnerId
	 * @param riseNo
	 * @return
	 */
	private boolean checkOrderCountsOfPartner(Long partnerId, String riseNo){
		String tableName = TableUtils.getOrderTableName(partnerId, PetConstants.ORDER_PARTNER_TABLE_PREFIX);

		//查询合伙人的当日订单数量
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", tableName);
		params.put("partnerId", partnerId);
		params.put("currentDate", LocalDate.now());
		int counts = submitOrderMapper.queryCountsByPartnerAndDate(params);

		//小于饲养上限，则为可用
		return counts < Integer.valueOf(riseNo) ? true : false;
	}

	public List queryOrderTotal(){
		return null;
	}
	
	/********************************************************/
	/********************************************************/
	/********************************************************/
	/********************************************************/
	/********************************************************/
	
	// TODO
	/**
	 * 用户下单
	 * @param param
	 */
	public void submitOrder(Object param){
		// 检查小区宠物总数
		if(cheakCommunityOrderNum("")){
			return; 
		}
		
		// 插入订单（用户）表
		OrderUserPojo orderUser = new OrderUserPojo();
		/*orderUser.setId();
		orderUser.setReceiveTime(receiveTime);
		orderUser.setReturnTime(returnTime);
		orderUser.setPetName(petName);
		orderUser.setSex(sex);
		orderUser.setAge(age);
		orderUser.setPhone(phone);
		orderUser.setUserName(userName);
		orderUser.setRemark(remark);
		orderUser.userId();
		orderUser.partnerId();
		orderUser.setOrderDate(orderDate);
		orderUser.setOrderStatus(orderStatus);
		orderUser.setAmt(amt);
		orderUser.setOrderUser(orderUser);*/
		
		int insertResult = 0;
		try{
			insertResult = submitOrderMapper.insertOrderUser(orderUser);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		// 如果插入失败,则查询该订单，分支判断
		if(!(insertResult > 0)){
			
		}
		
		// 检查小区宠物总数
		if(cheakCommunityOrderNum("")){
			return; 
		}
		
		return;// 调用支付
	}
	
	/**
	 * 检查小区宠物的总数
	 * @param userId
	 * @return false-允许下单/true-宠物已满不允许下单
	 */
	private Boolean cheakCommunityOrderNum(String community){
		
		// 根据小区id查询合伙人表获取小区宠物饲养上限
		int total = submitOrderMapper.queryTotalByCommunityId(community);
		
		// 根据小区id查询用户表获取小区目前订单数量
		int num = 3;// TODO
		
		if(num < total){
			return false;
		}
		
		return true;
		
		
	}
	
	/**
	 * 付款完成更新订单
	 * @param param
	 */
	public void paymentUpdateOrder(Object param){
		
		String outTradeNo = "123";
		
		Map<String, Object> map = UUIDUtil.getInfoFromTradeNo(outTradeNo);
		
		// 更新用户登录表(下单时间、下单数量、下单数量+1)
		
		// 更新订单表
		
		// 检查合适的合伙人
		List partnerList = getPartnerList("");
		
		if(partnerList == null || partnerList.size() ==0){
			// 不做处理等待轮询5
		}
		
		
		// 推送对应合伙人
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
