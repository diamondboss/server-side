package com.diamondboss.util.tools;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzifu on 2017/7/6.
 */
public abstract class HttpUtils {
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final Logger logger = Logger.getLogger(HttpUtils.class);

    public HttpUtils() {
    }

    public static String doPost(String url, String body) throws Exception {
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(url);
            HttpEntity entity = new ByteArrayEntity(body.getBytes("utf-8"));
            httpPost.setEntity(entity);
            CloseableHttpResponse response = null;

            try {
                response = client.execute(httpPost);
                if(response.getStatusLine().getStatusCode() == 200) {
                    result = EntityUtils.toString(response.getEntity(), "UTF-8");
                }
            } catch (Exception var16) {
                logger.error("do post for url:"+ url +",body:"+ body +" error:"+ var16.getMessage());
                throw var16;
            } finally {
                closeQuietly(response);
            }
        } finally {
            closeQuietly(client);
        }

        return result;
    }

    public static String doPost(String url, Map<String, Object> params) throws Exception {
        List<NameValuePair> paris = new ArrayList();
        Iterator var3 = params.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, Object> param = (Map.Entry)var3.next();
            paris.add(new BasicNameValuePair((String)param.getKey(), param.getValue().toString()));
        }

        return doPost(url, (List)paris);
    }

    public static String doPost(String url, List<NameValuePair> list) throws Exception {
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(url);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = null;

            try {
                response = client.execute(httpPost);
                if(response.getStatusLine().getStatusCode() == 200) {
                    result = EntityUtils.toString(response.getEntity(), "utf-8");
                }
            } catch (Exception var16) {
                logger.error("do post for url:" + url + ",body:" + list + " error:" + var16.getMessage());
                throw var16;
            } finally {
                closeQuietly(response);
            }
        } finally {
            closeQuietly(client);
        }

        return result;
    }

    public static String doGet(String url) throws Exception {
        return doGet(url, 0);
    }

    public static String doGet(String url, int timeOut) throws Exception {
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();

        try {
            HttpGet http = new HttpGet(url);
            CloseableHttpResponse response = null;

            try {
                RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeOut).build();
                http.setConfig(requestConfig);
                response = client.execute(http);
                if(response.getStatusLine().getStatusCode() == 200) {
                    result = EntityUtils.toString(response.getEntity(), "UTF-8");
                }
            } catch (Exception var15) {
                logger.error("do post for url:"+ url +", error:"+ var15.getMessage());
                throw var15;
            } finally {
                closeQuietly(response);
            }
        } finally {
            closeQuietly(client);
        }

        return result;
    }

    public static void closeQuietly(Closeable closer) {
        try {
            if(closer != null) {
                closer.close();
            }
        } catch (IOException var2) {
            logger.error("io close error:"+ var2.getMessage());
        }

    }
}
