package com.diamondboss.util.tools;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Desc:properties文件获取工具类
 * Created by liuzifu on 2017/6/14.
 */
public class PropsUtil {
    private static final Logger logger = Logger.getLogger(PropsUtil.class);
    private static Properties props;
    static {
        loadProps();
    }

    synchronized static private void loadProps(){
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
            //通过类加载器进行获取properties文件流-->
            in = PropsUtil.class.getClassLoader().getResourceAsStream("product.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("product.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("product.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props);
    }

    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

}
