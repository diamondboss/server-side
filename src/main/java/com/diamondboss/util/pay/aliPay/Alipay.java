package com.diamondboss.util.pay.aliPay;

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

	public static String refund(){
		String str = "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017061207471974&biz_content=%7B%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%E7%9A%84%E6%8F%8F%E8%BF%B0%E4%BF%A1%E6%81%AF%22%2C%22out_trade_no%22%3A%2215orderPay1synull7687%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%E7%9A%84%E4%BA%A4%E6%98%93%E6%A0%87%E9%A2%98%22%2C%22timeout_express%22%3A%225m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=182.92.149.119%3A8080%2Fapp%2Fali%2FpayConfirm&sign=OLCYdnIFneA35PNAwYyfk13QNXhxUGudpudP2tph8Jcqsi01X4gwgUgDtgTXd%2FO96HGBPzB0hjdBsfn5oen9H5tTBHichNu97OkKbD9fJaYlw3Kz536kt3E9yL4HJkFBGMP2L%2B%2FMSEtSyO%2BrzngfJLV7o5inJPp28nYb%2BbvCy1XHjmUAsgs0PNW8PM3hkVI3PwMypz3fIcVXtHydsESD%2F8I1FIHBiSughGorw1zRleHqEMrChatI6igiXSJenhHRvwEYx9Kh92wE0Vcu2mRtB4z1giY7EbbBx4G0DuENCw0LKC6w7wjAe3TkPth6GRBqxZWtnYIJCXNUsNXjSYA0Fg%3D%3D&sign_type=RSA2&timestamp=2017-07-01+17%3A23%3A39&version=1.0\n";

		return "";
	}


	public static void main(String[] args){
		Thread queryThread = new Thread(new QueryAlipayTradeStatus("2017070221001004940279085648"));
		queryThread.start();

		System.out.println(queryThread.getName());
	}
	
}
