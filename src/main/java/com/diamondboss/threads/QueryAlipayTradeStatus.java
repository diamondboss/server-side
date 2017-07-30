package com.diamondboss.threads;

import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.service.impl.DistributeOrderServiceImpl;
import com.diamondboss.payment.repository.PayConfirmMapper;
import com.diamondboss.util.pay.aliPay.Alipay;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Created by liuzifu on 2017/7/2.
 */
public class QueryAlipayTradeStatus implements Runnable {
    private static final Logger logger = Logger.getLogger(QueryAlipayTradeStatus.class);
    private String tradeNo;
    private String outTradeNo;
    private PayConfirmMapper payConfirmMapper;
    private DistributeOrderServiceImpl distributeOrderServiceImpl;

    public QueryAlipayTradeStatus(String tradeNo, String outTradeNo, PayConfirmMapper payConfirmMapper, 
    		DistributeOrderServiceImpl distributeOrderServiceImpl) {
        this.tradeNo = tradeNo;
        this.outTradeNo = outTradeNo;
        this.payConfirmMapper = payConfirmMapper;
        this.distributeOrderServiceImpl = distributeOrderServiceImpl;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            //查询订单状态
            String orderState = payConfirmMapper.queryOrderState(tradeNo);
            String tradeStatus = "";
       
            // 决定是否去查询支付状态
            if(PetConstants.ORDER_STATUS_UNPAID.equals(orderState)){
            	
            	 logger.info("查询系统数据库，得知订单状态未更新。交易号：" + tradeNo);
            	 //交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
                // TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
                tradeStatus =  Alipay.queryTradeStatus(tradeNo);
                logger.info(tradeStatus);
            }
            
            // TODO 更新订单状态
            Map<String, Object> sqlMap = new HashMap<>();
       	 	sqlMap.put("tradeNo", tradeNo);
       	 	sqlMap.put("outTradeNo", outTradeNo);	
            
            if("TRADE_SUCCESS".equals(orderState)){
            	logger.info("查询支付宝后，得知交易是成功的。交易号：" + tradeNo);
            	//更新数据库
            	sqlMap.put("orderStatus", PetConstants.ORDER_STATUS_PAY_SUCCESS);
            	sqlMap.put("payType", 0);
            	// TODO 异步通知状态入库
            	// TODO 更新表没写
                payConfirmMapper.updateOrderStatus(sqlMap);
            	//派单
                OrderUserPojo userPojo = payConfirmMapper.queryUserOrderById(sqlMap);
                // TODO 调起派单流程
                distributeOrderServiceImpl.DistributeOrder(userPojo);
            }

        } catch (InterruptedException e) {
            logger.error("查询支付宝支付状态线程error:"+e.getMessage());
        }
    }
}
