package com.diamondboss.util.tools.jedis;


import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import com.alibaba.fastjson.JSONObject;


@Service("cacheFunc")
public class CacheFunc {
	public static Logger log = LoggerFactory.getLogger(CacheFunc.class);
	public static final String sessid="sessid";

	private static JedisPool jedisPool = null;
	private static JedisSentinelPool jedisSentinelPool=null;
	
	// private static Map<String, String> tmpCache= new HashMap<String, String>();
	public JedisPool setJedisPool(String host, String pwd){
		try {    
			JedisPoolConfig config = new JedisPoolConfig();
//			config.setMaxTotal(200);
			config.setTestOnBorrow(true);
			String[] hostStr= host.split(":");
			jedisPool = new JedisPool(config, hostStr[0], Integer.parseInt(hostStr[1]), 90000, pwd);
		} catch (Exception e) {
			log.info("create JedisPool error : "+e.getMessage() , e);
		}
		return jedisPool;
	}
	
	public JedisSentinelPool setJedisSentinelPool(String hosts, String pwd){
		try {
			if(StringUtils.isBlank(hosts)){
				log.info("redis hosts is blank");
			}
        	GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        	poolConfig.setMaxTotal(200);
        	poolConfig.setTestOnBorrow(true);
			Set<String> hostsSet= new HashSet<String>();
			String[] hostsStrs= hosts.split(";");
			for(String s: hostsStrs){
				if(StringUtils.isBlank(s)){
					continue;
				}
				hostsSet.add(s);
			}
			
			jedisSentinelPool= new JedisSentinelPool("jedisSentinelPool", hostsSet,poolConfig, pwd);
		} catch (Exception e) {
			log.info("create JedisPool error : "+e.getMessage() , e);
		}
		return jedisSentinelPool;
	}

	@SuppressWarnings("deprecation")
	public Jedis getJedis(){
		if(jedisPool==null){
			synchronized(CacheFunc.class){
				if(jedisPool==null){
					//生产服务器缓�?  内网
//					setJedisPool("10.8.20.209:6379","atx@7wixs");
					//生产服务器缓�?  外网
//					setJedisPool("106.75.198.27:6379","atx@7wixs");
					
					// 测试服务器缓�?
//					setJedisPool("121.42.50.253:6378","leishantest");
					setJedisPool("182.92.149.119:6379","pet123456");
				}
			}
		}
		
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
        	log.error("[put] redis cache error", e);
        } finally {
        	if(jedis!=null){
        		jedisPool.returnResource(jedis);
        	}
        }
        return jedis;
	}
	
	@SuppressWarnings("deprecation")
	public Jedis getSentinelJedis(){
		if(jedisSentinelPool==null){
			synchronized(CacheFunc.class){
				if(jedisSentinelPool==null){
					setJedisSentinelPool("182.92.149.119:6379","pet123456");
//					setJedisSentinelPool("45.249.246.215:6378","leishantest");
				}
			}
		}
		
        Jedis jedis = null;
        try {
            //jedis = jedisPool.getResource();
            jedis = jedisSentinelPool.getResource();
        } catch (Exception e) {
        	log.error("[put] redis cache error", e);
        } finally {
        	if(jedis!=null){
        		jedisPool.returnResource(jedis);
        	}
        }
        return jedis;
	}

	public String setStr(String key, String val){
		Jedis jedis = getJedis();
		String status = jedis.set(key, val);
        log.info("write redis key="+key+",value="+val);
		return status;
	}
	
	public boolean exists(String key){
		Jedis jedis = getJedis();
        log.info("exists===key=="+key);
		return jedis.exists(key);
	}
	
	public String setStrEx(String key, int seconds, String val){
		Jedis jedis = getJedis();
		String status = jedis.setex(key, seconds, val);
        log.info("write redis key="+key+",value="+val);
		return status;
	}
	public String getStr(String key){
		Jedis jedis = getJedis();
		String val = jedis.get(key);
        log.info("read redis str key="+key+",value======================"+val);
        if(val==null){
        	return val;
        }
        if(val.equals("PONG")||val.equals("OK")){
        	val = jedis.get(key);
        	log.info("read redis obj again ==== key="+key+",value======================"+val);
        	if(val==null ||val.equals("PONG")||val.equals("OK")){
            	val = jedis.get(key);
            	log.info("read redis obj again2 ==== key="+key+",value======================"+val);
            }
        }
		return val;
	}
	
	public String setObj(String key, Object obj){
		Jedis jedis = getJedis();
		String val = JSONObject.toJSONString(obj);
		String status = jedis.set(key, val);
        log.info("write redis obj key="+key+",value="+val);
		return status;
	}
	public <C> C getObj(String key, Class<C> cla){
		Jedis jedis = getJedis();
		String val = jedis.get(key);
		C obj = JSONObject.parseObject(val, cla);
        log.info("read redis obj key="+key+",value="+val);
		return obj;
	}
	
	public boolean setCache(String key, String value){
		redisBase.init();
		setStr(key, value);
		//tmpCache.put(key, value);
		
		return true;
	}
	public String getCache(String key){
		redisBase.init();
		//return tmpCache.get(key);
		return getStr(key);
	}
	

	public boolean setCacheObj(String key, Object value){
		redisBase.init();
		//tmpCache.put(key, JSONObject.toJSONString(value));
		setStr(key, JSONObject.toJSONString(value));

		return true;
	}
	public Object getCacheObj(String key, Object value){
		redisBase.init();
		//String v=tmpCache.get(key);
		String v = getStr(key);
		if(StringUtils.isNotBlank(v)){
			return JSONObject.parseObject(v, value.getClass());
		}
		return null;
	}
	
	public boolean saveSession(HttpServletRequest request, String key, String value){
		if(request == null || StringUtils.isBlank(key) || StringUtils.isBlank(value)){
			return false;
		}
		return true;
	}
	
	// session
    // 获取session中的对象
    public Object getSesnObj(HttpServletRequest request, String key){
    	if(request == null || StringUtils.isBlank(key)){
    		return null;
    	}
    	Object o = request.getSession().getAttribute(key);
    	return o;
    }
    // 获取session中的对象
    public void setSesnObj(HttpServletRequest request, String key, Object obj){
    	if(request == null || StringUtils.isBlank(key) || obj== null){
    		return ;
    	}
    	request.getSession().setAttribute(key, obj);
    }
    
    // 获取session中的对象
    public void removeSesnObj(HttpServletRequest request, String key){
    	if(request == null || StringUtils.isBlank(key)){
    		return ;
    	}
    	request.getSession().removeAttribute(key);
    }
	
	@Resource(name = "redisBase")
	private RedisBase redisBase = null;
	
}
