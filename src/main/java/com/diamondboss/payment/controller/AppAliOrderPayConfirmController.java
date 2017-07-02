package com.diamondboss.payment.controller;

import com.alibaba.fastjson.JSONObject;
import com.diamondboss.payment.service.IPayConfirmService;
import com.diamondboss.util.pay.aliPay.EnumAlipayResult;
import com.diamondboss.util.vo.APPResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * Created by liuzifu on 2017/6/27.
 */
@Controller
@RequestMapping("/app/ali")
public class AppAliOrderPayConfirmController {
    private static Logger logger = Logger.getLogger(AppAliOrderPayConfirmController.class);
    @Autowired
    IPayConfirmService payConfirmService;

    @RequestMapping("/payConfirm")
    public void payConfirm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 获取支付宝POST过来反馈信息
            Map<String, String[]> requestParams = request.getParameterMap();
            logger.info("支付宝充值订单支付通知:"+JSONObject.toJSONString(requestParams));
            String confirmResult = payConfirmService.alipayConfirm(requestParams);

            response.getWriter().write(confirmResult);
        } catch (Exception ex) {
            logger.error("支付宝充值订单支付通知错误,requestBody:"+ JSONObject.toJSONString(request.getParameterMap()));
            response.getWriter().write("fail");
            return;
        } finally {
            response.getWriter().flush();
        }
    }

    @ResponseBody
    @RequestMapping("/checkAliPayResult")
    public APPResponseBody analysisPayResult(HttpServletRequest request, String resultStatus, String result, String memo){
        APPResponseBody app = new APPResponseBody();

        if (StringUtils.isNotBlank(resultStatus) || StringUtils.isNotBlank(result)
                || StringUtils.isNotBlank(memo)){
            app.setData("参数缺失");
            app.setRetnCode(1);
            return app;
        }

        boolean flag = false;
        if (StringUtils.equals(EnumAlipayResult.SUCCESS.status, resultStatus)){
                flag = payConfirmService.analysisAliPayResult(result);
        }

        app.setRetnCode(0);
        app.setData(flag);
        return app;
    }
}
