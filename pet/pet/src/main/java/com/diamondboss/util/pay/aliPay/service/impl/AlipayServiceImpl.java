package com.diamondboss.util.pay.aliPay.service.impl;

import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.diamondboss.user.controller.UserOtherController;
import com.diamondboss.user.service.impl.UserLoginServiceImpl;
import com.diamondboss.util.pay.aliPay.Alipay;
import com.diamondboss.util.pay.aliPay.service.AlipayService;
import com.diamondboss.util.tools.PropsUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzifu on 2017/6/21.
 *
 */
@Service
public class AlipayServiceImpl implements AlipayService {
    
    private static Logger logger = LogManager.getLogger(AlipayServiceImpl.class);

    @Override
    public String aliPayPreOrder(String orderId) {
        String notifyUrl = "localhost:8080/alipay/acceptPayNotice";

        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        //TODO 组装订单数据
        model.setBody("我是测试数据的描述信息");
        model.setSubject("我是测试数据的交易标题");
        model.setOutTradeNo(orderId);
        model.setSellerId(PropsUtil.getProperty("alipay.sellerid"));
        model.setTimeoutExpress(PropsUtil.getProperty("alipay.timeoutExpress"));
        model.setTotalAmount("0.01");
        model.setProductCode(PropsUtil.getProperty("alipay.productCode"));
        String orderInfo = Alipay.getPreOrder(model, notifyUrl);

        return orderInfo;
    }

    public static void main(String[] args){

        AlipayServiceImpl alipayService = new AlipayServiceImpl();
       System.out.println(alipayService.aliPayPreOrder("2017062497017"));
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
