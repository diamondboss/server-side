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
}
