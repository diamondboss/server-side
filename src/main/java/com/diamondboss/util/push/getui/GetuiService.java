package com.diamondboss.util.push.getui;

import com.diamondboss.util.tools.PropsUtil;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzifu on 2017/6/10.
 */
public class GetuiService {
    private static final Logger logger = LoggerFactory.getLogger(GetuiService.class);

    private static final String appId = PropsUtil.getProperty("getui.appId");
    private static final String appKey = PropsUtil.getProperty("getui.appKey");
    private static final String masterSecret = PropsUtil.getProperty("getui.masterSecret");
    private static final String host = PropsUtil.getProperty("getui.host");


    /**
     * 向个人发送通知中心消息
     * @param clientId
     * @param template 消息模板
     * @return
     */
    public static boolean pushToSingle(String clientId, LinkTemplate template){

        SingleMessage message = new SingleMessage();
        message.setData(template);
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);

        IGtPush push = new IGtPush(host, appKey, masterSecret);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(clientId);
        IPushResult ret = null;

        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            logger.error("pushToSingle error,retry automatic", e.getMessage());
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }

        if (ret != null) {
            logger.error("pushToSingle success !");
            return true;
        } else {
            logger.error("pushToSingle error");
            return false;
        }
    }
}
