package com.diamondboss.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.service.IParterWithDraw;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.ParterDetailVo;

/**
 * 合伙人提现控制器
 * @author xzf
 *
 */
@Controller
@RequestMapping("/withDraw")
public class ParterWithDrawController {
	
	@Resource  
	 private IParterWithDraw parterWithDraw;
	/**
	 * 查询用户的预算明细记录
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryParterDetail" ,method = RequestMethod.POST)
	public APPResponseBody queryUserDetail(HttpServletRequest request) {
		//获取前台传过来的用户ID
		String parter_id = request.getParameter("parterId");
		
		APPResponseBody app = new APPResponseBody();
		if(parter_id == null || "".equals(parter_id)){
			app.setData("参数不合法");
			app.setRetnCode(1);
			return app;
		}
		
		List<ParterDetailVo> parterOrders = parterWithDraw.queryParterDetail(parter_id);

		app.setData(parterOrders);
		app.setRetnCode(0);
		return app;
	}
}
