package com.diamondboss.util.pay.aliPay.service.impl;

import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.diamondboss.util.pay.aliPay.Alipay;
import com.diamondboss.util.pay.aliPay.service.AlipayService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzifu on 2017/6/21.
 *
 */
@Service
public class AlipayServiceImpl implements AlipayService {
    private static final Logger logger = Logger.getLogger(AlipayServiceImpl.class);

    @Override
    public String getPayOrderInfo() {
        String notifyUrl = "localhost:8080/alipay/acceptPayNotice";

        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        //TODO 组装订单数据
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        String orderInfo = Alipay.getPayOrder(model, notifyUrl);

        return orderInfo;
    }

    @Override
    public boolean analysisPayResult() {
        return false;
    }

    @Override
    public String acceptPayNotice() {
        Map<String, String> paramsMap = new HashMap<>(); //将异步通知中收到的待验证所有参数都存放到map中
        boolean signVerified = Alipay.checkAlipayRequest(paramsMap); //调用SDK验证签名
        if(signVerified){
            // TODO 验签成功后
            //按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
            return "succes";
        }else{
            // 验签失败则记录异常日志，并在response中返回failure.
            logger.info("支付通知延签失败,trade_no:"+paramsMap.get("trade_no"));
            return "failure";
        }

    }
}
