package com.diamondboss.petsHotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.diamondboss.constants.PetConstants;
import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.service.impl.DistributeOrderServiceImpl;
import com.diamondboss.payment.repository.PayConfirmMapper;
import com.diamondboss.payment.service.IPayConfirmService;
import com.diamondboss.util.pay.aliPay.EnumAlipayResult;
import com.diamondboss.util.pay.weChatPay.WXPayUtils;
import com.diamondboss.util.pojo.OutTradeNoPojo;
import com.diamondboss.util.tools.UUIDUtil;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.CheckAliPayVo;
import com.diamondboss.util.vo.CheckWXPayResultVo;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzifu on 2017/7/9.
 */
@Controller
@RequestMapping("/apphotel/wxPay")
public class WeChatPayConfirmController {
    private static Logger logger = Logger.getLogger(WeChatPayConfirmController.class);
    @Autowired
    IPayConfirmService payConfirmService;
    
    @Autowired
    public PayConfirmMapper payConfirmMapper;
    
    @Autowired
    private DistributeOrderServiceImpl distributeOrderServiceImpl;

    @RequestMapping("/payConfirm")
    public void payConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        Map<String, String> ret = new HashMap<>();

        Document requestXML;
        String requestBody = WXPayUtils.getRequestBody(request);
        try {
            logger.info("微信支付通知:" + requestBody);
            requestXML = DocumentHelper.parseText(requestBody); // 解析成XML
            ret = WXPayUtils.checkXmlAndPayResult(requestXML);
            logger.info("微信支付通知解析结果：" + JSONObject.toJSONString(ret));
            if (!ret.get("return_code").equals("SUCCESS")) {
                WXPayUtils.callBack(response, ret);
                return;
            }
        } catch (Exception ex) {
            logger.info("微信支付通知,处理出错"+ ex);
            WXPayUtils.callBack(response, ret);
            return;
        }

        // TODO: 2017/7/15 入库更新
        //ret = payConfirmService.wxpayConfirm(ret);
        WXPayUtils.callBack(response, ret);
        
        // 订单支付已成功，获取订单信息，检查数据库中的订单状态等，并给微信服务器返回success
        //交易状态
        String tradeStatus = ret.get("return_code");
        //我们自己的订单Id
        String outTradeNo = ret.get("outTradeNo");
        //微信	的订单Id
        String tradeNo = ret.get("wxOrder");
        
        OutTradeNoPojo pojo = UUIDUtil.getInfoFromTradeNo(outTradeNo);
        
        Map<String, Object> sqlMap = new HashMap<>();
        sqlMap.put("id", pojo.getId());
        sqlMap.put("orderUser", PetConstants.ORDER_USER_TABLE_PREFIX + pojo.getTableId());
        if("SUCCESS".equals(tradeStatus)){
        	sqlMap.put("orderStatus", PetConstants.ORDER_STATUS_PAY_SUCCESS);
        	sqlMap.put("outTradeNo", outTradeNo);
        	sqlMap.put("tradeNo", tradeNo);
        	sqlMap.put("payType", 1);
        }else{
        	sqlMap.put("orderStatus", PetConstants.ORDER_STATUS_PAY_FAILURE);
        	sqlMap.put("outTradeNo", outTradeNo);
        	sqlMap.put("tradeNo", tradeNo);
        	sqlMap.put("payType", 1);
        }
        
        logger.info("支付宝开始更新数据库");
        
        // TODO 异步通知状态入库
        payConfirmMapper.updateOrderStatus(sqlMap);
        OrderUserPojo userPojo = payConfirmMapper.queryUserOrderById(sqlMap);
        // TODO 调起派单流程
        distributeOrderServiceImpl.DistributeOrder(userPojo);
        return;
    }
    
    @ResponseBody
    @RequestMapping("/checkWXPayResult")
    public APPResponseBody checkWXPayResult(CheckWXPayResultVo vo, HttpServletRequest request){
        APPResponseBody app = new APPResponseBody();

        if (StringUtils.isBlank(vo.getOutTradeNo()) || StringUtils.isBlank(vo.getOutTradeNo())){
            app.setRetnDesc("参数缺失");
            app.setRetnCode(1);
            return app;
        }
        
        logger.info("----------outTradeNo:" + vo.getOutTradeNo());
        if(StringUtils.contains("SUCCESS", payConfirmService.queryUserOrderOfWX(vo.getOutTradeNo()))){
        	logger.info("查询服务端订单状态是成功");
        	 app.setRetnCode(0);
             app.setRetnDesc("支付成功");
         } else {
             app.setRetnCode(1);
             app.setRetnDesc("支付失败");
         }
        return app;
    }
}
