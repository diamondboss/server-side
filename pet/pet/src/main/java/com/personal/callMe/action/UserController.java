package com.personal.callMe.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.callMe.service.IUserService;
import com.personal.util.User;
import com.personal.util.vo.APPResponseBody;

@Controller  
@RequestMapping("/user")  
public class UserController {

	 @Resource  
	    private IUserService userService;  
	      
	 	@ResponseBody
	    @RequestMapping(value="showUser",method=RequestMethod.POST)
	    public String toIndex(HttpServletRequest request){  
	        int userId = 1;  
	        User user = this.userService.getUserById(userId);  
	        APPResponseBody app = new APPResponseBody();
	        app.setData(user.getAge());
	        app.setRetnCode(0);
	        return "showUser";  
	    }  
}
