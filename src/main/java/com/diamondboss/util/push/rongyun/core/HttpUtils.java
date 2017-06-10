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
     * @return 返回结果
     * @throws IOException
     */
    public static String sendPost(Map params, String requestUrl/*,
            String authorization*/) throws IOException {

        byte[] params1 = ((String) params.get("mobile")).getBytes("utf-8"); // 将参数转为二进制流
        byte[] params2 = "001".getBytes("utf-8"); // 将参数转为二进制流
        byte[] params3 = "86".getBytes("utf-8"); // 将参数转为二进制流
        
        byte[] requestBytes = new byte[params1.length + params2.length + params3.length];  
        System.arraycopy(params1, 0, requestBytes, 0, params1.length);  
        System.arraycopy(params2, 0, requestBytes, params1.length, params2.length);
        System.arraycopy(params3, 0, requestBytes, params2.length, params3.length);
        
        HttpClient httpClient = new HttpClient();// 客户端实例化
        PostMethod postMethod = new PostMethod(requestUrl);
        //设置请求头Authorization
        //postMethod.setRequestHeader("Authorization", "Basic " + authorization);
        // 设置请求头  Content-Type
        postMethod.setRequestHeader("Content-Type", "application/json");
        
        //设置请求头App-Key
        postMethod.setRequestHeader("App-Key", (String) params.get("appKey"));
        //设置请求头Nonce
        postMethod.setRequestHeader("Nonce", (String) params.get("nonce"));
        //设置请求头Timestamp
        postMethod.setRequestHeader("Timestamp", (String) params.get("Timestamp"));
        //设置请求头Signature
        postMethod.setRequestHeader("Signature", (String) params.get("hash"));
        
        InputStream inputStream = new ByteArrayInputStream(requestBytes, 0,
                requestBytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
                requestBytes.length, "application/json; charset=utf-8"); // 请求体
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
        // 打印返回结果
        System.out.println(result);

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
