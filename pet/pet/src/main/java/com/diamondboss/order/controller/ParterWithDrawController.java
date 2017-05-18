package com.diamondboss.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.service.IParterWithDraw;
import com.diamondboss.util.pojo.ParterWithDrawPojo;
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
		
		List<ParterWithDrawPojo> parterOrders = parterWithDraw.queryParterDetail(parter_id);
		
		List<ParterDetailVo> parterDetails = new ArrayList<>();
		
		for (ParterWithDrawPojo parterWithDraw : parterOrders) {
			ParterDetailVo ParterDetailVo = new ParterDetailVo();
			ParterDetailVo.setAmount(parterWithDraw.getAmount());
			ParterDetailVo.setOrderTime(parterWithDraw.getOrderTime());
			
			parterDetails.add(ParterDetailVo);
		}
		
		APPResponseBody app = new APPResponseBody();
		app.setData(parterDetails);
		app.setRetnCode(0);
		return app;
	}
}
