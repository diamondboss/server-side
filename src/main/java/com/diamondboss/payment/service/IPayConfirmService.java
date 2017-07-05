package com.diamondboss.payment.service;

import com.alipay.api.AlipayApiException;

import java.util.Map;

/**
 * Created by liuzifu on 2017/7/2.
 * 支付确认
 */

public interface IPayConfirmService {

    /**
     * 解析支付宝的同步支付返回结果
     * @param result
     * @return
     */
    boolean analysisAliPayResult(String result);

    /**
     *
     * @param requestParams
     * @return
     */
    String alipayConfirm(Map<String, String[]> requestParams) throws AlipayApiException;
    
    /**
     * 根据订单号，返回该订单的状态
     * @param tradeNo
     * @return
     */
    public String queryOrderState(String tradeNo);
}
