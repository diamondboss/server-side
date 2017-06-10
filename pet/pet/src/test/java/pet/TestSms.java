package pet;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.diamondboss.util.push.rongyun.service.impl.MsgSendServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)     
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class TestSms {
	
	@Resource(name = "sMSSendService")
	private MsgSendServiceImpl sMSSendService = null;
	
	@Test 
	public void testRedis(){
		sMSSendService.sendMsg("15026842350");
	}
}
