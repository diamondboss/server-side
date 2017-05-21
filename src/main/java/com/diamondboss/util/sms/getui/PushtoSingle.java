package com.diamondboss.util.sms.getui;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.style.Style0;

public class PushtoSingle {
    //����"Java SDK ��������"�� "�ڶ��� ��ȡ����ƾ֤ "�л�õ�Ӧ�����ã��û����������滻
    private static String appId = "";
    private static String appKey = "";
    private static String masterSecret = "";

    static String CID = "";
  //�������ͷ�ʽ
   // static String Alias = "";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    public static void main(String[] args) throws Exception {
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        LinkTemplate template = linkTemplateDemo();
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // ������Чʱ�䣬��λΪ���룬��ѡ
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // ��ѡ��1Ϊwifi��0Ϊ���������绷���������ֻ����ڵ���������������Ƿ��·�
        message.setPushNetWorkType(0); 
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(CID);
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
            System.out.println("��������Ӧ�쳣");
        }
    }
    public static LinkTemplate linkTemplateDemo() {
        LinkTemplate template = new LinkTemplate();
        // ����APPID��APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // ����֪ͨ������������
        style.setTitle("������֪ͨ������");
        style.setText("������֪ͨ������");
        // ����֪ͨ��ͼ��
        style.setLogo("icon.png");
        // ����֪ͨ������ͼ��
        style.setLogoUrl("");
        // ����֪ͨ�Ƿ����壬�𶯣����߿����
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // ���ô򿪵���ַ��ַ
        template.setUrl("http://www.baidu.com");
        return template;
    }
}