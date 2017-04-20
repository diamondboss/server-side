package com.personal.util.tools.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**   
 * Redis操作接口
 *
 * @author zfxue
 * @version 1.0 2013-6-14 上午08:54:14   
 */
public class RedisAPI {
    private static JedisPool pool = null;
    
    /**
     * 构建redis连接�?
     * 
     * @param ip
     * @param port
     * @return JedisPool
     */
    public static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            //控制�?个pool可分配多少个jedis实例，�?�过pool.getResource()来获取；
            //如果赋�?�为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)�?
           // config.setMaxActive(500);
            //控制�?个pool�?多有多少个状态为idle(空闲�?)的jedis实例�?
            config.setMaxIdle(5);
            //表示当borrow(引入)�?个jedis实例时，�?大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException�?
           // config.setMaxWait(1000 * 100);
            //在borrow�?个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(false);
            pool = new JedisPool(config, "182.92.149.119", 6379);
        }
        return pool;
    }
    
    /**
     * 返还到连接池
     * 
     * @param pool 
     * @param redis
     */
    @SuppressWarnings("deprecation")
	public static void returnResource(JedisPool pool, Jedis redis) {
        if (redis != null) {
            pool.returnResource(redis);
        }
    }
    
    /**
     * 获取数据
     * 
     * @param key
     * @return
     */
    @SuppressWarnings("deprecation")
	public static String get(String key){
        String value = null;
        
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
        
        return value;
    }
}