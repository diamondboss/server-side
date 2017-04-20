package com.personal.util.tools.jedis;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personal.util.tools.jedis.CacheFunc;
import com.personal.util.tools.jedis.RedisBase;

@Service("testRedis")
public class TestRedis {
	
	@Resource(name = "cacheFunc")
	private CacheFunc cacheFunc = null;

	public void testRedis(){
		RedisBase redisBase = new RedisBase();
		redisBase.init();
		cacheFunc.setStr("zbc123456", "薛志");
	}

}
