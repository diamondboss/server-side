package com.diamondboss.order.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.repository.CommunityMapper;
import com.diamondboss.order.repository.ParterInfoMapper;
import com.diamondboss.order.repository.ParterOrderMapper;
import com.diamondboss.order.repository.UserOrderServiceMapper;
import com.diamondboss.order.service.IOrderService;
import com.diamondboss.util.pojo.CommunityPojo;
import com.diamondboss.util.pojo.ParterInfoPojo;
import com.diamondboss.util.pojo.ParterOrderPojo;
import com.diamondboss.util.tools.SpinOffAddress;
import com.diamondboss.util.tools.TableUtils;
import com.diamondboss.util.vo.UserDetailVo;
import com.diamondboss.util.vo.UserOrderServiceVo;
import com.diamondboss.util.vo.UserOrdersServiceVo;

/**
 * 小区服务实现类
 * @author xzf
 *
 */
@Service(value = "orderService")
public class OrderServiceImpl implements IOrderService {

	@Autowired
	 private CommunityMapper communityMapper;
	 
	 @Autowired
	 private ParterInfoMapper parterInfoMapper;
	 
	 @Autowired
	 private ParterOrderMapper parterOrderMapper;
	 
	 @Autowired
	 private UserOrderServiceMapper userOrderServiceMapper;
	
	/**
	 * 根据小区ID，查询小区信息
	 */
	@Override
	public CommunityPojo queryCommunity(Long communityId) {
		return communityMapper.selectByPrimaryKey(communityId);
	}

	/**
	 * 根据小区ID，查询该小区有多少位合伙人
	 * @return
	 */
	@Override
	public Map<String, Integer> countParter(String subtitle) {
		Map<String, String> map = SpinOffAddress.getCountryMap(subtitle);
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("provinces" , map.get("provinces"));
		requestMap.put("city" , map.get("city"));
		requestMap.put("area" , map.get("area"));
		requestMap.put("street" , map.get("street"));
		requestMap.put("country" , map.get("country"));
		requestMap.put("effective", "1");
		
		List<ParterInfoPojo> parterInfos = parterInfoMapper.countParter(requestMap);
	
		int ParterNum = 0;
		for (ParterInfoPojo parterInfo : parterInfos) {
			ParterNum = ParterNum + Integer.valueOf(parterInfo.getRaisenumber());
		}
		
		Map<String, Integer> responseMap = new HashMap<>();
		responseMap.put("ParterNum", ParterNum);
		responseMap.put("ParterSize", parterInfos.size());
		
		return responseMap;
	}

	/**
	 * /**
	 * 根据小区ID，查询该小区有多少狗已预约
	 * @param subtitle
	 * @return
	 */
	@Override
	public int countParterOrder(String subtitle) {
		Map<String, String> map = SpinOffAddress.getCountryMap(subtitle);
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("provinces" , map.get("provinces"));
		requestMap.put("city" , map.get("city"));
		requestMap.put("area" , map.get("area"));
		requestMap.put("street" , map.get("street"));
		requestMap.put("country" , map.get("country"));
		requestMap.put("effective", "1");

//TODO		int countParterOrder = parterOrderMapper.countParterOrder(requestMap);
		
		return 0;
	}

	/**
	 * 查询用户的预约明细
	 * @param parter_id
	 * @return
	 */
	@Override
	public List<UserDetailVo> queryUserDetail(String parter_id) {
		Map<String, String> map = new HashMap<>();
		map.put("parter_id", parter_id);
		map.put("effective", "1");
		
		List<ParterOrderPojo>  userDetailList = parterOrderMapper.queryUserDetail(map);
		
		List<UserDetailVo> userDetails = new ArrayList<>();
		for (ParterOrderPojo parterOrder : userDetailList) {
			UserDetailVo userDetail = new UserDetailVo();
			userDetail.setOrderTime(parterOrder.getOrderTime());
			userDetail.setCreateTime(parterOrder.getCreateTime());
			userDetails.add(userDetail);
		}
		return userDetails;
	}
	
	/**
	 * 查询用户的实时订单
	 * @param userId
	 * @param orderDate
	 * @return
	 */
	public Map<String, Object> queryUserOrderService(String userId, String orderDate){
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(userId),
				PetConstants.ORDER_USER_TABLE_PREFIX);
		
		/*Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("orderDate", orderDate);
		map.put("tableName", tableName);
		
		UserOrderServiceVo userOrder= userOrderServiceMapper.queryUserOrderService(map);
		
		Map<String, Object> parmMap = new HashMap<>();
		parmMap.put("userId", userId);
		parmMap.put("partnerId", userOrder.getPartnerId());
		parmMap.put("orderDate", orderDate);
		parmMap.put("tableName", tableName);
		List<UserOrdersServiceVo> userOrders = userOrderServiceMapper.queryUserOrders(parmMap);*/
		
		Map<String, Object> responseMap = new HashMap<>();
		/*responseMap.put("userOrder", userOrder);
		responseMap.put("userOrders", userOrders);
		*/
		return responseMap;
	}

	@Override
	public void synchronizedPaymentResult(String orderId, Map<String, Objects> params) {
		//TODO 如何判定订单属于哪张表
		//更新支付结果到对应的表的记录中

		//如果为支付成功，还要继续出发推送任务
	}

}
