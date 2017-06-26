package com.diamondboss.util.pay.aliPay;

import java.util.Map;

import com.diamondboss.util.tools.PropsUtil;
import org.apache.log4j.Logger;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

public class Alipay {

	private static final Logger logger = Logger.getLogger(Alipay.class);
	
	
	private static final String ALIPAY_PUBLIC_KEY = PropsUtil.getProperty("alipay.publicKey");
	private static final String CHARSET = PropsUtil.getProperty("alipay.charset");
	private static final String SIGNTYPE = PropsUtil.getProperty("alipay.signType");
	//实例化客户端，只需要初始化一次，后续调用不同的API都可以使用同一个alipayClient对象
	private static  AlipayClient alipayClient = new DefaultAlipayClient(PropsUtil.getProperty("alipay.serverUrl"),
			PropsUtil.getProperty("alipay.appId"), PropsUtil.getProperty("alipay.app.privateKey"),
			PropsUtil.getProperty("alipay.format"), PropsUtil.getProperty("alipay.charset"),
			PropsUtil.getProperty("alipay.publicKey"), PropsUtil.getProperty("alipay.signType"));

	/**
	 * 获得支付订单
	 * @param model
	 * @param notifyurl
	 * @return
	 */
	public static String getPreOrder(AlipayTradeAppPayModel model, String notifyurl){
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		request.setBizModel(model);
		request.setNotifyUrl(notifyurl);
		String respBody = "";
		try {
				//这里和普通的接口调用不同，使用的是sdkExecute
				AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
				//orderString 可以直接给客户端请求，无需再做处理。
				respBody = response.getBody();
				logger.info("支付宝支付订单："+response.getBody());
		    } catch (AlipayApiException e) {
				logger.error("支付宝支付订单获取错误");
				logger.error(e.getMessage());
		}
		return respBody;
	}

	public static boolean checkAlipayRequest(Map<String,String> params){
		//获取支付宝POST过来反馈信息
		boolean flag = false;
//		Map<String,String> params = new HashMap<>();
//		Map requestParams = request.getParameterMap();
//		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//		    String name = (String) iter.next();
//		    String[] values = (String[]) requestParams.get(name);
//		    String valueStr = "";
//		    for (int i = 0; i < values.length; i++) {
//		        valueStr = (i == values.length - 1) ? valueStr + values[i]
//		                    : valueStr + values[i] + ",";
//		 	 }
//		//乱码解决，这段代码在出现乱码时使用。
//		//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//		params.put(name, valueStr);
//		}
		//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		try {
			flag = AlipaySignature.rsaCheckV1(params, PropsUtil.getProperty("alipay.publicKey"),
					PropsUtil.getProperty("alipay.charset"), PropsUtil.getProperty("alipay.signType"));
		} catch (AlipayApiException e) {
			logger.error("校验支付宝的请求失败");
			logger.error(e.getMessage());
		}
		return flag;
	}
	
	
}
