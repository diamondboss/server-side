package com.diamondboss.payment.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.diamondboss.payment.repository.PayConfirmMapper;
import com.diamondboss.payment.service.IPayConfirmService;
import com.diamondboss.threads.QueryAlipayTradeStatus;
import com.diamondboss.util.pojo.OutTradeNoPojo;
import com.diamondboss.util.tools.PropsUtil;
import com.diamondboss.util.tools.UUIDUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public PayConfirmMapper payConfirmMapper;
    
    @Override
    public boolean analysisAliPayResult(String result) {
        boolean flag = false;

        JSONObject content =  JSONObject.parseObject(result).getJSONObject("alipay_trade_app_pay_response");
        String appId = content.getString("app_id");
        String sellerId = content.getString("seller_id");
        if(StringUtils.equals(PropsUtil.getProperty("alipay.appId"), appId)
                && StringUtils.equals(PropsUtil.getProperty("alipay.sellerId"), sellerId)){
            flag = true;
        }

        String tradeNo = content.getString("trade_no");
        Thread queryThread = new Thread(new QueryAlipayTradeStatus(tradeNo));
        queryThread.start();

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

        String tradeStatus = params.get("trade_status");
        String outTradeNo = params.get("out_trade_no");
        
        OutTradeNoPojo pojo = UUIDUtil.getInfoFromTradeNo(outTradeNo);
        
        Map<String, Object> sqlMap = new HashMap<>();
        sqlMap.put("id", pojo.getId());
        sqlMap.put("tableId", pojo.getTableId());
        sqlMap.put("orderStatus", tradeStatus);
        
        // TODO 状态入库
        payConfirmMapper.updateOrderStatus();
        
        // TODO 调起派单流程


        return "success";
    }
}
