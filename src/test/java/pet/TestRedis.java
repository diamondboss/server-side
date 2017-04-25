package pet;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.diamondboss.util.tools.jedis.CacheFunc;
import com.diamondboss.util.tools.jedis.RedisBase;

@RunWith(SpringJUnit4ClassRunner.class)     //��ʾ�̳���SpringJUnit4ClassRunner��  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class TestRedis {
	
	@Resource(name = "cacheFunc")
	private CacheFunc cacheFunc = null;

	@Test 
	public void testRedis(){
		RedisBase redisBase = new RedisBase();
		redisBase.init();
		cacheFunc.setStr("zbc123", "��������");
		
		
		
		//System.out.println(cacheFunc.getCache("zbc123"));
	}

}
