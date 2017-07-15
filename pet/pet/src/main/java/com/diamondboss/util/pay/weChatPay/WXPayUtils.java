package com.diamondboss.util.pay.weChatPay;

import com.diamondboss.util.tools.PropsUtil;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by liuzifu on 2017/7/6.
 */
public class WXPayUtils {

    private static final Logger logger = Logger.getLogger(WXPayUtils.class);
    /**
     * 生成微信支付使用的nonce
     * @return
     */
    public static String createNonceStr() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String createSign(Document doc, String paykey) {
        List<Element> elements = new ArrayList<Element>(doc.getRootElement().elements());

        Collections.sort(elements, new Comparator<Element>() {
            @Override
            public int compare(Element e1, Element e2) {
                return e1.getName().compareTo(e2.getName());
            }

        });

        StringBuilder sb = new StringBuilder();
        for (Element e : elements) {
            if (e.getName().equals("sign") || Strings.isNullOrEmpty(e.getText())) {
                continue;
            }
            sb.append(e.getName()).append("=").append(e.getText()).append("&");
        }
        sb.append("key").append("=").append(paykey);

        return Hashing.md5().hashString(sb, Charsets.UTF_8).toString().toUpperCase();
    }

    public static boolean checkSign(Document doc, String paykey) {
        List<Element> elements = new ArrayList<Element>(doc.getRootElement().elements());
        String sign = "";
        Collections.sort(elements, new Comparator<Element>() {
            @Override
            public int compare(Element e1, Element e2) {
                return e1.getName().compareTo(e2.getName());
            }

        });

        StringBuilder sb = new StringBuilder();
        for (Element e : elements) {
            if (e.getName().equals("sign")) {
                sign = e.getText();
                continue;
            }
            if (Strings.isNullOrEmpty(e.getText())) {
                continue;
            }

            sb.append(e.getName()).append("=").append(e.getText()).append("&");
        }

        sb.append("key").append("=").append(paykey);
        String paySign = md5(sb.toString()).toUpperCase();
        logger.debug("校验签名,actualSign:"+ paySign +", expectSign:"+ sign);

        return sign.toUpperCase().equals(paySign);
    }

    /**
     * 获取请求的数据
     *
     * @param request
     * @return
     */
    public static String getRequestBody(HttpServletRequest request) {
        String inputLine;
        StringBuilder sbr = new StringBuilder();
        BufferedReader br = null;
        try {
            br = request.getReader();
            while ((inputLine = br.readLine()) != null) {
                sbr.append(inputLine);
            }
        } catch (IOException e) {
            logger.error("127.0.0.1||0||" + e.getClass().getName() + "||fail to read request body, request:" + request
                            +", server internal error:" + e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    logger.error("close buffer reader failed due to "+ e);
                }
            }
        }

        return sbr.toString();
    }

    public static boolean checkAppSign(Document doc) {
        String paykey = PropsUtil.getProperty("WXPay.payKey");
        return WXPayUtils.checkSign(doc, paykey);
    }

    public static Map<String, String> checkPayResult(Document requestXML) {
        Map<String, String> errRet = new HashMap<String, String>();
        errRet.put("return_code", "FAIL");
        errRet.put("return_msg", "参数格式校验错误");

        try {
            // 先获取return_code，查看是否成功，如果失败(通信异常)，则缺少许多xml返回值
            Element root = requestXML.getRootElement();
            Element returnCodeElement = root.element("return_code");

            if (returnCodeElement != null) {

                if (!"SUCCESS".equals(returnCodeElement.getText())) { // 交易失败（通信异常）
                    logger.debug("returnCode is FAIL, xml data: "+ requestXML.asXML());
                    return errRet;
                }
            }

            // 将返回的xml数据append一个paykey校验md5
            if (!WXPayUtils.checkAppSign(requestXML)) {
                logger.error("签名校验失败, xml data: "+ requestXML.asXML());
                errRet.put("return_msg", "APP签名校验失败");
                return errRet;
            }

            errRet.put("return_code", "SUCCESS");
            errRet.put("return_msg", "OK");
            return errRet;

        } catch (Exception ex) {
            logger.error("交易失败, 微信返回信息:"+ requestXML.asXML() +", 错误信息:"+ ex.toString());
            return errRet;
        }
    }


    public static Map<String, String> checkXmlAndPayResult(Document requestXML) {
        Map<String, String> errRet;

        String outTradeNo;
        Integer totalFee;
        String wxOrder;
        String openId;

        errRet = WXPayUtils.checkPayResult(requestXML);
        if (!"SUCCESS".equals(errRet.get("return_code"))) {
            return errRet;
        }

        errRet.put("return_code", "FAIL");
        errRet.put("return_msg", "参数格式校验错误");

        Element root = requestXML.getRootElement();

        // 检查交易是否成功
        Element resultCodeElement = root.element("result_code");
        String resultCode = "";
        if (resultCodeElement != null) {
            resultCode = resultCodeElement.getText();

            if (!resultCode.equals("SUCCESS")) { // 交易失败
                logger.debug("resultCode is FAIL, xml data:"+ requestXML.asXML());
                return errRet;
            }
        }

        if (WXPayUtils.hasElement(root, "out_trade_no")) {
            outTradeNo = WXPayUtils.getStringByElement(root, "out_trade_no");
            wxOrder = WXPayUtils.getStringByElement(root, "transaction_id");
            openId = WXPayUtils.getStringByElement(root, "openid");

            if (wxOrder == null) {
                logger.debug("未获取到微信订单号, 微信返回信息:"+ requestXML.asXML());
                return errRet;
            }

            if (WXPayUtils.hasElement(root, "total_fee")) {
                totalFee = WXPayUtils.getIntByElement(root, "total_fee");
            } else {
                logger.debug("out_trade_no:"+ outTradeNo +", 没有totalFee节点, 微信返回信息:"+ requestXML.asXML());
                return errRet;
            }

            errRet.put("return_code", "SUCCESS");
            errRet.put("return_msg", "OK");
            errRet.put("totalFee", String.valueOf(totalFee));
            errRet.put("outTradeNo", outTradeNo);
            errRet.put("wxOrder", wxOrder);
            errRet.put("openId", openId);

            return errRet;
        } else {
            logger.error("交易成功,未能获得订单信息:" + requestXML.asXML());
            return errRet;
        }
    }


    public static boolean hasElement(Element element, String elementName) {
        return element.element(elementName) != null;
    }

    public static String getStringByElement(Element element, String elementName) {
        if (WXPayUtils.hasElement(element, elementName)) {
            return element.element(elementName).getText();
        }

        return null;
    }

    public static Integer getIntByElement(Element element, String elementName) {
        Element intElement = element.element(elementName);
        Integer intValue = Integer.parseInt(intElement.getText());

        return intValue;
    }

    public static void callBack(HttpServletResponse response, Map<String, String> ret) throws IOException {
        Document responseXML = DocumentHelper.createDocument();
        Element root = responseXML.addElement("xml");
        root.addElement("return_code").setText(ret.get("return_code"));
        root.addElement("return_msg").setText(ret.get("return_msg"));
        response.getWriter().write(responseXML.asXML());
        response.getWriter().flush();
    }

    public static String md5(String content) {
        return Hashing.md5().hashString(content, Charsets.UTF_8).toString();
    }
}
