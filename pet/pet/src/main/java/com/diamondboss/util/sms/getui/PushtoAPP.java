package com.diamondboss.util.sms.getui;

import java.util.ArrayList;
import java.util.List;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.style.Style0;

public class PushtoAPP {    
    //����"Java SDK ��������"�� "�ڶ��� ��ȡ����ƾ֤ "�л�õ�Ӧ�����ã��û����������滻
    private static String appId = "";
    private static String appKey = "";
    private static String masterSecret = "";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    public static void main(String[] args) throws Exception {

        IGtPush push = new IGtPush(host, appKey, masterSecret);

        LinkTemplate template = linkTemplateDemo();
        AppMessage message = new AppMessage();
        message.setData(template);

        message.setOffline(true);
        //������Чʱ�䣬��λΪ���룬��ѡ
        message.setOfflineExpireTime(24 * 1000 * 3600);
        //���͸�App��Ŀ���û���Ҫ���������
        AppConditions cdt = new AppConditions(); 
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(appId);
        message.setAppIdList(appIdList);
        //�ֻ�����
        List<String> phoneTypeList = new ArrayList<String>();
        //ʡ��
        List<String> provinceList = new ArrayList<String>();
        //�Զ���tag
        List<String> tagList = new ArrayList<String>();

        cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
        cdt.addCondition(AppConditions.REGION, provinceList);
        cdt.addCondition(AppConditions.TAG,tagList);
        message.setConditions(cdt); 

        IPushResult ret = push.pushMessageToApp(message,"�������_toApp");
        System.out.println(ret.getResponse().toString());
    }

    public static LinkTemplate linkTemplateDemo() throws Exception {
        LinkTemplate template = new LinkTemplate();
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

        template.setUrl("http://www.baidu.com");

        return template;
    }
}