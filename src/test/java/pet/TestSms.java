package pet;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.diamondboss.order.pojo.OrderUserPojo;
import com.diamondboss.order.vo.SendNotifySmsInfoVo;
import com.diamondboss.util.push.rongyun.service.impl.SendMsgServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)     
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class TestSms {
	
	@Resource(name = "msgSendServiceImpl")
	private SendMsgServiceImpl sMSSendService = null;
	
	@Test 

	public void testSendVerifyMsg(){
		sMSSendService.sendVerifyMsg("18238954989");
	}
	
	@Test
	public void testSendNotifyMsg(){
		OrderUserPojo pojo =  new OrderUserPojo();
		pojo.setPhone("15026842350");
		pojo.setUserName("小黄");
		pojo.setOrderDate("2017-07-04");
		pojo.setPartnerName("'博文阿姨'");
		
		SendNotifySmsInfoVo sendSmsInfo = new SendNotifySmsInfoVo();
		sendSmsInfo.setUserName(pojo.getUserName());
		sendSmsInfo.setPartnerName(pojo.getPartnerName());
		sendSmsInfo.setOrderDate(pojo.getOrderDate());
		sendSmsInfo.setPhone(pojo.getPhone());
		
		sMSSendService.sendNotifyMsg(sendSmsInfo, 1);
	}


	@Test
	public void testVerifyCode(){
		sMSSendService.verifyCode("1314","254732");
	}
	
	@Test
	public void testRYGetToken(){
		sMSSendService.getToken("000001", "我", "http://zfxue-test.oss-cn-shanghai.aliyuncs.com/dbmap/map1.jpg");
	}

}
