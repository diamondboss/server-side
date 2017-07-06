package com.diamondboss.util.pay.weChatPay;

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
import java.util.UUID;

/**
 * Created by liuzifu on 2017/7/6.
 */
public class WeChatPay {
    private static final Logger logger = Logger.getLogger(WeChatPay.class);

    private static final String appId= PropsUtil.getProperty("weChat.appId");
    private static final String mchID= PropsUtil.getProperty("weChat.mchId");
    private static final String payKey= PropsUtil.getProperty("weChat.payKey");
    private static final String tradeType= PropsUtil.getProperty("weChat.tradeType");

    public static Map<String, Object> sendPreOrder(WeChatPayDto weChatPayDto){

        String orderId = weChatPayDto.getOrderId();
        logger.info("weChat preOrder--->orderId:"+orderId+", body:"
                + weChatPayDto.getBody()+", fee:" + weChatPayDto.getFee());
        String nonceStr = WeChatUtils.createNonceStr();

        Document requestXML = DocumentHelper.createDocument();
        Element root = requestXML.addElement("xml");

        root.addElement("appid").setText(appId);
        root.addElement("attach").setText(weChatPayDto.getAttach());
        root.addElement("mch_id").setText(mchID);
        // 随机字符串
        root.addElement("nonce_str").setText(UUIDUtil.uuid());
        root.addElement("body").setText(weChatPayDto.getBody());
        // 使用uuid做订单号，预防价格变动导致的订单重复问题
        root.addElement("out_trade_no").setText(UUIDUtil.uuid());
        root.addElement("total_fee").setText(String.valueOf(weChatPayDto.getFee()));
        root.addElement("spbill_create_ip").setText(weChatPayDto.getIp());
        root.addElement("notify_url").setText(weChatPayDto.getNotifyUrl());
        root.addElement("trade_type").setText(tradeType);

        root.addElement("sign").setText(WeChatUtils.createSign(requestXML, payKey));

        logger.debug("orderId:"+orderId+", requestXml:"+requestXML.asXML());

        String url = PropsUtil.getProperty("weChat.notifyUrl");

        String response;
        try {
            response = HttpUtils.doPost(url, requestXML.asXML());
        } catch (Exception e) {
            logger.error("preOrder error: orderId:" + orderId + ", url:" + url);
            return null;
        }

        // 解析回复信息
        try {
            Document responseXML = DocumentHelper.parseText(response);
            Element returnCodeElement = responseXML.getRootElement().element("return_code");
            Element resultCodeElement = responseXML.getRootElement().element("result_code");
            Element returnMsgElement = responseXML.getRootElement().element("return_msg");
            Element errorCodeElement = responseXML.getRootElement().element("err_code");
            Element errorCodeDesElement = responseXML.getRootElement().element("err_code_des");

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
                logger.debug("preOrder success,returnCode is SUCCESS,orderId:"+ orderId);
                if (!WeChatUtils.checkSign(responseXML, payKey)) { // 校验签名
                    logger.debug("preOrder success,checkSign failed,orderId:"+ orderId);
                    return null;
                }

                if (resultCodeElement == null) {
                    return null;
                }
                String resultCode = resultCodeElement.getText();
                // 下单成功
                if (resultCode.equals("SUCCESS")) {
                    logger.debug("preOrder success,resultCode is success,orderId:"+ orderId);

                    // 构建返回信息
                    String prepayId = responseXML.getRootElement().element("prepay_id").getText();
                    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                    String pack = String.format("prepay_id=%s", prepayId);
                    Map<String, String> paySignMap = new TreeMap<>();

                    if (tradeType.equals("JSAPI")) {
                        paySignMap.put("appId", appId);
                        paySignMap.put("nonceStr", nonceStr);
                        paySignMap.put("timeStamp", timestamp);
                        paySignMap.put("signType", "MD5");
                        paySignMap.put("package", pack);
                        logger.debug("JSAPI请求成功，paySignMap:{}"+ paySignMap);
                    } else if (tradeType.equals("APP")) {
                        paySignMap.put("appid", appId);
                        paySignMap.put("noncestr", nonceStr);
                        paySignMap.put("timestamp", timestamp);
                        paySignMap.put("package", "Sign=WXPay");
                        paySignMap.put("partnerid", mchID);
                        paySignMap.put("prepayid", prepayId);

                        logger.debug("APP请求成功, paySignMap:{}"+ paySignMap);
                    }

                    StringBuffer paySignSB = new StringBuffer();
                    for (Map.Entry<String, String> entry : paySignMap.entrySet()) {
                        paySignSB.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                    }
                    paySignSB.append("key=").append(payKey);
                    String paySign = WeChatUtils.md5(paySignSB.toString()).toUpperCase();

                    Map<String, Object> result = new HashMap<>();
                    result.put("nonceStr", nonceStr);
                    result.put("timeStamp", timestamp);
                    result.put("package", pack);
                    result.put("paySign", paySign);

                    logger.debug("preOrder success, orderId:"+ orderId +", result:"+ result);
                    return result;
                } else if (resultCode.equals("FAIL")) {
                    logger.debug("preOrder failed,resultCode is FAIL,orderId:"+ orderId);

                    if (errorCodeElement == null) {
                        return null;
                    }

                    String errorCode = errorCodeElement.getText();
                    String errorCodeDes = null;
                    if (errorCodeDesElement != null) {
                        errorCodeDes = errorCodeDesElement.getText();
                    }

                    if (errorCode.equals("OUT_TRADE_NO_USED")) {
                        logger.debug("preOrder failed, 单号重复, orderId:"+ orderId);

//                        boolean isCancel = cancelUsedOrder(appId, mchID, weChatDto.getOrderId(), nonceStr, payKey);
//                        if (isCancel && retryTimes <= 5) {
//                            retryTimes++;
//                            // 成功取消后重新下单
//                            sendPreOrderNew(weChatDto, tradeType, appId, mchID, payKey, retryTimes);
//                            return null;
//                        } else {
//                            Map<String, Object> result = new HashMap<String, Object>();
//                            result.put("cancel", "false");
//                            return result;
//                        }
                    }

                    logger.debug("error_code:"+ errorCode +" error_code_des:"+ errorCodeDes);
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
