package com.diamondboss.util.pay.aliPay.service;


/**
 * Created by liuzifu on 2017/6/21.
 */
public interface AlipayService {

    /**
     * 获取支付订单信息
     * @return
     */
    String getPayOrderInfo();

    /**
     * 解析支付结果（延签与解析）
     * @return
     */
    boolean analysisPayResult();

    /**
     * 接受支付通知
     */
    String acceptPayNotice();
}