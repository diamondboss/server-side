package com.diamondboss.util.tools;

import java.util.Random;
import java.util.UUID;

import com.diamondboss.util.pojo.OutTradeNoPojo;

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
     * 生成一个uuid
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
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
    
    /**
     * 标志 + 用户表 xx + 分隔符 + 对应表id + 随机数
     */
    public static String makeTradeNo(int tableId, String id){
    	Random rd = new Random();
    	
    	String s = "orderPay" + tableId + "sy" + id;
    	String sb = s.length() + s + rd.nextInt(9999); 
    	return sb;
    }
    
    /**
     * 标志 + 用户表 xx + 分隔符 + 对应表id + 随机数
     */
    public static OutTradeNoPojo getInfoFromTradeNo(String outTradeNo){
    	
    	String[] s = outTradeNo.split("orderPay");
    	String[] st = s[1].split("sy");
    	int len = Integer.valueOf(s[0]) - 10 - st[0].length()  ;
    	
    	OutTradeNoPojo pojo = new OutTradeNoPojo();
    	pojo.setId(st[1].substring(0, len));
    	pojo.setTableId(st[0]);
    	
    	return pojo;
    }
    
    public static void main(String[] args) {
    	String s = makeTradeNo(19,"49953245");
    	System.out.println(s);
    
    	System.out.println(getInfoFromTradeNo(s).toString());
	}
    
    
}
