package com.diamondboss.util.pay.weChatPay;

import com.alibaba.fastjson.JSONObject;
import com.diamondboss.util.pojo.OutTradeNoPojo;
import com.diamondboss.util.tools.HttpUtils;
import com.diamondboss.util.tools.PropsUtil;
import com.diamondboss.util.tools.UUIDUtil;
import com.mysql.jdbc.log.LogUtils;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.http.HttpEntity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

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
    
    /**
     * 退款
     * @param wXPayReFundDto
     * @return
     */
    public static Map<String, Object> refund(WXPayReFundDTO wXPayReFundDto){
		logger.info("weChat refund--->outTradeNo:" + wXPayReFundDto.getOutTradeNo() + ", totalFee:"
				+ wXPayReFundDto.getTotalFee() + ", refundFee:" + wXPayReFundDto.getRefundFee() + ", notifyUrl:"
				+ wXPayReFundDto.getNotifyUrl());

		String nonceStr = WXPayUtils.createNonceStr();

		Document requestXML = DocumentHelper.createDocument();
		Element root = requestXML.addElement("xml");

		root.addElement("appid").setText(appId);
		// 商户自定义数据
		root.addElement("mch_id").setText(mchID);
		// 随机字符串
		root.addElement("nonce_str").setText(nonceStr);

		root.addElement("out_trade_no").setText(wXPayReFundDto.getOutTradeNo());
		
		OutTradeNoPojo pojo = UUIDUtil.getInfoFromTradeNo(wXPayReFundDto.getOutTradeNo());
		root.addElement("out_refund_no").setText(UUIDUtil.makeTradeNo(Integer.valueOf(pojo.getTableId()), pojo.getId()));
		
		root.addElement("total_fee").setText(String.valueOf(wXPayReFundDto.getTotalFee()));
		root.addElement("refund_fee").setText(String.valueOf(wXPayReFundDto.getRefundFee()));
		root.addElement("transaction_id").setText("");
		
		root.addElement("sign").setText(WXPayUtils.createSign(requestXML, payKey));
		

        String url = wXPayReFundDto.getNotifyUrl();
        String response;
        try {
            response = HttpUtils.doPost(url, requestXML.asXML());
        } catch (Exception e) {
            logger.error("weChat refund--->outTradeNo:" + wXPayReFundDto.getOutTradeNo() + ", url:" + url);
            logger.info(e.getMessage());
            return null;
        }
		return analysisRefundResponse(wXPayReFundDto.getOutTradeNo(), nonceStr, response); 
    }
    
    /**
     * 退款新接口
     * @param wXPayReFundDto
     * @return
     */
    @SuppressWarnings("deprecation")
	public static Map<String, Object> refund1(WXPayReFundDTO wXPayReFundDto){

        Document requestXML = DocumentHelper.createDocument();
        Element root = requestXML.addElement("xml");

        String nonceStr = WXPayUtils.createNonceStr();
        
        root.addElement("appid").setText(appId);
        root.addElement("mch_id").setText(mchID);
        root.addElement("nonce_str").setText(nonceStr);
        root.addElement("transaction_id").setText("");

        OutTradeNoPojo pojo = UUIDUtil.getInfoFromTradeNo(wXPayReFundDto.getOutTradeNo());
        root.addElement("out_refund_no").setText(UUIDUtil.makeTradeNo(Integer.valueOf(pojo.getTableId()), pojo.getId()));
        root.addElement("total_fee").setText(String.valueOf(wXPayReFundDto.getTotalFee()));
        root.addElement("refund_fee").setText(String.valueOf(wXPayReFundDto.getRefundFee()));

        root.addElement("sign").setText(WXPayUtils.createSign(requestXML, payKey));
        
        logger.debug("wxReFund, requestXml:{}");

        String reuqestXml = root.asXML();
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
        } catch (KeyStoreException e) {
        	logger.info("wechat key error");
            return null;
        }
        FileInputStream instream = null;// 放退款证书的路径
        try {
            //instream = new FileInputStream("configs/h5_apiclient_cert.p12");
        	instream = new FileInputStream("D:\\warTest\\apiclient_cert.p12" );
        } catch (FileNotFoundException e) {
        	logger.info("wechat cert error");
            return null;
        }
        try {
            keyStore.load(instream, mchID.toCharArray());
        } catch (CertificateException | NoSuchAlgorithmException | IOException e) {
        	logger.info("wechat key load cert error");
            return null;
        } finally {
            try {
                instream.close();
            } catch (IOException e) {
            	logger.info(e.getMessage());
            }
        }
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchID.toCharArray())
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException | UnrecoverableKeyException e) {
        	logger.info("wechat sslcontext error");
            return null;
        }
        @SuppressWarnings("deprecation")
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

        try {
            HttpPost httpPost = new HttpPost(wXPayReFundDto.getNotifyUrl());// 退款接口

            System.out.println("executing request" + httpPost.getRequestLine());
            StringEntity reqEntity = new StringEntity(reuqestXml);
            // 设置类型
            reqEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse resp = httpclient.execute(httpPost);
            try {
                HttpEntity entity = (HttpEntity) resp.getEntity();
                if (entity != null) {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(((org.apache.http.HttpEntity) entity).getContent(), "UTF-8"));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        response += text;
                    }

                }
                EntityUtils.consume((org.apache.http.HttpEntity) entity);
            } finally {
                resp.close();
            }
        } catch (UnsupportedEncodingException | ClientProtocolException e) {
        	logger.info("send refund request error ", e);
            logger.info("send refund request error");
            return null;
        } catch (IOException e) {
        	logger.info("send refund request error ", e);
            logger.info("send refund request error");
            return null;
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("refund response" + response);

        // 解析回复信息
        try {
            Document responseXML = DocumentHelper.parseText(response);
            Element returnCodeElement = responseXML.getRootElement().element("return_code");
            Element resultCodeElement = responseXML.getRootElement().element("result_code");
            Element returnMsgElement = responseXML.getRootElement().element("return_msg");
            Element errorCodeElement = responseXML.getRootElement().element("err_code");
            Element errorCodeDesElement = responseXML.getRootElement().element("err_code_des");

            if (returnCodeElement == null) {
            	logger.info("send refund result is null");
                return null;
            }

            String returnCode = returnCodeElement.getText();

            if (returnCode == null) {
            	logger.info("send refund return code is null");
                return null;
            }

            // 下单失败（通信异常）
            if (returnCode.equals("FAIL")) {
                // 没有返回信息
                if (returnMsgElement == null) {
                	logger.info("refund error,退款失败（通信异常) returnMsg is null");
                    logger.info("send refund return code is fail and returnMsgElement is null");
                    return null;
                }
                String returnMsg = returnMsgElement.getText();
                logger.info("refund error 退款失败（通信异常)");
                return null;
            }

            // 请求成功
            if (returnCode.equals("SUCCESS")) {
                logger.info("refund success,returnCode is SUCCESS");
                if (!WXPayUtils.checkSign(responseXML, payKey)) { // 校验签名
                	logger.info("refund success,checkSign failed");
                    logger.info("refund success,checkSign failed");
                    return null;
                }

                if (resultCodeElement == null) {
                	logger.info("resultCodeElement is null");
                    return null;
                }
                String resultCode = resultCodeElement.getText();
                // 下单成功
                if (resultCode.equals("SUCCESS")) {
                	logger.info("refund success,resultCode is success");
                    return null;
                } else if (resultCode.equals("FAIL")) {
                	logger.info("refund failed,resultCode is FAIL");

                    if (errorCodeElement == null) {
                    	logger.info("errorCodeElement is null");
                        return null;
                    }
                    String errorCode = errorCodeElement.getText();
                    String errorCodeDes = null;
                    if (errorCodeDesElement != null) {
                        errorCodeDes = errorCodeDesElement.getText();
                    }
                    return null;
                }
            }

        } catch (DocumentException ex) {
        	logger.info("parse response xml error.");
        	logger.info("parse response xml error");
            return null;
        }

        logger.info("resultCode is error");	
    	
    	return null;
    }
    
    
    /**
     * 查询退款结果
     * @param outTradeNo
     * @return
     */
    public static String queryRefundStatus(String outTradeNo){
		Document requestXML = DocumentHelper.createDocument();
		Element root = requestXML.addElement("xml");

		return "";
    }
    
    /**
     * 微信退款响应处理
     * @param orderId
     * @param nonceStr
     * @param response
     * @return
     */
    private static Map<String, Object> analysisRefundResponse(String outTradeNo,String nonceStr, String response) {
		try {
			Document responseXML = DocumentHelper.parseText(response);
			Element returnCodeElement = responseXML.getRootElement().element("return_code");
			Element returnMsgElement = responseXML.getRootElement().element("return_msg");
			Element resultCodeElement = responseXML.getRootElement().element("result_code");
			
			// Element errorCodeElement =
			// responseXML.getRootElement().element("err_code");
			// Element errorCodeDesElement =
			// responseXML.getRootElement().element("err_code_des");

			if (returnCodeElement == null) {
				return null;
			}

			String returnCode = returnCodeElement.getText();
			if (returnCode == null) {
				return null;
			}
			
			// 退款失败（通信异常）
			if (returnCode.equals("FAIL")) {
				// 没有返回信息
				if (returnMsgElement == null) {
					logger.error("refund error,退款失败（通信异常),outTradeNo:" + outTradeNo + ", returnMsg is null");
					return null;
				}
				String returnMsg = returnMsgElement.getText();
				logger.error("refund error,退款失败（通信异常),outTradeNo:" + outTradeNo + ", errorMsg:" + returnMsg);
				return null;
			}

			// 请求成功
			if (returnCode.equals("SUCCESS")) {
				logger.info("refund success,returnCode is SUCCESS,outTradeNo:" + outTradeNo);
				if (!WXPayUtils.checkSign(responseXML, payKey)) { // 校验签名
					logger.info("refund success,checkSign failed,outTradeNo:" + outTradeNo);
					return null;
				}
				if (resultCodeElement == null) {
					return null;
				}
				String resultCode = resultCodeElement.getText();
				// 退款成功
				if (resultCode.equals("SUCCESS")) {
					logger.info("preOrder success,resultCode is success,outTradeNo:" + outTradeNo);
					
                    Map<String, String> paySignMap = new TreeMap<>();

                    paySignMap.put("appid", appId);
                    paySignMap.put("noncestr", nonceStr);
                    paySignMap.put("package", "Sign=WXPay");
                    paySignMap.put("partnerid", mchID);

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
                    result.put("package", "Sign=WXPay");
                    result.put("nonceStr", nonceStr);
                    result.put("sign", paySign);

                    logger.info("refund success, outTradeNo:"+ outTradeNo +", result:"+ JSONObject.toJSONString(result));
                    return result;
				}else if (resultCode.equals("FAIL")) {
                    logger.info("refund failed,resultCode is FAIL,outTradeNo:"+ outTradeNo);
                    
                    return null;
				}
			}
		} catch (DocumentException ex) {
			logger.error("refund parse response xml error.", ex);
			return null;
		}

		return null;
    }
    
}
