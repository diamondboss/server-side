package com.diamondboss.util.push.getui;

import java.util.HashMap;
import java.util.Map;
import com.diamondboss.util.tools.PropsUtil;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;

public class PushToSingle {
    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
    private static String appId = PropsUtil.getProperty("getui.appId");
    private static String appKey = PropsUtil.getProperty("getui.appKey");
    private static String masterSecret = PropsUtil.getProperty("getui.masterSecret");
	

    //别名推送方式
    // static String Alias = "";
    static String host = PropsUtil.getProperty("getui.host");
 
    public static void pushToSingle(Map<String, String> map){
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        TransmissionTemplate template = TransmissionTemplateTestIos.getTemplate(map);
        //LinkTemplate template = PushToSingle.linkTemplateDemo(map);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(map.get("CID"));
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }
    
    public static LinkTemplate linkTemplateDemo(Map<String, String> map) {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(map.get("title"));
        style.setText(map.get("text"));
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("http://zfxue-test.oss-cn-shanghai.aliyuncs.com/dbmap/logo.jpg");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 设置打开的网址地址
        template.setUrl(map.get("url"));
        return template;
    }
    
    public static void main(String[] args) {
    	Map<String, String> map = new HashMap<String, String>();
		map.put("CID", "17b1cbdf42373506e8d6246cedb8344f");
		map.put("title", "订单派送失败");
		map.put("text", "抱歉，您的订单派送失败！");
		map.put("url", "http://www.baidu.com");
		
		pushToSingle(map);
	}
}
