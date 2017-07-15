package com.diamondboss.util.pay.weChatPay;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

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

    public static String md5(String content) {
        return Hashing.md5().hashString(content, Charsets.UTF_8).toString();
    }
}
