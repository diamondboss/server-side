package com.diamondboss.util.sms.rongyun.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * 
 * All rights Reserved, Designed By alsfox.com   
 * @ClassName:  StringTools   
 * @Description:TODO(String字符串常用操作工具)   
 * @author: Lynn  
 * @date:   2016年9月6日 上午10:36:24   
 *
 */
public class StringTools {

	/**
	 * 
	 * @Title: isEmpty   
	 * @Description: TODO(判断传入数据是否为空)   
	 * @param: @param str
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isEmpty(String str) {

		if (null == str || "".equals(str) || "null".equals(str)) {
			return true;
		} else if ("".equals(str.trim())) {
			return true;
		}
		return false;
	}

	
	/**
	 * 
	 * @Title: isNumber   
	 * @Description: TODO(判断传入数据是否为数字)   
	 * @param: @param str
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isNumber(String str) {
		String checkPassWord = "^[0-9]+$";
		if (null == str || "".equals(str) || "null".equals(str)) {
			return false;
		}
		if (!str.matches(checkPassWord)) {
			return false;
		}

		return true;
	}
	
	
	/**
	 * 
	 * @Title: isDouble   
	 * @Description: TODO(判断传入数据是否为Double类型)   
	 * @param: @param str
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isDouble(String str){
		if (null == str || "".equals(str) || "null".equals(str)) {
			return false;
		}
		try{
			Double.parseDouble(str);
			return true;
		}catch(NumberFormatException ex){
			
		}
		return false;
	}

	
	/**
	 * 
	 * @Title: checkEmail   
	 * @Description: TODO(判断传入数据是否为邮箱格式)   
	 * @param: @param email
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean checkEmail(String email) {
		String checkEmail = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
		if (null == email || "".equals(email) || "null".equals(email)) {
			return false;
		}else if(email.indexOf("@")<3){
			return false;
		}else {
			return email.matches(checkEmail);
		}
	}

	 /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */  
    public static boolean checkPhoneNo(String str) throws PatternSyntaxException {  
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        if(null == str || "".equals(str) || "null".equals(str)){
        	return false;
        }else{
        	Pattern p = Pattern.compile(regExp);  
        	Matcher m = p.matcher(str);  
        	return m.matches();  
        }
    }  
    
	/**
	 * 
	 * @Title: addLink   
	 * @Description: TODO(为文中地址字符串添加链接)   
	 * @param: @param str
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String addLink(String str) {
		String regex = "((http:|https:)//|www.)[^[A-Za-z0-9\\._\\?%&+\\-=/#]]*";
		Pattern pattern = Pattern.compile(regex);
		if (null == str || "".equals(str) || "null".equals(str)) {
			return str;
		}else{
			Matcher matcher = pattern.matcher(str);
			StringBuffer result = new StringBuffer();
			while (matcher.find()) {
				StringBuffer replace = new StringBuffer();
				if (matcher.group().contains("http")) {
					replace.append("<a href=").append(matcher.group().replace("nbsp;", ""));
				} else {
					replace.append("<a href=http://").append(matcher.group().replace("nbsp;", ""));
				}
				replace.append(" target=\"_blank\">" + matcher.group() + "</a>");
				matcher.appendReplacement(result, replace.toString());
			}
			matcher.appendTail(result);
			return result.toString();
		}
	}


	/**
	 * 
	 * clearHtml:(清除html标签). <br/>
	 *
	 * @param str
	 * @return
	 * @since JDK 1.7
	 */
	public static String clearHtmlTag(String str) {
		if (!isEmpty(str)) {
			Pattern p = Pattern.compile("<[.[^<]]*>");
			Matcher m = p.matcher(str);
			StringBuffer sb = new StringBuffer();
			while (m.find()) {
			    m.appendReplacement(sb, m.group().replace("<", "&lt;").replace(">", "&gt;"));
			}
			m.appendTail(sb);
			return sb.toString();
		} else {
			return str;
		}
	}
	

	/**
	 * 
	 * @Title: getRandoForSix   
	 * @Description: TODO(产生随机的六位数)   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getRandomForSix(){
		return (int)(Math.random()*899999+100000)+"";
	}
	
	
	/**
	 * 
	 * @Title: getRandomOrderNo   
	 * @Description: TODO(随机获取订单编号)   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getRandomOrderNo(String prefixStr){
		String uuId = java.util.UUID.randomUUID()+"";
		String time = System.currentTimeMillis()+"";
		String randomSix = getRandomForSix();
		String result = MD5Utils.MD5(uuId+time+randomSix);
		return prefixStr+result.substring(8, 24);
	}
	
	
	public static String stripXSS(String value) {
        if (value != null) {
        	
            // Avoid null characters
            value = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of e­xpression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) e­xpressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid e­xpression(...) e­xpressions
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... e­xpressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... e­xpressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= e­xpressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }
	
	
	public static String madeRandomPwd(int length) {
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	
	/**
	 * String 模板数据适配，占位符为{0},{1},{2}...
	 */
	public static String StringTemplate(String srcStr,Object...obj){
		if(srcStr != null && !"".equals(srcStr)){
			if(obj != null && obj.length > 0){
				for(int i = 0;i < obj.length;i++){
					srcStr = srcStr.replace("{"+i+"}", obj[i]+"");
				}
			}
		}
		return srcStr;
	}
	
	/**
	 * 从Json字符串中取出指定数据的值
	 */
	public static String getJsonForAttr(String jsonStr,String attr){
		if(jsonStr != null && !"".equals(jsonStr)){
			JsonParser parser = new JsonParser(); 
			JsonObject jsonObject = parser.parse(jsonStr).getAsJsonObject();
			JsonElement jsonElement = jsonObject.get(attr);
			if(jsonElement != null){
				return jsonElement.getAsString();
			}
		}
		return "";
	}
	
}
