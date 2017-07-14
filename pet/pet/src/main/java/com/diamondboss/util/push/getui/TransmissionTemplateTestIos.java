package com.diamondboss.util.push.getui;

import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class TransmissionTemplateTestIos {
	public static TransmissionTemplate getTemplate() {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId("wDV62wDmLT6zpGoL5gsHC");
	    template.setAppkey("Z1eOXz6SSNAoSXNV7h4UP8");
	    template.setTransmissionContent("透传内容");
	    template.setTransmissionType(2);
	    APNPayload payload = new APNPayload();
	    //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
	    payload.setAutoBadge("+1");
	    payload.setContentAvailable(1);
	    payload.setSound("default");
	    payload.setCategory("$由客户端定义");

	    //简单模式APNPayload.SimpleMsg
	    payload.setAlertMsg(new APNPayload.SimpleAlertMsg("你好，帅总，这是对全部用户发送，请打开APP查看哦！"));

	    //字典模式使用APNPayload.DictionaryAlertMsg
	    //payload.setAlertMsg(getDictionaryAlertMsg());

	    // 添加多媒体资源
	    payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.video)
	                .setResUrl("http://ol5mrj259.bkt.clouddn.com/test2.mp4")
	                .setOnlyWifi(true));

	    template.setAPNInfo(payload);
	    return template;
	}
	private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(){
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody("body");
	    alertMsg.setActionLocKey("ActionLockey");
	    alertMsg.setLocKey("LocKey");
	    alertMsg.addLocArg("loc-args");
	    alertMsg.setLaunchImage("launch-image");
	    // iOS8.2以上版本支持
	    alertMsg.setTitle("Title");
	    alertMsg.setTitleLocKey("TitleLocKey");
	    alertMsg.addTitleLocArg("TitleLocArg");
	    return alertMsg;
	}
}
