package com.diamondboss.payment.controller;

import com.alibaba.fastjson.JSONObject;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.payment.service.IPayConfirmService;
import com.diamondboss.util.pay.aliPay.EnumAlipayResult;
import com.diamondboss.util.pay.weChatPay.WXPayUtils;
import com.diamondboss.util.pojo.OutTradeNoPojo;
import com.diamondboss.util.tools.UUIDUtil;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.CheckAliPayVo;
import com.diamondboss.util.vo.CheckWXPayResultVo;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            logger.info("微信支付通知:" + requestBody);
            requestXML = DocumentHelper.parseText(requestBody); // 解析成XML
            ret = WXPayUtils.checkXmlAndPayResult(requestXML);
            logger.info("微信支付通知解析结果：" + JSONObject.toJSONString(ret));
            if (!ret.get("return_code").equals("SUCCESS")) {
                WXPayUtils.callBack(response, ret);
                return;
            }
        } catch (Exception ex) {
            logger.info("微信支付通知,处理出错"+ ex);
            WXPayUtils.callBack(response, ret);
            return;
        }

        // TODO: 2017/7/15 入库更新
        //ret = payConfirmService.wxpayConfirm(ret);
        WXPayUtils.callBack(response, ret);
        return;
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
//                LogUtils.ERROR.info("get order from redis failed due to", e);
//            }
//
//            if (orderMap == null || orderMap.isEmpty()) {
//                boolean isExisted = orderService.checkChargeOrder(orderId);
//                if (!isExisted) {
//                    WeChatUtils.callBack(resp, ret);
//                    log.info("batch:{},App充值订单支付通知, orderId:{} does not exist", batch, orderId);
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
//                LogUtils.ERROR.info("record User.Notify.WeChat failed due to", e);
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
    
    @ResponseBody
    @RequestMapping("/checkWXPayResult")
    public APPResponseBody checkWXPayResult(CheckWXPayResultVo vo, HttpServletRequest request){
        APPResponseBody app = new APPResponseBody();

        if (StringUtils.isBlank(vo.getOutTradeNo()) || StringUtils.isBlank(vo.getOutTradeNo())){
            app.setRetnDesc("参数缺失");
            app.setRetnCode(1);
            return app;
        }
        
        logger.info("----------outTradeNo:" + vo.getOutTradeNo());
        if(StringUtils.contains("SUCCESS", payConfirmService.queryUserOrderOfWX(vo.getOutTradeNo()))){
        	logger.info("查询服务端订单状态是成功");
        	 app.setRetnCode(0);
             app.setRetnDesc("支付成功");
         } else {
             app.setRetnCode(1);
             app.setRetnDesc("支付失败");
         }
        return app;
    }
}
