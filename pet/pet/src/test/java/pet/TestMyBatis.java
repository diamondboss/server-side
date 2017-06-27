package pet;

import javax.annotation.Resource;

import com.diamondboss.util.pojo.UserInfoPojo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.diamondboss.personal.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)     //��ʾ�̳���SpringJUnit4ClassRunner��  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestMyBatis {
	 
	 private static Logger logger = LogManager.getLogger(TestMyBatis.class);
	//  private ApplicationContext ac = null;  
	    @Resource  
	    private IUserService userService = null;  
	  
	//  @Before  
	//  public void before() {  
//	      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//	      userService = (IUserService) ac.getBean("userService");  
	//  }  
	  
	    @Test  
	    public void test1() {  
	        UserInfoPojo user = userService.getUserById(1);
	        // System.out.println(user.getUserName());  
	        // logger.info("ֵ��"+user.getUserName());  
	        logger.info(JSON.toJSONString(user));  
	    }  
}
