package com.personal.util.tools.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.personal.util.tools.jedis.RedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 工具�?
 * 
 * @author caspar http://blog.csdn.net/tuposky
 */
public class RedisUtil {

	public static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	private static JedisPool jedisPool = null;
	/**
	 * redis过期时间,以秒为单�?
	 */
	public final static int EXRP_HOUR = 60 * 60; // �?小时
	public final static int EXRP_DAY = 60 * 60 * 24; // �?�?
	public final static int EXRP_MONTH = 60 * 60 * 24 * 30; // �?个月

	/**
	 * 初始化Redis连接�?
	 */
	private static synchronized void initialPool(String ip) {
		int port = 6379;
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(2);  
			config.setMaxWaitMillis(5);  
			config.setTestOnBorrow(true);  
			

			config.setMaxTotal(200);
			config.setTestOnBorrow(false);

			port = 6379;

			jedisPool = new JedisPool(config, ip, port, 30000);
		} catch (Exception e) {
			logger.info("First create JedisPool error : "+e.getMessage() , e);
			try {
				// 如果第一个IP异常，则访问第二个IP
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxTotal(300);
				config.setTestOnBorrow(false);
				jedisPool = new JedisPool(config, ip, port, 30000);
			} catch (Exception e2) {
				logger.error("Second create JedisPool error : " +e2.getMessage(), e2);
			}
		}
	}

	/**
	 * 在多线程环境同步初始�?
	 */
	private static synchronized void poolInit(String ip) {
		if (jedisPool == null) {
			initialPool(ip);
		}
	}

	/**
	 * 同步获取Jedis实例
	 * 
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis(String ip) {
		if (jedisPool == null) {
			poolInit(ip);
		}
		Jedis jedis = null;
		try {
			if (jedisPool != null) {
				jedis = jedisPool.getResource();
			}
		} catch (Exception e) {
			logger.error("Get jedis error : " + e,e);
		} finally {
			returnResource(jedis);
		}
		return jedis;
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null && jedisPool != null) {
			jedisPool.returnResource(jedis);
		}
	}




}
