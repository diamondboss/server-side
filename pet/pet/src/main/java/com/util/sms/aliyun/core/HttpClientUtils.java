package com.util.sms.aliyun.core;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 
 * All rights Reserved, Designed By alsfox.com   
 * @ClassName:  HttpClientUtil   
 * @Description:TODO(HTTPClient工具类)   
 * @author: Lynn  
 * @date:   2016年9月6日 下午3:05:06   
 *
 */
public class HttpClientUtils {
	
	// 日志记录器
	private final static Logger logger = Logger.getLogger(HttpClientUtils.class);
	
	// 锁对象
	private static final Object lock = new Object();
	
	// 工具对象实例
	private static HttpClientUtils instance;
	
	// 连接管理
	private PoolingHttpClientConnectionManager connManager;
	private ConnectionConfig connConfig;
	private SocketConfig socketConfig;
	
	/**
	 * 请求编码，使用utf-8
	 */
	private String urlCharset = "UTF-8";
	
	
	private HttpClientUtils(){
		connConfig = ConnectionConfig.custom().setCharset(Charset.forName(urlCharset)).build();
		socketConfig = SocketConfig.custom().setSoTimeout(100000).build();
		connManager = new PoolingHttpClientConnectionManager();
		connManager.setDefaultConnectionConfig(connConfig);
		connManager.setDefaultSocketConfig(socketConfig);
	}
	
	
	/**
	 * 
	 * @Title: getInstance   
	 * @Description: TODO(获取工具对象实例)   
	 * @param: @param urlCharset
	 * @param: @return      
	 * @return: HttpClientUtils      
	 * @throws
	 */
	public static HttpClientUtils getInstance(){
		synchronized (lock) {
			if(instance == null){
				instance = new HttpClientUtils();
			}
			return instance;
		}
	}
	
	
	/**
	 * 
	 * @Title: smsSendMesage   
	 * @Description: TODO(短信通道发送短信)   
	 * @param:      
	 * @return: String      
	 * @throws
	 */
	public String smsSendMesage(String targetUrl,Map<String,String> params){
		logger.info("发送短信请求地址：" + targetUrl);
		CloseableHttpClient client = HttpClientBuilder.create().setConnectionManager(connManager).build();
		HttpPost httpost = new HttpPost(targetUrl);
        try {
        	if (params != null) {  
        		HttpEntity parmEntity = null;
        		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        		for (String key : params.keySet()) {  
        			formparams.add(new BasicNameValuePair(key, params.get(key)));  
        		}  
        		parmEntity = new UrlEncodedFormEntity(formparams, urlCharset);
        		httpost.setEntity(parmEntity);  
        	} 
        	CloseableHttpResponse response = client.execute(httpost);
			try{
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					if (response.getEntity() != null) {  
						String result = EntityUtils.toString(response.getEntity(), urlCharset);  
						logger.info("发送短信返回结果：" + result);
						return result;
			        }
				}
			}finally{
				response.close();
			}
		} catch (Exception e) {
			logger.error("发送短信请求地址：" + targetUrl , e);
		}finally{
			httpost.releaseConnection();
		}
        return "";
	}
	
	
	
	
}
