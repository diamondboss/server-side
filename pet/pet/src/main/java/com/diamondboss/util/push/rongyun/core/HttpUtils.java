package com.diamondboss.util.push.rongyun.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
/**
 * http发送工具类
 * @author zfxue
 *
 */
public class HttpUtils {
	/**
     * 发送post请求
     * 
     * @author zfxue
     * @param params  参数
     * @param requestUrl 请求地址
     * @param isVerify 是否是验证：false-发短信，true-验证
     * @return 返回结果
     * @throws IOException
     */
    public static String sendPost(Map params, String requestUrl, int flag) throws IOException {
        byte[] requestBytes = null;
        if (flag == 1){
            byte[] params1 = ("sessionId=" + params.get("sessionId") + "&").getBytes("utf-8"); // 将参数转为二进制流
            byte[] params2 = ("code=" + params.get("code")).getBytes("utf-8"); // 将参数转为二进制流

            requestBytes = new byte[params1.length + params2.length];
            System.arraycopy(params1, 0, requestBytes, 0, params1.length);
			System.arraycopy(params2, 0, requestBytes, params1.length, params2.length);
		} else if (flag == 0) {
			byte[] params1 = ("mobile=" + params.get("mobile") + "&").getBytes("utf-8"); // 将参数转为二进制流
			byte[] params2 = ("templateId=" + params.get("templateId") + "&").getBytes("utf-8"); // 将参数转为二进制流
			byte[] params3 = ("region=" + params.get("region")).getBytes("utf-8"); // 将参数转为二进制流

			requestBytes = new byte[params1.length + params2.length + params3.length];
			System.arraycopy(params1, 0, requestBytes, 0, params1.length);
			System.arraycopy(params2, 0, requestBytes, params1.length, params2.length);
			System.arraycopy(params3, 0, requestBytes, params1.length + params2.length, params3.length);
		} else if(flag == 2){
			byte[] params1 = ("userId=" + params.get("userId") + "&").getBytes("utf-8"); // 将参数转为二进制流
			byte[] params2 = ("name=" + params.get("name") + "&").getBytes("utf-8"); // 将参数转为二进制流
			byte[] params3 = ("portraitUri=" + params.get("portraitUri")).getBytes("utf-8"); // 将参数转为二进制流

			requestBytes = new byte[params1.length + params2.length + params3.length];
			System.arraycopy(params1, 0, requestBytes, 0, params1.length);
			System.arraycopy(params2, 0, requestBytes, params1.length, params2.length);
			System.arraycopy(params3, 0, requestBytes, params1.length + params2.length, params3.length);
		}else if(flag == 3){
			byte[] params1 = ("region=" + params.get("region") + "&").getBytes("utf-8"); // 将参数转为二进制流
			byte[] params2 = ("mobile=" + params.get("mobile") + "&").getBytes("utf-8"); // 将参数转为二进制流
			byte[] params3 = ("templateId=" + params.get("templateId") + "&").getBytes("utf-8"); // 将参数转为二进制流
			byte[] params4 = ("p1=" + params.get("p1") + "&").getBytes("utf-8"); // 将参数转为二进制流
			byte[] params5 = ("p2=" + params.get("p2") + "&").getBytes("utf-8"); // 将参数转为二进制流
			byte[] params6 = ("p3=" + params.get("p3") + "&").getBytes("utf-8"); // 将参数转为二进制流

			requestBytes = new byte[params1.length + params2.length + params3.length + params4.length + params5.length + params6.length];
			System.arraycopy(params1, 0, requestBytes, 0, params1.length);
			System.arraycopy(params2, 0, requestBytes, params1.length, params2.length);
			System.arraycopy(params3, 0, requestBytes, params1.length + params2.length, params3.length);
			System.arraycopy(params4, 0, requestBytes, params1.length + params2.length + params3.length, params4.length);
			System.arraycopy(params5, 0, requestBytes, params1.length + params2.length + params3.length + params4.length, params5.length);
			System.arraycopy(params6, 0, requestBytes, params1.length + params2.length + params3.length + params4.length + params5.length, params6.length);
		}

        
        HttpClient httpClient = new HttpClient();// 客户端实例化
        PostMethod postMethod = new PostMethod(requestUrl);
        // 设置请求头  Content-Type
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        
        //设置请求头App-Key
        postMethod.setRequestHeader("App-Key", (String) params.get("appKey"));
        //设置请求头Nonce
        postMethod.setRequestHeader("Nonce", (String) params.get("nonce"));
        //设置请求头Timestamp
        postMethod.setRequestHeader("Timestamp", (String) params.get("timestamp"));
        //设置请求头Signature
        postMethod.setRequestHeader("Signature", (String) params.get("hash"));
        
        InputStream inputStream = new ByteArrayInputStream(requestBytes, 0,
                requestBytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
                requestBytes.length, "application/x-www-form-urlencoded"); // 请求体
        postMethod.setRequestEntity(requestEntity);
        httpClient.executeMethod(postMethod);// 执行请求
        InputStream soapResponseStream = postMethod.getResponseBodyAsStream();// 获取返回的流
        byte[] datas = null;
        try {
            datas = readInputStream(soapResponseStream);// 从输入流中读取数据
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = new String(datas, "UTF-8");// 将二进制流转为String
        return result;

    }
    
    /**
     * 从输入流中读取数据
     * 
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }
}
