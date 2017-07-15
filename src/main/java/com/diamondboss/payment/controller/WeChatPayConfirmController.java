package com.diamondboss.payment.controller;

import com.diamondboss.payment.service.IPayConfirmService;
import com.diamondboss.util.pay.weChatPay.WXPayUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzifu on 2017/7/9.
 */
@Controller
@RequestMapping("/app/wxPay")
public class WeChatPayConfirmController {
    private static Logger logger = Logger.getLogger(WeChatPayConfirmController.class);
    @Autowired
    IPayConfirmService payConfirmService;

    @RequestMapping("/payConfirm")
    public void payConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        Map<String, String> ret = new HashMap<>();

        Document requestXML;
        String requestBody = WXPayUtils.getRequestBody(request);
        try {
            logger.debug("微信支付通知:" + requestBody);
            requestXML = DocumentHelper.parseText(requestBody); // 解析成XML
            ret = WXPayUtils.checkXmlAndPayResult(requestXML);
            if (!ret.get("return_code").equals("SUCCESS")) {
                WXPayUtils.callBack(response, ret);
                return;
            }
        } catch (Exception ex) {
            logger.debug("微信支付通知,处理出错"+ ex);
            WXPayUtils.callBack(response, ret);
            return;
        }

        // TODO: 2017/7/15 入库更新
        payConfirmService.wxpayConfirm(ret);
        // 订单支付已成功，获取订单信息，检查数据库中的订单状态等，并给微信服务器返回success
//        try {
//            ret.put("return_code", "SUCCESS");
//            ret.put("return_msg", "OK");
//
//            String orderId = ret.get("cysOrder");
//            String tradeNo = ret.get("wxOrder");
//            String openId = ret.get("openId");
//
//            // 检查redis中的订单状态
//            Map<String, String> orderMap;
//            try {
//                orderMap = redisClient.getMap(RedisKey.order(orderId));
//            } catch (Exception e) {
//                orderMap = null;
//                LogUtils.ERROR.debug("get order from redis failed due to", e);
//            }
//
//            if (orderMap == null || orderMap.isEmpty()) {
//                boolean isExisted = orderService.checkChargeOrder(orderId);
//                if (!isExisted) {
//                    WeChatUtils.callBack(resp, ret);
//                    log.debug("batch:{},App充值订单支付通知, orderId:{} does not exist", batch, orderId);
//                    return;
//                }
//            }
//
//            orderService.sendPaymentMqMsg(orderId, tradeNo, openId);
//            //orderService.recordPay(orderId, tradeNo);
//
//            try {
//                // 记录日志
//                Map<String, Object> logMap = new HashMap<>();
//                logMap.put("orderId", orderId);
//                logMap.put("openId", openId);
//                logMap.put("transactionId", tradeNo);
//                logMap.put("notifyTime", new Date());
//
//                YunLogger.yun.metric("User.Notify.WeChat", 0.0, logMap);
//            } catch (Exception e) {
//                LogUtils.ERROR.debug("record User.Notify.WeChat failed due to", e);
//            }
//
//            WeChatUtils.callBack(resp, ret);
//            return;
//        } catch (Exception ex) {
//            log.warn("batch:{}, App充值订单支付通知,保存数据出错", batch, ex);
//            WeChatUtils.callBack(resp, ret);
//            return;
//        }
    }
}
