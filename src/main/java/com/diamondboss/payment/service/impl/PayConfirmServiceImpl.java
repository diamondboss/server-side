package com.diamondboss.payment.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.diamondboss.payment.service.IPayConfirmService;
import com.diamondboss.util.tools.PropsUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by liuzifu on 2017/7/2.
 */
@Service
public class PayConfirmServiceImpl implements IPayConfirmService {
    private static final Logger logger = Logger.getLogger(PayConfirmServiceImpl.class);

    @Override
    public boolean analysisAliPayResult(String result) {
        boolean flag = false;

        //  String resultt = "{"alipay_trade_app_pay_response":{"code":"10000","msg":"Success","app_id":"2017061207471974","auth_app_id":"2017061207471974","charset":"utf-8","timestamp":"2017-07-01 13:54:07","total_amount":"0.01","trade_no":"2017070121001004940276861094","seller_id":"2088721242292724","out_trade_no":"15orderPay1synull1187"},"sign":"Zoxn0BAJwVrbg1HvKRsYf3km8V147gnjHqZ3oe8bJYIpCRV3zk8zIldH4nG38fMVUwcWmcRAyAZ+7UT0nJ4XkFHfQIUm/D2J9lFk2SsATXlxrn4pDbofrh7CbxXg3cl+t52Q2hLamaN7LdvMjNM0B/tXJXeDsajxzfze9Ncd/U7ASHq7jk8rUOqOWLeXO5Qzj3IjCagxh14m228PWPSxPAp+oqtfOwcA1SAa2dTDs6DDG0Y1vUsPv2iD7P4nR0cgkXnq2ZZIZldarVa5DqE5vwjuXyveBaqjwMC0z0O+H1cNZx0na1a2Y9a2fVsoEf7F++JPIGt0yjjhwYe6qZxINw==","sign_type":"RSA2"}, memo=}";
        JSONObject content =  JSONObject.parseObject(result).getJSONObject("alipay_trade_app_pay_response");
        String appId = content.getString("app_id");
        String sellerId = content.getString("seller_id");
        if(StringUtils.equals(PropsUtil.getProperty("app_id"), appId)
                && StringUtils.equals(PropsUtil.getProperty("seller_id"), sellerId)){
            flag = true;
        }

        //TODO 起异步线程，等待5s后查询支付宝支付结果，调起派单流程

        return flag;
    }

    @Override
    public String alipayConfirm(Map<String, String[]> requestParams) throws AlipayApiException {
        Map<String, String> params = new HashMap<>();

        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
            logger.info("支付宝支付通知解析后："+JSONObject.toJSONString(params));
        }

        // 验签
        boolean flag = AlipaySignature.rsaCheckV1(params, PropsUtil.getProperty("alipay.publicKey"),
                PropsUtil.getProperty("alipay.charset"), PropsUtil.getProperty("alipay.signType"));

        if(!flag){
            return "fail";
        }

        // TODO 状态入库

        // TODO 调起派单流程


        return "success";
    }
}
