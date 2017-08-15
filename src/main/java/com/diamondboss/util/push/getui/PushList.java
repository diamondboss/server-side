package com.diamondboss.util.push.getui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.diamondboss.order.vo.PartnerClientVo;
import com.diamondboss.util.tools.PropsUtil;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
public class PushList {
    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	private static String appId = PropsUtil.getProperty("getui.appId");
	private static String appKey = PropsUtil.getProperty("getui.appKey");
	private static String masterSecret = PropsUtil.getProperty("getui.masterSecret");

    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    
	public static void pushListToUser(List<PartnerClientVo> pertnerClientList, Map<String, String> map) {

		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin_pushList_needDetails", "true");
		// 配置返回每个别名及其对应cid的用户状态，可选
		// System.setProperty("gexin_pushList_needAliasDetails", "true");
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		// 通知透传模板
		TransmissionTemplate template = TransmissionTemplateTestIos.getTemplate(map);
		ListMessage message = new ListMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 配置推送目标
		List targets = new ArrayList();

		for (PartnerClientVo partnerClient : pertnerClientList) {
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(partnerClient.getClientId());

			targets.add(target);
		}
		// taskId用于在推送时去查找对应的message
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		System.out.println(ret.getResponse().toString());
	}
 
}