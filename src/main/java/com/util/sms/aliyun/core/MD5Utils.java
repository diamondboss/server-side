package com.util.sms.aliyun.core;

import java.net.URLEncoder;
import java.security.MessageDigest;


/**
 * 
 * All rights Reserved, Designed By alsfox.com   
 * @ClassName:  MD5Util   
 * @Description:TODO(AlsFox专用MD5工具)   
 * @author: Lynn  
 * @date:   2016年9月9日 下午4:57:56   
 *
 */
public class MD5Utils {
	
	public final static String MD5(String str) {
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
        	
        	str = URLEncoder.encode(str, "utf-8");
        	
            byte[] btInput = str.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char strs[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                strs[k++] = hexDigits[byte0 >>> 4 & 0xf];
                strs[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(strs);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
	
	public final static String MD5_FOR_NATIVE(String str) {
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
		try {
			
			byte[] btInput = str.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char strs[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				strs[k++] = hexDigits[byte0 >>> 4 & 0xf];
				strs[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(strs);
		} catch (Exception e) {
			e.printStackTrace();
			return str;
		}
	}
	
	public final static String MD516(String str) {
		return MD5(str).substring(9, 25);
	}
	
	public static void main(String[] args) {
		String str = "111111";
		System.out.println(MD5(str));
	}
	
}
