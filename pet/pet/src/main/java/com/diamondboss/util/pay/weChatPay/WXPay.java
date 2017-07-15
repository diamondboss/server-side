package com.diamondboss.util.pay.weChatPay;

import com.alibaba.fastjson.JSONObject;
import com.diamondboss.util.tools.HttpUtils;
import com.diamondboss.util.tools.PropsUtil;
import com.diamondboss.util.tools.UUIDUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by liuzifu on 2017/7/6.
 */
public class WXPay {
    private static final Logger logger = Logger.getLogger(WXPay.class);

    private static final String appId= PropsUtil.getProperty("WXPay.appId");
    private static final String mchID= PropsUtil.getProperty("WXPay.mchId");
    private static final String payKey= PropsUtil.getProperty("WXPay.payKey");
    private static final String tradeType= PropsUtil.getProperty("WXPay.tradeType");


    public static Map<String, Object> sendPreOrder(WXPayDto weChatPayDto){

        String orderId = weChatPayDto.getOrderId();
        logger.info("weChat preOrder--->orderId:"+orderId+", body:"
                + weChatPayDto.getBody()+", fee:" + weChatPayDto.getFee());
        String nonceStr = WXPayUtils.createNonceStr();

        Document requestXML = DocumentHelper.createDocument();
        Element root = requestXML.addElement("xml");

        root.addElement("appid").setText(appId);
        //商户自定义数据
//        root.addElement("attach").setText(weChatPayDto.getAttach());
        root.addElement("mch_id").setText(mchID);
        // 随机字符串
        root.addElement("nonce_str").setText(UUIDUtil.uuid());
        root.addElement("body").setText(weChatPayDto.getBody());
        root.addElement("out_trade_no").setText(weChatPayDto.getOutTradeNo());
        root.addElement("total_fee").setText(String.valueOf(weChatPayDto.getFee()));
        root.addElement("spbill_create_ip").setText(weChatPayDto.getIp());
        root.addElement("notify_url").setText(weChatPayDto.getNotifyUrl());
        root.addElement("trade_type").setText(tradeType);

        root.addElement("sign").setText(WXPayUtils.createSign(requestXML, payKey));

        String url = PropsUtil.getProperty("WXPay.unifiedOrderUrl");
        String response;
        try {
            response = HttpUtils.doPost(url, requestXML.asXML());
        } catch (Exception e) {
            logger.error("preOrder error, orderId:" + orderId + ", url:" + url);
            return null;
        }
        return analysisPreOrderResponse(orderId, nonceStr, response);

    }

    /**
     * 查询支付结果
     * @param outTradeNo
     * @return SUCCESS—支付成功
     *         REFUND—转入退款
     *         NOTPAY—未支付
     *         CLOSED—已关闭
     *         REVOKED—已撤销（刷卡支付）
     *         USERPAYING--用户支付中
     *         PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    public static String queryTradeStatus(String outTradeNo){
        Document requestXML = DocumentHelper.createDocument();
        Element root = requestXML.addElement("xml");

        root.addElement("appid").setText(appId);
        root.addElement("mch_id").setText(mchID);
        root.addElement("nonce_str").setText(UUIDUtil.uuid());
        root.addElement("out_trade_no").setText(outTradeNo);
        root.addElement("sign").setText(WXPayUtils.createSign(requestXML, payKey));

        String url = PropsUtil.getProperty("WXPay.orderQuery");
        String response;
        try {
            response = HttpUtils.doPost(url, requestXML.asXML());
        } catch (Exception e) {
            logger.error("queryTradeStatus error, outTradeNo: " + outTradeNo + ", url: " + url);
            return null;
        }
        // 解析回复信息
        try {
            Document responseXML = DocumentHelper.parseText(response);
            Element returnCodeElement = responseXML.getRootElement().element("return_code");
            Element resultCodeElement = responseXML.getRootElement().element("result_code");
            Element returnMsgElement = responseXML.getRootElement().element("return_msg");

            if (returnCodeElement == null) {
                return null;
            }

            String returnCode = returnCodeElement.getText();
            if (returnCode == null) {
                return null;
            }

            // 查询失败（通信异常）
            if (returnCode.equals("FAIL")) {
                // 没有返回信息
                if (returnMsgElement == null) {
                    logger.error("queryTradeStatus error,查询失败（通信异常),outTradeNo:"+ outTradeNo +", returnMsg is null");
                    return null;
                }
                String returnMsg = returnMsgElement.getText();
                logger.error("queryTradeStatus error,查询失败（通信异常),outTradeNo:"+ outTradeNo +", errorMsg:"+ returnMsg);
                return null;
            }

            // 请求成功
            if (returnCode.equals("SUCCESS")) {
                logger.info("queryTradeStatus success,returnCode is SUCCESS,outTradeNo:"+ outTradeNo);
                if (!WXPayUtils.checkSign(responseXML, payKey)) { // 校验签名
                    logger.info("queryTradeStatus success,checkSign failed,outTradeNo:"+ outTradeNo);
                    return null;
                }

                if (resultCodeElement == null) {
                    return null;
                }
               return resultCodeElement.getText();
            }

        } catch (DocumentException ex) {
            logger.error("parse response xml error.", ex);
            return null;
        }
        return null;
    }


    private static Map<String, Object> analysisPreOrderResponse(String orderId, String nonceStr, String response) {
        // 解析回复信息
        try {
            Document responseXML = DocumentHelper.parseText(response);
            Element returnCodeElement = responseXML.getRootElement().element("return_code");
            Element resultCodeElement = responseXML.getRootElement().element("result_code");
            Element returnMsgElement = responseXML.getRootElement().element("return_msg");
//            Element errorCodeElement = responseXML.getRootElement().element("err_code");
//            Element errorCodeDesElement = responseXML.getRootElement().element("err_code_des");

            if (returnCodeElement == null) {
                return null;
            }

            String returnCode = returnCodeElement.getText();
            if (returnCode == null) {
                return null;
            }

            // 下单失败（通信异常）
            if (returnCode.equals("FAIL")) {
                // 没有返回信息
                if (returnMsgElement == null) {
                    logger.error("preOrder error,下单失败（通信异常),orderId:"+ orderId +", returnMsg is null");
                    return null;
                }
                String returnMsg = returnMsgElement.getText();
                logger.error("preOrder error,下单失败（通信异常),orderId:"+ orderId +", errorMsg:"+ returnMsg);
                return null;
            }

            // 请求成功
            if (returnCode.equals("SUCCESS")) {
                logger.info("preOrder success,returnCode is SUCCESS,orderId:"+ orderId);
                if (!WXPayUtils.checkSign(responseXML, payKey)) { // 校验签名
                    logger.info("preOrder success,checkSign failed,orderId:"+ orderId);
                    return null;
                }

                if (resultCodeElement == null) {
                    return null;
                }
                String resultCode = resultCodeElement.getText();
                // 下单成功
                if (resultCode.equals("SUCCESS")) {
                    logger.info("preOrder success,resultCode is success,orderId:"+ orderId);

                    // 构建返回信息
                    String prepayId = responseXML.getRootElement().element("prepay_id").getText();
                    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                    String pack = String.format("prepay_id=%s", prepayId);
                    Map<String, String> paySignMap = new TreeMap<>();

                    paySignMap.put("appid", appId);
                    paySignMap.put("noncestr", nonceStr);
                    paySignMap.put("timestamp", timestamp);
                    paySignMap.put("package", "Sign=WXPay");
                    paySignMap.put("partnerid", mchID);
                    paySignMap.put("prepayid", prepayId);

                    logger.info("APP请求成功, paySignMap:{}"+ paySignMap);

                    StringBuffer paySignSB = new StringBuffer();
                    for (Map.Entry<String, String> entry : paySignMap.entrySet()) {
                        paySignSB.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                    }
                    paySignSB.append("key=").append(payKey);
                    String paySign = WXPayUtils.md5(paySignSB.toString()).toUpperCase();

                    Map<String, Object> result = new HashMap<>();
                    result.put("appid", appId);
                    result.put("partnerid", mchID);
                    result.put("prepayid", prepayId);
                    result.put("package", "Sign=WXPay");
                    result.put("nonceStr", nonceStr);
                    result.put("timeStamp", timestamp);
                    result.put("sign", paySign);

                    logger.info("preOrder success, orderId:"+ orderId +", result:"+ JSONObject.toJSONString(result));
                    return result;
                } else if (resultCode.equals("FAIL")) {
                    logger.info("preOrder failed,resultCode is FAIL,orderId:"+ orderId);
//                    if (errorCodeElement == null) {
//                        return null;
//                    }
//
//                    String errorCode = errorCodeElement.getText();
//                    String errorCodeDes = null;
//                    if (errorCodeDesElement != null) {
//                        errorCodeDes = errorCodeDesElement.getText();
//                    }
//
//                    if (errorCode.equals("OUT_TRADE_NO_USED")) {
////                        logger.info("preOrder failed, 单号重复, orderId:"+ orderId);
//
////                        boolean isCancel = cancelUsedOrder(appId, mchID, weChatDto.getOrderId(), nonceStr, payKey);
////                        if (isCancel && retryTimes <= 5) {
////                            retryTimes++;
////                            // 成功取消后重新下单
////                            sendPreOrderNew(weChatDto, tradeType, appId, mchID, payKey, retryTimes);
////                            return null;
////                        } else {
////                            Map<String, Object> result = new HashMap<String, Object>();
////                            result.put("cancel", "false");
////                            return result;
////                        }
//                    }
//
////                    logger.info("error_code:"+ errorCode +" error_code_des:"+ errorCodeDes);
                    return null;
                }
            }

        } catch (DocumentException ex) {
            logger.error("parse response xml error.", ex);
            return null;
        }
        return null;
    }
}
