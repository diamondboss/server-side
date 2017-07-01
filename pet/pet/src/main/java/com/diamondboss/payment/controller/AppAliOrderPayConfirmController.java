package com.diamondboss.payment.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by liuzifu on 2017/6/27.
 */
@Controller
@RequestMapping("/app/ali")
public class AppAliOrderPayConfirmController {
    private static Logger logger = LogManager.getLogger(AppAliOrderPayConfirmController.class);

    @RequestMapping("/payConfirm")
    public void payConfirm(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuilder reqStr = new StringBuilder();
        try {
            // 获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map<String, String[]> requestParams = request.getParameterMap();
            logger.info("支付宝充值订单支付通知:{}", JSONObject.toJSONString(request.getParameterMap()));
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
                reqStr.append("name=");
                reqStr.append(name);
                reqStr.append(",valueStr=");
                reqStr.append(valueStr);
                reqStr.append("&");
            }


            //TODO 延签、状态入库


            logger.info("支付宝充值订单支付通知:{}", reqStr.toString());
            response.getWriter().write("success");
            response.getWriter().flush();
        } catch (Exception ex) {
            logger.error("支付宝充值订单支付通知错误,requestBody:{}", reqStr.toString());
            response.getWriter().write("fail");
            response.getWriter().flush();
            return;
        }
    }
}
