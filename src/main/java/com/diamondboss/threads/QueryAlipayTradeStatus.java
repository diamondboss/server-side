package com.diamondboss.threads;

import com.diamondboss.util.pay.aliPay.Alipay;
import org.apache.log4j.Logger;

/**
 * Created by liuzifu on 2017/7/2.
 */
public class QueryAlipayTradeStatus implements Runnable {
    private static final Logger logger = Logger.getLogger(QueryAlipayTradeStatus.class);
    private String tradeNo;

    public QueryAlipayTradeStatus(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            // TODO 查询订单状态

            // 决定是否去查询支付状态

            //交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
            // TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
            String tradeStatus =  Alipay.queryTradeStatus(tradeNo);
            logger.info(tradeStatus);

            // TODO 更新订单状态

            // TODO 决定是否进行派单


        } catch (InterruptedException e) {
            logger.error("查询支付宝支付状态线程error:"+e.getMessage());
        }
    }
}
