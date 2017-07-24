package com.diamondboss.util.pay.aliPay;

import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.diamondboss.threads.QueryAlipayTradeStatus;
import org.apache.log4j.Logger;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

import com.diamondboss.util.tools.PropsUtil;

public class Alipay {

	private static final Logger logger = Logger.getLogger(Alipay.class);
	
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

	public static String queryTradeStatus(String tradeNo){
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		AlipayTradeQueryModel model = new AlipayTradeQueryModel();
		model.setTradeNo(tradeNo);
		request.setBizModel(model);
		String tradeStatus = "";
		try {
			AlipayTradeQueryResponse response = alipayClient.execute(request);
			//交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
			// TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
			tradeStatus = response.getTradeStatus();
			logger.info("支付宝订单号"+tradeNo+ "的交易状态:"+tradeStatus);
		} catch (AlipayApiException e){
			logger.error("支付宝查询交易状态错误");
			logger.error(e.getMessage());
		}
		return tradeStatus;
	}

	public static boolean refund(String tradeNo){
		logger.info("进入支付宝退款方法");
		logger.info("进入支付宝退款方法--订单号--" + tradeNo);
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		AlipayTradeRefundModel model = new AlipayTradeRefundModel();
		model.setTradeNo(tradeNo);
		model.setRefundAmount("0.01");
		
		
		boolean refundFlag = false;
		try {
			AlipayTradeRefundResponse response = alipayClient.execute(request);
			refundFlag = response.isSuccess();
		} catch (AlipayApiException e) {
			logger.error("支付宝退款错误：");
			logger.error(e.getMessage());
		}
		return refundFlag;
	}


	public static void main(String[] args){
		//Thread queryThread = new Thread(new QueryAlipayTradeStatus("2017070221001004940279085648"));
		//queryThread.start();

		//System.out.println(queryThread.getName());
	}
	
}
