package com.personal.callMe.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.personal.callMe.service.IUserService;
import com.personal.util.User;

@Controller  
@RequestMapping("/user")  
public class UserController {

	 @Resource  
	    private IUserService userService;  
	      
	    @RequestMapping(value="showUser",method=RequestMethod.POST)
	    public String toIndex(HttpServletRequest request,Model model){  
	        int userId = Integer.parseInt(request.getParameter("id"));  
	        User user = this.userService.getUserById(userId);  
	        model.addAttribute("user", user);  
	        return "showUser";  
	    }  
}
