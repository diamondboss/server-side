package com.diamondboss.timer.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.pojo.UserOrderListPojo;
import com.diamondboss.order.repository.DistributeOrderMapper;
import com.diamondboss.order.repository.GrabOrderMapper;
import com.diamondboss.order.repository.PlaceOrderMapper;
import com.diamondboss.order.vo.PartnerClientVo;
import com.diamondboss.timer.repository.AutoAllocationOrderMapper;
import com.diamondboss.util.tools.TableUtils;

@Component
public class AutoAllocationOrderService {

	private static final Logger log = Logger.getLogger(AutoAllocationOrderService.class);

	@Autowired
	private GrabOrderMapper grabOrder;

	@Autowired
	private PlaceOrderMapper placeOrderMapper;
	
	@Autowired
	private AutoAllocationOrderMapper autoAllocationOrder;

	@Autowired
	private DistributeOrderMapper distributeOrderMapper;
	
	/**
	 * 自动派单
	 */
	@Scheduled(cron="0 0/5 *  * * ? ")
	public void auto() {

		// 扫描是否有没处理的用户订单
		log.info("扫描是否有没处理的用户订单");
		List<String> userList = autoAllocationOrder.scanUserOrder();

		if (userList == null || userList.size() == 0) {
			return;
		}

		// 
		Map<String, List<UserOrderListPojo>> orderMap = new HashMap<>();

		Map<String, Object> queryMap = new HashMap<>();
		String time = LocalDate.now().toString() + " " + 
				LocalTime.now().minusMinutes(30).withNano(0).toString();
		queryMap.put("time", time);
		
		for (int i = 0, len = userList.size(); i < len; i++) {

			queryMap.put("userId", userList.get(i));
			queryMap.put("userTable", TableUtils.getOrderTableName(
					Long.valueOf(userList.get(i)), PetConstants.ORDER_USER_TABLE_PREFIX));
			
			// 根据用户id查询未完成的订单
			List<UserOrderListPojo> userOrder = autoAllocationOrder.queryOrderByUserId(queryMap);
			if (userOrder == null || userOrder.size() == 0) {
				continue;
			}
			for (int j = 0; j < userOrder.size(); j++) {

				/* key小区id,value该小区的订单  */
				if (orderMap.containsKey(userOrder.get(j).getCommunityId())) {
					
					/* 如果key已存在 */
					List<UserOrderListPojo> orderList = orderMap.get(userOrder.get(j).getCommunityId());
					orderList.add(userOrder.get(j));
					orderMap.put(userOrder.get(j).getCommunityId(), orderList);

				} else {
					/* 如果key不存在 */
					List<UserOrderListPojo> orderList = new ArrayList<>();
					orderList.add(userOrder.get(j));
					orderMap.put(userOrder.get(j).getCommunityId(), orderList);
				}
			}
		}
		
		List<List<UserOrderListPojo>> orderList =  new ArrayList<>(orderMap.values());
		try {
			list2Str(orderList);
		} catch (Exception e) {
			log.error("");
		}
	}
	
	/**
	 * 锟斤拷锟竭程达拷锟斤拷
	 * @param list
	 * @param nThreads
	 * @return
	 * @throws Exception
	 */
	private void list2Str(List<List<UserOrderListPojo>> orderList) throws Exception {  
		
        if (orderList == null || orderList.isEmpty()) {  
            return;  
        }
        
        int nThreads = orderList.size();// 获取线程数量
  
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);  
        List<Future<String>> futures = new ArrayList<Future<String>>(nThreads);  
          
        for (int i = 0; i < nThreads; i++) {  
        	// 每个小区开一个线程
            final List<UserOrderListPojo> subList =  orderList.get(i);
            	Callable<String> task = new Callable<String>() {  
                @Override  
                public String call() throws Exception {  
                	
                	for(int i=0;i<subList.size();i++){
                		
                		// 获取可以接单的合伙人
            			 List<PartnerClientVo> partnerList =
            					 getPartnerList(subList.get(i).getCommunityId(), 
            							 subList.get(i).getOrderDate());

            			// 随机合伙人
            			Random random = new Random();
            			int n = random.nextInt(partnerList.size());
            			String partnerId = partnerList.get(n).getPartnerId();
            			
        				OrderUserPojo updatePojo = new OrderUserPojo();
        				updatePojo.setId(subList.get(i).getId());
        				updatePojo.setPartnerId(partnerId);
        				updatePojo.setPartnerName(partnerList.get(n).getPartnerName());
        				updatePojo.setOrderStatus(PetConstants.ORDER_STATUS_RECEIVED);
        				
        				String orderUser = TableUtils.getOrderTableName(
        						Long.valueOf(subList.get(i).getUserId()),
        						PetConstants.ORDER_USER_TABLE_PREFIX);
        				
        				updatePojo.setOrderUser(orderUser);
        				
        				try{
        					int updateNum = distributeOrderMapper.updateOrderUser(updatePojo);
        					if(updateNum == 0){
        						continue;
        					}
        				}catch(Exception e){
        					log.info("更新用户订单失败" + e.getMessage());
        					continue;
        				}
            			
        				// 插入合伙人订单
            			Map<String, Object> insertPartnerMap = new HashMap<>();
            			insertPartnerMap.put("userTable", updatePojo.getOrderUser());
            			insertPartnerMap.put("userId", updatePojo.getId());
            			insertPartnerMap.put("partnerId", partnerId);
            			insertPartnerMap.put("partnerName", partnerList.get(n).getPartnerName());
            			insertPartnerMap.put("partnerTable", 
            					TableUtils.getOrderTableName(Long.valueOf(partnerId),
            							PetConstants.ORDER_PARTNER_TABLE_PREFIX));

            			autoAllocationOrder.insertPartnerOrder(insertPartnerMap);
            			
            			// 更新用户登录表
            			grabOrder.updateUserLogin(subList.get(i).getUserId());

                    	
                	}
					return null;
                	
                }  
            };  
            futures.add(executorService.submit(task));  
        }  
          
//        for (Future<String> future : futures) {  
//        }  
        executorService.shutdown();  
          
        return;  
    }  

	/**
	 * 锟斤拷询小锟斤拷锟缴接碉拷锟侥合伙拷锟斤拷
	 * @param commId
	 * @return
	 */
	private List<PartnerClientVo> getPartnerList(String CommunityId, String orderDate){
		List<PartnerClientVo> partnerList = new ArrayList<>();

		List<PartnerClientVo> partners = 
				placeOrderMapper.queryPartnerClient(CommunityId);

		
		if (!CollectionUtils.isEmpty(partners)){
			for (PartnerClientVo pojo : partners){
				
					partnerList.add(pojo);
					if (!checkOrderCountsOfPartner(pojo.getPartnerId(), orderDate)) {
				}
			}

		}
		return partnerList;
	}
	
	/**
	 * 确锟较合伙拷锟斤拷锟角凤拷锟斤拷越锟斤拷芏锟斤拷锟�
	 * @param partnerId
	 * @param riseNo
	 * @return true--锟斤拷锟斤拷锟铰碉拷;false--锟斤拷锟斤拷锟铰碉拷
	 */
	private boolean checkOrderCountsOfPartner(String partnerId, String orderDate){
		
		if(StringUtils.isBlank(partnerId)){
			return true;
		}
		
		String tableName = TableUtils.getOrderTableName(Long.valueOf(partnerId), 
				PetConstants.ORDER_PARTNER_TABLE_PREFIX);
		
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", tableName);
		params.put("partnerId", partnerId);
		params.put("orderDate", orderDate);
		params.put("orderStatus", "4");
		
		int counts = placeOrderMapper.queryCountsByPartnerAndDate(params);
		int riseNo = placeOrderMapper.queryPartnerCondition(partnerId);
		
		return counts < riseNo ? false : true;
	}
	
}

