package pet;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.diamondboss.util.sms.rongyun.service.impl.MsgSendService;


@RunWith(SpringJUnit4ClassRunner.class)     
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class TestSms {
	
	@Resource(name = "sMSSendService")
	private MsgSendService sMSSendService = null;
	
	@Test 
	public void testRedis(){
		sMSSendService.sendMsg("15026842350");
	}
}
