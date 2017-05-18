package com.diamondboss.util.tools;

import java.util.UUID;

public class UUIDUtil {

	 public UUIDUtil() { 
	    } 
	 
    /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
    
    /** 
     * 通过UUID获得一个8位用户标识
     * @return String UUID 
     */ 
    public static String getOrderIdByUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return "游客" + s.substring(0,8); 
    } 
    
}
