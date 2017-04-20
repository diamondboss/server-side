package com.personal.util.tools.jedis;  
  
import java.util.HashMap;  
import java.util.Map;  
  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;  
import redis.clients.jedis.JedisPool;  
import redis.clients.jedis.JedisPoolConfig;  
  

/** 
 * Redis工具�?,用于获取RedisPool. 
 * 参�?�官网说明如下： 
 * You shouldn't use the same instance from different threads because you'll have strange errors. 
 * And sometimes creating lots of Jedis instances is not good enough because it means lots of sockets and connections, 
 * which leads to strange errors as well. A single Jedis instance is not threadsafe! 
 * To avoid these problems, you should use JedisPool, which is a threadsafe pool of network connections. 
 * This way you can overcome those strange errors and achieve great performance. 
 * To use it, init a pool: 
 *  JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost"); 
 *  You can store the pool somewhere statically, it is thread-safe. 
 *  JedisPoolConfig includes a number of helpful Redis-specific connection pooling defaults. 
 *  For example, Jedis with JedisPoolConfig will close a connection after 300 seconds if it has not been returned. 
 * @author wujintao 
 */  
public class JedisUtil  {  
    protected Logger log = LoggerFactory.getLogger(getClass());  
      
    /** 
     * 私有构�?�器. 
     */  
    private JedisUtil() {  
          
    }  
    private static Map<String,JedisPool> maps  = new HashMap<String,JedisPool>();  
      
      
    /** 
     * 获取连接�?. 
     * @return 连接池实�? 
     */  
    private static JedisPool getPool(String ip,int port) {  
        String key = ip+":" +port;  
        JedisPool pool = null;  
        if(!maps.containsKey(key)) {  
            JedisPoolConfig config = new JedisPoolConfig();  
            config.setMaxIdle(200);
            //�?大连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例�?大的连接�?
            config.setMaxTotal(300);
            config.setTestOnBorrow(false);
            config.setTestOnReturn(false);
           
            try{    
                /** 
                 *如果你遇�? java.net.SocketTimeoutException: Read timed out exception的异常信�? 
                 *请尝试在构�?�JedisPool的时候设置自己的超时�?. JedisPool默认的超时时间是2�?(单位毫秒) 
                 */  
                pool =RedisAPI.getPool();
                maps.put(key, pool);  
            } catch(Exception e) {  
                e.printStackTrace();  
            }  
        }else{  
            pool = maps.get(key);  
        }  
        return pool;  
    }  
  
    /** 
     *类级的内部类，也就是静�?�的成员式内部类，该内部类的实例与外部类的实�? 
     *没有绑定关系，�?�且只有被调用到时才会装载，从�?�实现了延迟加载�? 
     */  
    private static class RedisUtilHolder{  
        /** 
         * 静�?�初始化器，由JVM来保证线程安�? 
         */  
        private static JedisUtil instance = new JedisUtil();  
    }  
  
    /** 
     *当getInstance方法第一次被调用的时候，它第�?次读�? 
     *RedisUtilHolder.instance，导致RedisUtilHolder类得到初始化；�?�这个类在装载并被初始化的时候，会初始化它的�? 
     *态域，从而创建RedisUtil的实例，由于是静态的域，因此只会在虚拟机装载类的时�?�初始化�?次，并由虚拟机来保证它的线程安全性�?? 
     *这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本�? 
     */  
    public static JedisUtil getInstance() {  
        return RedisUtilHolder.instance;  
    }  
      
    /** 
     * 获取Redis实例. 
     * @return Redis工具类实�? 
     */  
    @SuppressWarnings("deprecation")
	public Jedis getJedis(String ip,int port) {  
        Jedis jedis  = null;  
        int count =0;  
        do{  
            try{   
                jedis = getPool(ip,port).getResource();  
                //log.info("get redis master1!");  
            } catch (Exception e) {  
                log.error("get redis master1 failed!", e);  
                 // �?毁对�?    
                getPool(ip,port).returnBrokenResource(jedis);    
            }  
            count++;  
        }while(jedis==null&&count<10);  
        return jedis;  
    }  
  
    /** 
     * 释放redis实例到连接池. 
     * @param jedis redis实例 
     */  
    @SuppressWarnings("deprecation")
	public void closeJedis(Jedis jedis,String ip,int port) {  
        if(jedis != null) {  
            getPool(ip,port).returnResource(jedis);  
        }  
    }  
}  